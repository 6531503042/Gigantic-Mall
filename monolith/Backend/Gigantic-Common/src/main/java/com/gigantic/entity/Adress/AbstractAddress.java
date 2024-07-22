package com.gigantic.entity.Adress;

import com.gigantic.Mapper.IdBasedEntity;

import javax.persistence.Column;

public class AbstractAddress extends IdBasedEntity {

    @Column(name = "name", length = 300, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 300, nullable = false)
    private String lastname;

    @Column(name = "phone_number", nullable = false)
    private int phoneNumber;

    @Column(name = "address_line1", length = 4086, nullable = false)
    private String addressLine1;

    @Column(name = "address_line2", length = 4086)
    private String addressLine2;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "city", nullable = false)
    private String city;


    //Constructor
    public AbstractAddress() {
        //Default Constructor
    }

    //Getter & Setter

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(Long id) {
        super.setId(id);
    }

    protected Long getId() {
        return super.getId();
    }
}
