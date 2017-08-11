package com.example.vipavee.testsqlite.model;

import android.provider.BaseColumns;

/**
 * Created by Vipavee on 09/08/2017.
 */

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String tel;
    private String email;
    private String description;
    //Database
    public static final String DATABASE_NAME = "vipavee_employee.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "employee";
    //Contructor

    public Employee() {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
        this.description = description;

    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }




    public class Column {
            public static final String ID = BaseColumns._ID;
            public static final String FIRST_NAME = "first_name";
            public static final String LAST_NAME = "last_name";
            public static final String TEL = "tel";
            public static final String EMAIL = "email";
            public static final String DESCRIPTION = "description";
        }


}


