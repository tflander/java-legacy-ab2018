package com.neopragma.legacy.screen;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class AddressProviderIntegrationTest {

    private AddressProvider addressProvider;

    @Before
    public void setUp() throws Exception {
        addressProvider = new AddressProvider();
    }

    @Test
    public void itFindsAddisonTexasBy5DigitZipCode() throws URISyntaxException, IOException {
        Address address = addressProvider.buildAddressFromZipCode("75001");
        assertEquals("Addison", address.getCity());
        assertEquals("TX", address.getState());
    }

    @Test
    public void itFindsMaranaArizonaBy9DigitZipCode() throws URISyntaxException, IOException {
        Address address = addressProvider.buildAddressFromZipCode("856585578");
        assertEquals("Marana", address.getCity());
        assertEquals("AZ", address.getState());
    }
}
