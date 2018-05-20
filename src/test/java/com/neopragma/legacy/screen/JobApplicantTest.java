package com.neopragma.legacy.screen;

import com.neopragma.legacy.screen.support.Address;
import com.neopragma.legacy.screen.support.AddressProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

/**
 * Automated unit checks for the base version of the JobApplicant application.
 * This version of the code contains a number of code smells that may point to
 * potential improvements in the design of the code.
 *
 * @author neopragma
 * @version 1.0.0
 * @since 1.7
 *
 * @author todd flanders
 * @version 2.0.0
 * @since 2.0
 */
@RunWith(MockitoJUnitRunner.class)
public class JobApplicantTest {

    @InjectMocks
    private JobApplicant jobApplicant = new JobApplicant();

    @Mock
    AddressProvider addressProvider;

    @Test
    public void addSetsName() throws Exception {
        jobApplicant.add("first", "middle", "last", "ssn", "zip");
        assertEquals("first", jobApplicant.getFirstName());
        assertEquals("last", jobApplicant.getLastName());
        assertEquals("middle", jobApplicant.getMiddleName());
    }

    @Test
    public void addSetsAddressFromAddressProvider() throws Exception {
        Address address = new Address();
        when(addressProvider.buildAddressFromZipCode("zip")).thenReturn(address);
        jobApplicant.add("first", "middle", "last", "ssn", "zip");
        assertSame(address, jobApplicant.getAddress());
    }

    @Test
    public void givenInvalidSsn_AddSetsEmptySsn() throws Exception {
        jobApplicant.add("first", "middle", "last", "ssn", "zip");
        assertEquals("", jobApplicant.getSsn());
    }

    @Test
    public void givenValidSsn_AddSetsSsn() throws Exception {
        Address address = new Address();
        when(addressProvider.buildAddressFromZipCode("zip")).thenReturn(address);
        jobApplicant.add("first", "middle", "last", "123-45-6789", "zip");
        assertEquals("123456789", jobApplicant.getSsn());
    }

    @Test
    public void completeSpanishNameProvided() {
        jobApplicant.setSpanishName("PrimerNombre", "SegundoNombre", "PrimerApellido", "SegundoApellido");
        assertEquals(0, jobApplicant.validateName());
    }

    @Test
    public void spanishNameWithOneFirstNameProvided() {
        jobApplicant.setSpanishName("PrimerNombre", null, "PrimerApellido", "SegundoApellido");
        assertEquals(0, jobApplicant.validateName());
    }

    @Test
    public void spanishNameWithOneLastNameProvided() {
        jobApplicant.setSpanishName("PrimerNombre", null, "PrimerApellido", null);
        assertEquals(0, jobApplicant.validateName());
    }

    @Test
    public void spanishNameWithNoFirstNameProvided() {
        jobApplicant.setSpanishName(null, null, "PrimerApellido", null);
        assertEquals(6, jobApplicant.validateName());
    }

    @Test
    public void spanishNameWithNoLastNameProvided() {
        jobApplicant.setSpanishName("PrimerNombre", "SegundoNombre", null, null);
        assertEquals(6, jobApplicant.validateName());
    }

}
