package com.neopragma.legacy.screen.support;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class AddressProvider {

    public Address buildAddressFromZipCode(String zipCode) throws URISyntaxException, IOException {
        Address address = new Address();
        address.setZipCode(zipCode);
        try (CloseableHttpResponse response = getAddressResponse(zipCode)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                StringBuffer result = getRawData(response);
                parseResultAndUpdateAddress(result, address);
            } else {
                address.setCity("");
                address.setState("");
            }
        }
        return address;
    }

    private void parseResultAndUpdateAddress(StringBuffer result, Address address) {
        int metaOffset = result.indexOf("<meta ");
        int contentOffset = result.indexOf(" content=\"Zip Code ", metaOffset);
        contentOffset += 19;
        contentOffset = result.indexOf(" - ", contentOffset);
        contentOffset += 3;
        int stateOffset = result.indexOf(" ", contentOffset);
        address.setCity(result.substring(contentOffset, stateOffset));
        stateOffset += 1;
        address.setState(result.substring(stateOffset, stateOffset+2));
    }

    private StringBuffer getRawData(CloseableHttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result;
    }

    private CloseableHttpResponse getAddressResponse(String zipCode) throws URISyntaxException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.zip-codes.com")
                .setPath("/search.asp")
                .setParameter("fld-zip", zipCode)
                .setParameter("selectTab", "0")
                .setParameter("srch-type", "city")
                .build();
        return httpclient.execute(new HttpGet(uri));

    }

}
