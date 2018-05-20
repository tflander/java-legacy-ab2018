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
    private String ssn;
    private Name name;

    private SocialSecurityNumberFormatter socialSecurityNumberFormatter = new SocialSecurityNumberFormatter();

    public void setName(Name name) {
        this.name = name;
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #setName(Name)}
     */
    @Deprecated
    public void setName(String firstName, String middleName, String lastName) {
        name = new DefaultNameBuilder().buildName(firstName, middleName, lastName);
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link #setName(Name)}
     */
    @Deprecated
    public void setSpanishName(String primerNombre, String segundoNombre,
                               String primerApellido, String segundoApellido) {
        name = new SpanishNameBuilder().buildName(primerNombre, segundoNombre, primerApellido, segundoApellido);
    }

    public String formatLastNameFirst() {
        return new DefaultNameBuilder().formatLastNameFirst(name);
    }

    /**
     * @deprecated As of release 2.0, replaced by {@link NameValidator#validateName(Name)}
     */
    @Deprecated
    public int validateName() {
        return new NameValidator().validateName(name);
    }

    public void setSsn(String ssn) {
        this.ssn = socialSecurityNumberFormatter.removeDashes(ssn);
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
        return new SocialSecurityNumberValidator().validate(this.ssn);
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

    public void add(String firstName,
                    String middleName,
                    String lastName,
                    String ssn,
                    String zipCode) throws URISyntaxException, IOException {
        setName(firstName, middleName, lastName);
        setSsn(ssn);
        address = addressProvider.buildAddressFromZipCode(zipCode);
        save();
    }

    public void save() {
        //TODO save information to a database
        System.out.println("Saving to database: " + formatLastNameFirst());
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

    public static void main(String[] args) throws URISyntaxException, IOException {
        JobApplicantApp.main(args);
    }

    protected String getFirstName() {
        return name.getFirstName();
    }

    protected String getLastName() {
        return name.getLastName();
    }

    protected String getMiddleName() {
        return name.getMiddleName();
    }

    protected String getSsn() {
        return ssn;
    }

    /**
     * @since 2.0
     * @return address for job applicant
     */
    public Address getAddress() {
        return address;
    }

}
