package com.neopragma.legacy.screen.support;

public class SpanishNameBuilder {

    public Name buildName(String primerNombre, String segundoNombre,
                               String primerApellido, String segundoApellido) {
        String lastName;
        if (primerApellido != null) {
            lastName = primerApellido + (segundoApellido == null ? null : " " + segundoApellido);
        } else {
            lastName = "";
        }

        return Name.builder()
                .firstName(primerNombre == null ? "" : primerNombre)
                .middleName(segundoNombre == null ? "" : segundoNombre)
                .lastName(lastName)
                .build();

    }

}
