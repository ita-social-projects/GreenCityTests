package com.softserve.edu.greencity.ui.tools.jdbc.entity;


import java.util.ArrayList;
import java.util.List;

enum UBSPersonalDataEntityFields {
    ID(0),
    EMAIL(1),
    FIRST_NAME(2),
    LAST_NAME(3),
    PHONE_NUMBER(4),
    USERS_ID(5),
    ADDRESS_ID(6);

    private int number;

    private UBSPersonalDataEntityFields(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

public class UBSPersonalDataEntity {

    public static final String SELECT_ALL = "SELECT * FROM ubs_user;";
    public static final String SELECT_BY_FIELD = "SELECT * FROM ubs_user WHERE %s='%s';";
    public static final String UPDATE_FIELD_BY_ID = "UPDATE ubs_user SET %s='%s' WHERE id='%s';";
    public static final String DELETE_BY_ID = "DELETE FROM ubs_user WHERE id=%s;";
    public static final String CREATE_USER = "";

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private long usersId;
    private long addressId;

    public UBSPersonalDataEntity() {
        email = "";
        firstName = "";
        lastName = "";
        phoneNumber = "";
        usersId = 0;
        addressId = 0;
    }

    public UBSPersonalDataEntity(String email, String firstName, String lastName, String phoneNumber, long usersId, long addressId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.usersId = usersId;
        this.addressId = addressId;
    }

    public long getId() {
        return id;
    }

    public UBSPersonalDataEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UBSPersonalDataEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UBSPersonalDataEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UBSPersonalDataEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UBSPersonalDataEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public long getUsersId() {
        return usersId;
    }

    public UBSPersonalDataEntity setUsersId(long usersId) {
        this.usersId = usersId;
        return this;
    }

    public long getAddressId() {
        return addressId;
    }

    public UBSPersonalDataEntity setAddressId(long addressId) {
        this.addressId = addressId;
        return this;
    }

    @Override
    public String toString() {
        return "UBSPersonalDataEntity ["
                + "id=" + id
                + ", email=" + email
                + ", first_name =" + firstName
                + ", last_name =" + lastName
                + ", phone_number =" + phoneNumber
                + ", users_id =" + usersId
                + ", address_id =" + addressId
                + "]";
    }

    public static UBSPersonalDataEntity getUBSPersonalDataEntity(List<String> row) {
        return new UBSPersonalDataEntity()
                .setId(Long.parseLong(row.get(UBSPersonalDataEntityFields.ID.getNumber())))
                .setEmail(row.get(UBSPersonalDataEntityFields.EMAIL.getNumber()))
                .setFirstName(row.get(UBSPersonalDataEntityFields.FIRST_NAME.getNumber()))
                .setLastName(row.get(UBSPersonalDataEntityFields.LAST_NAME.getNumber()))
                .setPhoneNumber(row.get(UBSPersonalDataEntityFields.PHONE_NUMBER.getNumber()))
                .setUsersId(Long.parseLong(row.get(UBSPersonalDataEntityFields.USERS_ID.getNumber())))
                .setAddressId(Long.parseLong(row.get(UBSPersonalDataEntityFields.ADDRESS_ID.getNumber())));
    }

    public static List<UBSPersonalDataEntity> getListUBSPersonalDataEntity(List<List<String>> rows) {
        List<UBSPersonalDataEntity> result = new ArrayList<>();
        for (List<String> currentRow : rows) {
            result.add(getUBSPersonalDataEntity(currentRow));
        }
        return result;
    }
}
