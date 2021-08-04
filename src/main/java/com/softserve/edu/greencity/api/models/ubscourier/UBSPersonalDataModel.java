package com.softserve.edu.greencity.api.models.ubscourier;
import java.util.Objects;

public class UBSPersonalDataModel {

    public UBSUsersModel usersId;
    public UBSAddressModel addressId;
    public Long id;
    public String email;
    public String firstName;
    public String lastName;
    public String phoneNumber;


    public UBSPersonalDataModel(){
        usersId=null;
        addressId=null;
        id=0L;
        email="";
        firstName="";
        lastName="";
        phoneNumber="";

    }

 /*   @Override
    public boolean equals(Object obj) {
        if (this == obj){ return true;}

        if (!(obj instanceof UBSPersonalDataModel)) {
            return false;
        }
        UBSPersonalDataModel that = (UBSUsersModel) obj;
        return usersId.equals( that.usersId) && Objects.equals(addressId, that.addressId)
                && Objects.equals(id, that.id) && Objects.equals(email, that.email)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName) && Objects.equals(phoneNumber, that.phoneNumber);
    }
*/
}
