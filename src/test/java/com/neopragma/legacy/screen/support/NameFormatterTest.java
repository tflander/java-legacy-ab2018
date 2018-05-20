package com.neopragma.legacy.screen.support;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameFormatterTest {

    private NameFormatter nameFormatter;

    @Before
    public void setUp() throws Exception {
        nameFormatter = new NameFormatter();
    }

    @Test
    public void formatEnglishNameLastNameFirst() {
        Name name = new DefaultNameBuilder().buildName("First", "Middle", "Last");
        assertEquals("Last, First Middle", nameFormatter.formatLastNameFirst(name));
    }

}
