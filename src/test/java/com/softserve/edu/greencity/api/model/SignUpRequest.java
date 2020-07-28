package com.softserve.edu.greencity.api.model;

public class SignUpRequest {

        private String email;
        private String name;
        private String password;

        public String getEmail(){ return email;}
        public String getName(){
            return name;
        }
        public String getPassword(){
            return password;
        }

        public void setEmail(String email){
            this.email = email;
        }
        public void setName(String name){
            this.name = name;
        }
        public void setPassword(String password){
            this.password = password;
        }


    @Override
    public String toString() {
        return "{" + "\n" +
                "email=" + email +",\n" +
                "name=" + name + ",\n" +
                "pass=" + password + ",\n" +
                '}';
    }
}


