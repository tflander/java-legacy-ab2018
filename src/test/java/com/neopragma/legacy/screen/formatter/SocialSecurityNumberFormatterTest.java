package com.neopragma.legacy.screen.formatter;

import com.neopragma.legacy.screen.formatter.SocialSecurityNumberFormatter;
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
        assertEquals("123-45-6789", socialSecurityNumberFormatter.addDashes("123456789"));
    }

}