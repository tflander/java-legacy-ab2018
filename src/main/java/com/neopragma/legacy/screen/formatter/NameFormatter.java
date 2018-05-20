package com.neopragma.legacy.screen.formatter;

import com.neopragma.legacy.screen.domain.Name;

public class NameFormatter {

    public String formatLastNameFirst(Name name) {
        StringBuilder sb = new StringBuilder(name.getLastName());
        sb.append(", ");
        sb.append(name.getFirstName());
        if ( name.getMiddleName().length() > 0 ) {
            sb.append(" ");
            sb.append(name.getMiddleName());
        }
        return sb.toString();
    }

}
