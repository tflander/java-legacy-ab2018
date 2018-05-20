package com.neopragma.legacy.screen.support;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SocialSecurityNumberFormatterTest {

    private SocialSecurityNumberFormatter socialSecurityNumberFormatter;

    @Before
    public void setUp() throws Exception {
        socialSecurityNumberFormatter = new SocialSecurityNumberFormatter();
    }

    @Test
    public void ssnFormattingTest() {
        assertEquals("123-45-6789", socialSecurityNumberFormatter.formatSsn("123456789"));
    }

}
