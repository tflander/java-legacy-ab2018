package com.neopragma.legacy.screen;

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

public class Address {

    private String zipCode;
    private String city;
    private String state;

    public void populateUsingZipCode(String zipCode) throws URISyntaxException, IOException {
        this.zipCode = zipCode;
        // Use a service to look up the city and state based on zip code.
        // Save the returned city and state if content length is greater than zero.
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.zip-codes.com")
                .setPath("/search.asp")
                .setParameter("fld-zip", this.zipCode)
                .setParameter("selectTab", "0")
                .setParameter("srch-type", "city")
                .build();
        HttpGet request = new HttpGet(uri);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                long len = entity.getContentLength();
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                int metaOffset = result.indexOf("<meta ");
                int contentOffset = result.indexOf(" content=\"Zip Code ", metaOffset);
                contentOffset += 19;
                contentOffset = result.indexOf(" - ", contentOffset);
                contentOffset += 3;
                int stateOffset = result.indexOf(" ", contentOffset);
                city = result.substring(contentOffset, stateOffset);
                stateOffset += 1;
                state = result.substring(stateOffset, stateOffset+2);
            } else {
                city = "";
                state = "";
            }
        } finally {
            response.close();
        }
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
