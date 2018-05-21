package com.example.acorona.test;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 *
 * @author acorona
 */
public class Customer implements Parcelable {
    private Integer customerNumber;

    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Integer salesRepEmployeeNumber;
    private double creditLimit;

    public Customer() {
    }

    public Customer(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Customer(Integer customerNumber, String customerName, String phone, double creditLimit) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.phone = phone;
        this.creditLimit = creditLimit;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSalesRepEmployeeNumber() {
        return salesRepEmployeeNumber;
    }

    public void setSalesRepEmployeeNumber(Integer salesRepEmployeeNumber) {
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerNumber != null ? customerNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerNumber == null && other.customerNumber != null) || (this.customerNumber != null && !this.customerNumber.equals(other.customerNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.newsoft.tallerservicio.entidades.Customers[ customerNumber=" + customerNumber + " ]";
    }


    protected Customer(Parcel in) {
        customerNumber = in.readByte() == 0x00 ? null : in.readInt();
        customerName = in.readString();
        contactLastName = in.readString();
        contactFirstName = in.readString();
        phone = in.readString();
        addressLine1 = in.readString();
        addressLine2 = in.readString();
        city = in.readString();
        state = in.readString();
        postalCode = in.readString();
        country = in.readString();
        salesRepEmployeeNumber = in.readByte() == 0x00 ? null : in.readInt();
        creditLimit = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (customerNumber == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(customerNumber);
        }
        dest.writeString(customerName);
        dest.writeString(contactLastName);
        dest.writeString(contactFirstName);
        dest.writeString(phone);
        dest.writeString(addressLine1);
        dest.writeString(addressLine2);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(postalCode);
        dest.writeString(country);
        if (salesRepEmployeeNumber == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(salesRepEmployeeNumber);
        }
        dest.writeDouble(creditLimit);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Customer> CREATOR = new Parcelable.Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };
}