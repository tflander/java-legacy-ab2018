package com.neopragma.legacy.screen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultNameBuilderTest {

    private DefaultNameBuilder defaultNameBuilder;

    @Before
    public void setUp() throws Exception {
        defaultNameBuilder = new DefaultNameBuilder();
    }

    @Test
    public void completeNameProvided() {
        Name name = defaultNameBuilder.setName("First", "Middle", "Last");
        assertEquals(0, defaultNameBuilder.validateName(name));
    }

    @Test
    public void firstAndLastNamesProvided() {
        Name name = defaultNameBuilder.setName("First", null, "Last");
        assertEquals(0, defaultNameBuilder.validateName(name));
    }

    @Test
    public void missingFirstName() {
        Name name = defaultNameBuilder.setName(null, null, "Last");
        assertEquals(6, defaultNameBuilder.validateName(name));
    }

    @Test
    public void missingLastName() {
        Name name = defaultNameBuilder.setName("First", null, null);
        assertEquals(6, defaultNameBuilder.validateName(name));
    }

    @Test
    public void formatEnglishNameLastNameFirst() {
        Name name = defaultNameBuilder.setName("First", "Middle", "Last");
        assertEquals("Last, First Middle", defaultNameBuilder.formatLastNameFirst(name));
    }


}
