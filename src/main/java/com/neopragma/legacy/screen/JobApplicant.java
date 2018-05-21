package com.neopragma.legacy.screen;

import com.neopragma.legacy.screen.builder.AddressBuilder;
import com.neopragma.legacy.screen.builder.DefaultNameBuilder;
import com.neopragma.legacy.screen.builder.JobApplicantApi;
import com.neopragma.legacy.screen.builder.SpanishNameBuilder;
import com.neopragma.legacy.screen.domain.Address;
import com.neopragma.legacy.screen.domain.Name;
import com.neopragma.legacy.screen.formatter.NameFormatter;
import com.neopragma.legacy.screen.formatter.SocialSecurityNumberFormatter;
import com.neopragma.legacy.screen.persistance.JobApplicantRepository;
import com.neopragma.legacy.screen.demo.*;
import com.neopragma.legacy.screen.validator.NameValidator;
import com.neopragma.legacy.screen.validator.SocialSecurityNumberValidator;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Job applicant class.
 */
public class JobApplicant {

    private Address address;
    private String ssn;
    private Name name;

    private final AddressBuilder addressBuilder = new AddressBuilder();
    private final DefaultNameBuilder defaultNameBuilder = new DefaultNameBuilder();
    private final JobApplicantRepository jobApplicantRepository = new JobApplicantRepository();
    private final SocialSecurityNumberValidator socialSecurityNumberValidator = new SocialSecurityNumberValidator();
    private final NameValidator nameValidator = new NameValidator();
    private final NameFormatter nameFormatter = new NameFormatter();
    private final SocialSecurityNumberFormatter socialSecurityNumberFormatter = new SocialSecurityNumberFormatter();
    private final SpanishNameBuilder spanishNameBuilder = new SpanishNameBuilder();
    private final JobApplicantApi jobApplicantApi = new JobApplicantApi();

    /**
     * @deprecated as of Release 2.0, use {@link com.neopragma.legacy.screen.builder.JobApplicantApi#createJobApplicant(String, String, String, String, String)}
     * @param firstName
     * @param middleName
     * @param lastName
     * @param ssn
     * @param zipCode
     * @throws URISyntaxException
     * @throws IOException
     */
    public void add(String firstName,
                    String middleName,
                    String lastName,
                    String ssn,
                    String zipCode) throws URISyntaxException, IOException {
        JobApplicant jobApplicant = jobApplicantApi.createJobApplicant(firstName, middleName, lastName, ssn, zipCode);
        this.name = jobApplicant.getName();
        this.ssn = jobApplicant.getSsn();
        this.address = jobApplicant.getAddress();
    }

    public void setSsn(String ssn) {
        this.ssn = socialSecurityNumberFormatter.removeDashes(ssn);
    }

    /**
     * @since 2.0
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * @since 2.0
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @since 2.0
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @since 2.0
     */
    public Name getName() {
        return name;
    }

    /**
     * @since 2.0
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #setName(Name)}
     */
    @Deprecated
    public void setName(String firstName, String middleName, String lastName) {
        name = defaultNameBuilder.buildName(firstName, middleName, lastName);
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #setName(Name)}
     */
    @Deprecated
    public void setSpanishName(String primerNombre, String segundoNombre,
                               String primerApellido, String segundoApellido) {
        name = spanishNameBuilder.buildName(primerNombre, segundoNombre, primerApellido, segundoApellido);
    }

    /**
     * @deprecated as of release 2.0, replaced by {@link NameFormatter#formatLastNameFirst(Name)}
     * @return
     */
    public String formatLastNameFirst() {
        return nameFormatter.formatLastNameFirst(name);
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link NameValidator#validateName(Name)}
     */
    @Deprecated
    public int validateName() {
        return nameValidator.validateName(name);
    }

    /**
     *  @deprecated  As of release 2.0, replaced by {@link SocialSecurityNumberFormatter#addDashes(String)}
     */
    @Deprecated
    public String formatSsn() {
        return socialSecurityNumberFormatter.addDashes(ssn);
    }

    /**
     *  @deprecated  As of release 2.0, replaced by {@link SocialSecurityNumberValidator#validate(String)}
     */
    @Deprecated
    public int validateSsn() {
        return socialSecurityNumberValidator.validate(this.ssn);
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link AddressBuilder#buildAddressFromZipCode(String)}
     * @param zipCode
     * @throws URISyntaxException
     * @throws IOException
     */
    @Deprecated
    public void setZipCode(String zipCode) throws URISyntaxException, IOException {
        this.address = addressBuilder.buildAddressFromZipCode(zipCode);
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #getAddress()}
     */
    public String getCity() {
        return address.getCity();
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #getAddress()}
     */
    public String getState() {
        return address.getState();
    }

    /**
     * @deprecated As of release 2.0, use {@link JobApplicantRepository#save(JobApplicant...)}
     */
    @Deprecated
    public void save() {
        jobApplicantRepository.save(this);
    }

    @Override
    public String toString() {
        return "JobApplicant{" +
                "address=" + address +
                ", addressBuilder=" + addressBuilder +
                ", ssn='" + ssn + '\'' +
                ", name=" + name +
                '}';
    }

    /**
     * @deprecated As of release 2.0, use {@link JobApplicantApp#main(String[])}
     */
    @Deprecated
    public static void main(String[] args) throws URISyntaxException, IOException {
        JobApplicantApp.main(args);
    }

    /**
     * @deprecated As of release 2.0, use {@link #getName()}
     */
    @Deprecated
    protected String getFirstName() {
        return name.getFirstName();
    }

    /**
     * @deprecated As of release 2.0, use {@link #getName()}
     */
    @Deprecated
    protected String getLastName() {
        return name.getLastName();
    }

    /**
     * @deprecated As of release 2.0, use {@link #getName()}
     */
    @Deprecated
    protected String getMiddleName() {
        return name.getMiddleName();
    }

}
