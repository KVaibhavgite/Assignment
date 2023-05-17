package com.example.assignment;



public class Member {
    private int id;
    private String EmployeeId;
    private String FirstName;
    private String LastName;
    private String MobileNo;
    //private String Address;

    public Member(String employeeId, String firstName, String lastName, String mobileNo) {
        this.id = id;
        EmployeeId = employeeId;
        FirstName = firstName;
        LastName = lastName;
        MobileNo = mobileNo;
        //Address = address;
    }

    public Member(int id, String employeeId, String firstName, String lastName, String mobileNo) {
        this.id = id;
        EmployeeId = employeeId;
        FirstName = firstName;
        LastName = lastName;
        MobileNo = mobileNo;
       // Address = address;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

//    public String getAddress() {
//        return Address;
//    }
//
//    public void setAddress(String address) {
//        Address = address;
//    }


}