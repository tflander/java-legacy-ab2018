package com.neopragma.legacy.screen.support;

public class SocialSecurityNumberFormatter {
    public String formatSsn(String ssn) {
        return ssn.substring(0, 3) + "-" +
                ssn.substring(3, 5) +
                "-" +
                ssn.substring(5);
    }
}
