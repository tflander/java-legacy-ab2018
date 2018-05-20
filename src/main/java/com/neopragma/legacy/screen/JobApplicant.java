package com.neopragma.legacy.screen;

import com.neopragma.legacy.screen.support.*;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Job applicant class.
 */
public class JobApplicant {

    private Address address = new Address();
    private AddressProvider addressProvider = new AddressProvider();
    private DefaultNameBuilder defaultNameBuilder = new DefaultNameBuilder();
    private JobApplicantRepository jobApplicantRepository = new JobApplicantRepository();
    private SocialSecurityNumberValidator socialSecurityNumberValidator = new SocialSecurityNumberValidator();
    private NameValidator nameValidator = new NameValidator();
    private String ssn;
    private Name name;

    private SocialSecurityNumberFormatter socialSecurityNumberFormatter = new SocialSecurityNumberFormatter();
    SpanishNameBuilder spanishNameBuilder = new SpanishNameBuilder();

    public void setName(Name name) {
        this.name = name;
    }

    public void setSsn(String ssn) {
        this.ssn = socialSecurityNumberFormatter.removeDashes(ssn);
    }

    public void add(String firstName,
                    String middleName,
                    String lastName,
                    String ssn,
                    String zipCode) throws URISyntaxException, IOException {
        name = defaultNameBuilder.buildName(firstName, middleName, lastName);
        setSsn(ssn);
        address = addressProvider.buildAddressFromZipCode(zipCode);
        jobApplicantRepository.save(this);
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
    public Name getName() {
        return name;
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

    // TODO: fix and deprecate
    public String formatLastNameFirst() {
        return defaultNameBuilder.formatLastNameFirst(name);
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

    public void setZipCode(String zipCode) throws URISyntaxException, IOException {
        this.address = addressProvider.buildAddressFromZipCode(zipCode);
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
                ", addressProvider=" + addressProvider +
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
