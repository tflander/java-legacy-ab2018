package com.neopragma.legacy.screen.support;

import com.neopragma.legacy.screen.support.SocialSecurityNumberValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SocialSecurityNumberValidatorTest {

    private SocialSecurityNumberValidator socialSecurityNumberValidator;

    @Before
    public void setUp() throws Exception {
        socialSecurityNumberValidator = new SocialSecurityNumberValidator();
    }

    @Test
    public void validSsnWithNoDashes() {
        assertEquals(0, socialSecurityNumberValidator.validate("123456789"));
    }

    @Test
    public void validSsnWithDashes() {
        assertEquals(0, socialSecurityNumberValidator.validate("123-45-6789"));
    }

    @Test
    public void ssnWithDashesInWrongPlaces() {
        assertEquals(1, socialSecurityNumberValidator.validate("12-345-6789"));
    }

    @Test
    public void ssnIsTooShort() {
        assertEquals(1, socialSecurityNumberValidator.validate("12345678"));
    }

    @Test
    public void ssnIsTooLong() {
        assertEquals(1, socialSecurityNumberValidator.validate("1234567890"));
    }

    @Test
    public void ssnAreaNumberIs000() {
        assertEquals(2, socialSecurityNumberValidator.validate("000223333"));
    }

    @Test
    public void ssnAreaNumberIs666() {
        assertEquals(2, socialSecurityNumberValidator.validate("666223333"));
    }

    @Test
    public void ssnAreaNumberStartsWith9() {
        assertEquals(2, socialSecurityNumberValidator.validate("900223333"));
    }

    @Test
    public void ssnSerialNumberIs0000() {
        assertEquals(3, socialSecurityNumberValidator.validate("111220000"));
    }

    @Test
    public void itRejectsSsn078051120() {
        assertEquals(4, socialSecurityNumberValidator.validate("078051120"));
    }

    @Test
    public void itRejectsSsn219099999() {
        assertEquals(4, socialSecurityNumberValidator.validate("219099999"));
    }


}
