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

    public void setName(String firstName, String middleName, String lastName) {
        name = new DefaultNameBuilder().setName(firstName, middleName, lastName);
    }

    public void setSpanishName(String primerNombre, String segundoNombre,
                               String primerApellido, String segundoApellido) {
        String lastName;
        if (primerApellido != null) {
            lastName = primerApellido + (segundoApellido == null ? null : " " + segundoApellido);
        } else {
            lastName = "";
        }

        name = Name.builder()
                .firstName(primerNombre == null ? "" : primerNombre)
                .middleName(segundoNombre == null ? "" : segundoNombre)
                .lastName(lastName)
                .build();

    }

    public String formatLastNameFirst() {
        return new DefaultNameBuilder().formatLastNameFirst(name);
    }

    public int validateName() {
        return new DefaultNameBuilder().validateName(name);
    }

    public void setSsn(String ssn) {
        if (ssn.matches("(\\d{3}-\\d{2}-\\d{4}|\\d{9})")) {
            this.ssn = ssn.replaceAll("-", "");
        } else {
            this.ssn = "";
        }
    }

    public String formatSsn() {
        return ssn.substring(0, 3) + "-" +
                ssn.substring(3, 5) +
                "-" +
                ssn.substring(5);
    }

    /**
     *  * @deprecated  As of release 2.0, replaced by TODO Create Link Annotation

     * @return
     */
    @Deprecated
    public int validateSsn() {
        return new SocialSecurityNumberValidator().validate(this.ssn);
    }

    public void setZipCode(String zipCode) throws URISyntaxException, IOException {
        this.address = addressProvider.buildAddressFromZipCode(zipCode);
    }

    public String getCity() {
        return address.getCity();
    }

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

    protected Address buildAddressFromZipCode(String zipCode) throws IOException, URISyntaxException {
        return addressProvider.buildAddressFromZipCode(zipCode);
    }

    protected void setAddress(Address address) {
        this.address = address;
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

    protected Address getAddressForTesting() {
        return address;
    }
}
