package com.neopragma.legacy.screen.integrationTests;

import com.neopragma.legacy.screen.builder.AddressBuilder;
import com.neopragma.legacy.screen.domain.Address;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class AddressBuilderIntegrationTest {

    private AddressBuilder addressBuilder;

    @Before
    public void setUp() throws Exception {
        addressBuilder = new AddressBuilder();
    }

    @Test
    public void itFindsAddisonTexasBy5DigitZipCode() throws URISyntaxException, IOException {
        Address address = addressBuilder.buildAddressFromZipCode("75001");
        assertEquals("Addison", address.getCity());
        assertEquals("TX", address.getState());
    }

    @Test
    public void itFindsMaranaArizonaBy9DigitZipCode() throws URISyntaxException, IOException {
        Address address = addressBuilder.buildAddressFromZipCode("856585578");
        assertEquals("Marana", address.getCity());
        assertEquals("AZ", address.getState());
    }
}
