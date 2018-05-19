package com.neopragma.legacy.screen;

public class DefaultNameBuilder {

    public Name setName(String first, String middle, String last) {
        return Name.builder()
                .firstName(first == null ? "" : first)
                .middleName(middle == null ? "" : middle)
                .lastName(last == null ? "" : last)
                .build();
    }

    public int validateName(Name name) {
        if ( name.getFirstName().length() > 0 && name.getLastName().length() > 0 ) {
            return 0;
        } else {
            return 6;
        }
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
