package com.neopragma.legacy.screen.support;

public class DefaultNameBuilder {

    public Name buildName(String first, String middle, String last) {
        return Name.builder()
                .firstName(first == null ? "" : first)
                .middleName(middle == null ? "" : middle)
                .lastName(last == null ? "" : last)
                .build();
    }

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
