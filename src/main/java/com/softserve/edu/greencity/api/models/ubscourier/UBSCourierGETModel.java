package com.softserve.edu.greencity.api.models.ubscourier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UBSCourierGETModel {
    public String addressComment;
    public String email;
    public String firstName;
    public Long id;
    public String lastName;
    public String phoneNumber;

    public UBSCourierGETModel(){
       addressComment="";
       email="";
       firstName="";
       id=0L;
       lastName="";
       phoneNumber="";
    }

   @Override
   public String toString() {
      ObjectMapper mapper = new ObjectMapper();
      String objAsJson = null;
      try {
         objAsJson = mapper.writeValueAsString(this);
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      }
      return objAsJson;
   }


}
