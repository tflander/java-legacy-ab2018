package com.neopragma.legacy.screen.support;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultNameBuilderTest {

    private DefaultNameBuilder defaultNameBuilder;
    private NameValidator nameValidator;

    @Before
    public void setUp() throws Exception {
        defaultNameBuilder = new DefaultNameBuilder();
        nameValidator = new NameValidator();
    }

    @Test
    public void completeNameProvided() {
        Name name = defaultNameBuilder.buildName("First", "Middle", "Last");
        assertEquals(0, nameValidator.validateName(name));
    }

    @Test
    public void firstAndLastNamesProvided() {
        Name name = defaultNameBuilder.buildName("First", null, "Last");
        assertEquals(0, nameValidator.validateName(name));
    }

    @Test
    public void missingFirstName() {
        Name name = defaultNameBuilder.buildName(null, null, "Last");
        assertEquals(6, nameValidator.validateName(name));
    }

    @Test
    public void missingLastName() {
        Name name = defaultNameBuilder.buildName("First", null, null);
        assertEquals(6, nameValidator.validateName(name));
    }


}
