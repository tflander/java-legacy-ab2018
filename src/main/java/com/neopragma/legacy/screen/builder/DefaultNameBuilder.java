package com.neopragma.legacy.screen.builder;

import com.neopragma.legacy.screen.domain.Name;

public class DefaultNameBuilder {

    public Name buildName(String first, String middle, String last) {
        return Name.builder()
                .firstName(first == null ? "" : first)
                .middleName(middle == null ? "" : middle)
                .lastName(last == null ? "" : last)
                .build();
    }

}
