package com.example.ecommerce;



import com.google.gson.annotations.SerializedName;

public class RegistrationDTO {

    @SerializedName("name")
    private String firstName;
//
//    @SerializedName("lastName")
//    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

//    @SerializedName("mobile")
//    private String mobile;
//
//    @SerializedName("address")
//    private String address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }


    public RegistrationDTO(String firstName, String email, String password) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

