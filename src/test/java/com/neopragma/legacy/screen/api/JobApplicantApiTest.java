package com.neopragma.legacy.screen.api;

import com.neopragma.legacy.screen.JobApplicant;
import com.neopragma.legacy.screen.builder.AddressBuilder;
import com.neopragma.legacy.screen.builder.JobApplicantApi;
import com.neopragma.legacy.screen.domain.Address;
import com.neopragma.legacy.screen.domain.Name;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicantApiTest {

    @InjectMocks
    private JobApplicantApi jobApplicantApi = new JobApplicantApi();

    @Mock
    private
    AddressBuilder addressBuilder;

    @Test
    public void addSetsName() throws Exception {
        JobApplicant jobApplicant = jobApplicantApi.createJobApplicant("first", "middle", "last", "ssn", "zip");
        Name name = jobApplicant.getName();
        assertEquals("first", name.getFirstName());
        assertEquals("last", name.getLastName());
        assertEquals("middle", name.getMiddleName());
    }

    @Test
    public void addSetsAddressFromAddressProvider() throws Exception {
        Address address = new Address();
        when(addressBuilder.buildAddressFromZipCode("zip")).thenReturn(address);
        JobApplicant jobApplicant = jobApplicantApi.createJobApplicant("first", "middle", "last", "ssn", "zip");
        assertSame(address, jobApplicant.getAddress());
    }
    @Test
    public void givenInvalidSsn_AddSetsEmptySsn() throws Exception {
        JobApplicant jobApplicant = jobApplicantApi.createJobApplicant("first", "middle", "last", "ssn", "zip");
        assertEquals("", jobApplicant.getSsn());
    }

    @Test
    public void givenValidSsn_AddSetsSsn() throws Exception {
        Address address = new Address();
        when(addressBuilder.buildAddressFromZipCode("zip")).thenReturn(address);
        JobApplicant jobApplicant = jobApplicantApi.createJobApplicant("first", "middle", "last", "123-45-6789", "zip");
        assertEquals("123456789", jobApplicant.getSsn());
    }

}
