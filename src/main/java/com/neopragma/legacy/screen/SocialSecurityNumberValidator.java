package com.neopragma.legacy.screen;

public class SocialSecurityNumberValidator {
    public int validate(String ssn) {
        if (ssn.matches("(\\d{3}-\\d{2}-\\d{4}|\\d{9})")) {
            ssn = ssn.replaceAll("-", "");
        }

        if (!ssn.matches("\\d{9}")) {
            return 1;
        }
        if ("000".equals(ssn.substring(0, 3)) ||
                "666".equals(ssn.substring(0, 3)) ||
                "9".equals(ssn.substring(0, 1))) {
            return 2;
        }
        if ("0000".equals(ssn.substring(5))) {
            return 3;
        }
        for (String specialCase : specialCases) {
            if (ssn.equals(specialCase)) {
                return 4;
            }
        }
        return 0;
    }

    private final String[] specialCases = new String[]{
            "219099999", "078051120"
    };

}
