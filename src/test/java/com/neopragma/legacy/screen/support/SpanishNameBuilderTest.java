package com.neopragma.legacy.screen.support;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpanishNameBuilderTest {

    private SpanishNameBuilder spanishNameBuilder;
    private NameValidator nameValidator;

    @Before
    public void setUp() throws Exception {
        spanishNameBuilder = new SpanishNameBuilder();
        nameValidator = new NameValidator();
    }

    @Test
    public void completeSpanishNameProvided() {
        Name name = spanishNameBuilder.buildName("PrimerNombre", "SegundoNombre", "PrimerApellido", "SegundoApellido");
        assertEquals(0, nameValidator.validateName(name));
    }

    @Test
    public void spanishNameWithOneFirstNameProvided() {
        Name name = spanishNameBuilder.buildName("PrimerNombre", null, "PrimerApellido", "SegundoApellido");
        assertEquals(0, nameValidator.validateName(name));
    }

    @Test
    public void spanishNameWithOneLastNameProvided() {
        Name name = spanishNameBuilder.buildName("PrimerNombre", null, "PrimerApellido", null);
        assertEquals(0, nameValidator.validateName(name));
    }

    @Test
    public void spanishNameWithNoFirstNameProvided() {
        Name name = spanishNameBuilder.buildName(null, null, "PrimerApellido", null);
        assertEquals(6, nameValidator.validateName(name));
    }

    @Test
    public void spanishNameWithNoLastNameProvided() {
        Name name = spanishNameBuilder.buildName("PrimerNombre", "SegundoNombre", null, null);
        assertEquals(6, nameValidator.validateName(name));
    }


}
