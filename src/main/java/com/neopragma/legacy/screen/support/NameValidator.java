package com.neopragma.legacy.screen.support;

public class NameValidator {

    public int validateName(Name name) {
        if ( name.getFirstName().length() > 0 && name.getLastName().length() > 0 ) {
            return 0;
        } else {
            return 6;
        }
    }

}
