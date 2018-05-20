package com.neopragma.legacy.screen.formatter;

public class SocialSecurityNumberFormatter {

    public String addDashes(String ssn) {
        return ssn.substring(0, 3) + "-" +
                ssn.substring(3, 5) +
                "-" +
                ssn.substring(5);
    }

    public String removeDashes(String ssn) {
        if (ssn.matches("(\\d{3}-\\d{2}-\\d{4}|\\d{9})")) {
            return ssn.replaceAll("-", "");
        } else {
            return "";
        }
    }
}
