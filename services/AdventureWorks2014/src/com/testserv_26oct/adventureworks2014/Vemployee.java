/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.testserv_26oct.adventureworks2014;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Vemployee generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`vEmployee`")
@IdClass(VemployeeId.class)
public class Vemployee implements Serializable {

    private Integer businessEntityId;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String jobTitle;
    private String phoneNumber;
    private String phoneNumberType;
    private String emailAddress;
    private Integer emailPromotion;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String stateProvinceName;
    private String postalCode;
    private String countryRegionName;
    private String additionalContactInfo;

    @Id
    @Column(name = "`BusinessEntityID`", nullable = false, scale = 0, precision = 10)
    public Integer getBusinessEntityId() {
        return this.businessEntityId;
    }

    public void setBusinessEntityId(Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    @Id
    @Column(name = "`Title`", nullable = true, length = 255)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @Column(name = "`FirstName`", nullable = false, length = 255)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Id
    @Column(name = "`MiddleName`", nullable = true, length = 255)
    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Id
    @Column(name = "`LastName`", nullable = false, length = 255)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Id
    @Column(name = "`Suffix`", nullable = true, length = 255)
    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Id
    @Column(name = "`JobTitle`", nullable = false, length = 255)
    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Id
    @Column(name = "`PhoneNumber`", nullable = true, length = 255)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Id
    @Column(name = "`PhoneNumberType`", nullable = true, length = 255)
    public String getPhoneNumberType() {
        return this.phoneNumberType;
    }

    public void setPhoneNumberType(String phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }

    @Id
    @Column(name = "`EmailAddress`", nullable = true, length = 255)
    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Id
    @Column(name = "`EmailPromotion`", nullable = false, scale = 0, precision = 10)
    public Integer getEmailPromotion() {
        return this.emailPromotion;
    }

    public void setEmailPromotion(Integer emailPromotion) {
        this.emailPromotion = emailPromotion;
    }

    @Id
    @Column(name = "`AddressLine1`", nullable = false, length = 255)
    public String getAddressLine1() {
        return this.addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Id
    @Column(name = "`AddressLine2`", nullable = true, length = 255)
    public String getAddressLine2() {
        return this.addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Id
    @Column(name = "`City`", nullable = false, length = 255)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Id
    @Column(name = "`StateProvinceName`", nullable = false, length = 255)
    public String getStateProvinceName() {
        return this.stateProvinceName;
    }

    public void setStateProvinceName(String stateProvinceName) {
        this.stateProvinceName = stateProvinceName;
    }

    @Id
    @Column(name = "`PostalCode`", nullable = false, length = 255)
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Id
    @Column(name = "`CountryRegionName`", nullable = false, length = 255)
    public String getCountryRegionName() {
        return this.countryRegionName;
    }

    public void setCountryRegionName(String countryRegionName) {
        this.countryRegionName = countryRegionName;
    }

    @Id
    @Column(name = "`AdditionalContactInfo`", nullable = true, length = 255)
    public String getAdditionalContactInfo() {
        return this.additionalContactInfo;
    }

    public void setAdditionalContactInfo(String additionalContactInfo) {
        this.additionalContactInfo = additionalContactInfo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vemployee)) return false;
        final Vemployee vemployee = (Vemployee) o;
        return Objects.equals(getBusinessEntityId(), vemployee.getBusinessEntityId()) &&
                Objects.equals(getTitle(), vemployee.getTitle()) &&
                Objects.equals(getFirstName(), vemployee.getFirstName()) &&
                Objects.equals(getMiddleName(), vemployee.getMiddleName()) &&
                Objects.equals(getLastName(), vemployee.getLastName()) &&
                Objects.equals(getSuffix(), vemployee.getSuffix()) &&
                Objects.equals(getJobTitle(), vemployee.getJobTitle()) &&
                Objects.equals(getPhoneNumber(), vemployee.getPhoneNumber()) &&
                Objects.equals(getPhoneNumberType(), vemployee.getPhoneNumberType()) &&
                Objects.equals(getEmailAddress(), vemployee.getEmailAddress()) &&
                Objects.equals(getEmailPromotion(), vemployee.getEmailPromotion()) &&
                Objects.equals(getAddressLine1(), vemployee.getAddressLine1()) &&
                Objects.equals(getAddressLine2(), vemployee.getAddressLine2()) &&
                Objects.equals(getCity(), vemployee.getCity()) &&
                Objects.equals(getStateProvinceName(), vemployee.getStateProvinceName()) &&
                Objects.equals(getPostalCode(), vemployee.getPostalCode()) &&
                Objects.equals(getCountryRegionName(), vemployee.getCountryRegionName()) &&
                Objects.equals(getAdditionalContactInfo(), vemployee.getAdditionalContactInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBusinessEntityId(),
                getTitle(),
                getFirstName(),
                getMiddleName(),
                getLastName(),
                getSuffix(),
                getJobTitle(),
                getPhoneNumber(),
                getPhoneNumberType(),
                getEmailAddress(),
                getEmailPromotion(),
                getAddressLine1(),
                getAddressLine2(),
                getCity(),
                getStateProvinceName(),
                getPostalCode(),
                getCountryRegionName(),
                getAdditionalContactInfo());
    }
}

