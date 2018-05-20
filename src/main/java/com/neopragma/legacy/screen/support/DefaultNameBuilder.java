package com.neopragma.legacy.screen.support;

public class DefaultNameBuilder {

    public Name buildName(String first, String middle, String last) {
        return Name.builder()
                .firstName(first == null ? "" : first)
                .middleName(middle == null ? "" : middle)
                .lastName(last == null ? "" : last)
                .build();
    }

}
