package com.neopragma.legacy.screen;

import com.neopragma.legacy.screen.builder.AddressBuilder;
import com.neopragma.legacy.screen.builder.DefaultNameBuilder;
import com.neopragma.legacy.screen.builder.JobApplicantApi;
import com.neopragma.legacy.screen.builder.SpanishNameBuilder;
import com.neopragma.legacy.screen.formatter.NameFormatter;
import com.neopragma.legacy.screen.formatter.SocialSecurityNumberFormatter;
import com.neopragma.legacy.screen.persistance.JobApplicantRepository;
import com.neopragma.legacy.screen.validator.NameValidator;
import com.neopragma.legacy.screen.validator.SocialSecurityNumberValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Automated unit checks for the base version of the JobApplicant application.
 * This version of the code contains a number of code smells that may point to
 * potential improvements in the design of the code.
 *
 * @author neopragma
 * @author todd flanders
 * @version 2.0.0
 * @since 1.7
 */
@RunWith(MockitoJUnitRunner.class)
public class JobApplicantTest {

    @InjectMocks
    private JobApplicant jobApplicant = new JobApplicant();

    @Mock
    private AddressBuilder addressBuilder;

    @Mock
    private DefaultNameBuilder defaultNameBuilder;

    @Mock
    private JobApplicantRepository jobApplicantRepository;

    @Mock
    private SocialSecurityNumberValidator socialSecurityNumberValidator;

    @Mock
    private NameValidator nameValidator;

    @Mock
    private NameFormatter nameFormatter;

    @Mock
    private SocialSecurityNumberFormatter socialSecurityNumberFormatter;

    @Mock
    private SpanishNameBuilder spanishNameBuilder;

    @Mock
    private JobApplicantApi jobApplicantApi;

    @Test
    public void deprecatedAddIsFacade() throws Exception {
        when(jobApplicantApi.createJobApplicant("first", "middle", "last", "ssn", "zip")).thenReturn(jobApplicant);
        jobApplicant.add("first", "middle", "last", "ssn", "zip");
        verify(jobApplicantApi).createJobApplicant("first", "middle", "last", "ssn", "zip");
    }

    @Test
    public void deprecatedSetNameIsFacade() throws Exception {
        jobApplicant.setName("first", "middle", "last");
        verify(defaultNameBuilder).buildName("first", "middle", "last");
    }

    @Test
    public void deprecatedSetSpanishNameIsFacade() throws Exception {
        jobApplicant.setSpanishName("firstFirst", "secondFirst", "firstLast", "secondLast");
        verify(spanishNameBuilder).buildName("firstFirst", "secondFirst", "firstLast", "secondLast");
    }

    @Test
    public void deprecatedFormatLastNameFirstIsFacade() throws Exception {
        jobApplicant.formatLastNameFirst();
        verify(nameFormatter).formatLastNameFirst(jobApplicant.getName());
    }

    @Test
    public void deprecatedValidateNameIsFacade() throws Exception {
        jobApplicant.validateName();
        verify(nameValidator).validateName(jobApplicant.getName());
    }

    @Test
    public void deprecatedFormatSsnIsFacade() throws Exception {
        jobApplicant.formatSsn();
        verify(socialSecurityNumberFormatter).addDashes(jobApplicant.getSsn());
    }

    @Test
    public void deprecatedValidateSsnIsFacade() throws Exception {
        jobApplicant.validateSsn();
        verify(socialSecurityNumberValidator).validate(jobApplicant.getSsn());
    }

    @Test
    public void deprecatedSetZipCodeIsFacade() throws Exception {
        jobApplicant.setZipCode("zip");
        verify(addressBuilder).buildAddressFromZipCode("zip");
    }

    @Test
    public void deprecadedSaveIsFacade() throws Exception {
        jobApplicant.save();
        verify(jobApplicantRepository).save(jobApplicant);
    }

}
