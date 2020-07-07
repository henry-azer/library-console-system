package com.henry.Users;

import com.henry.DatabaseConnection.AccountDB;

public class Admin extends AccountDB {
    private String username;
    private String password;

    public boolean isAdmin(String username, String userpass) {
        return adminLogCheck(username, userpass).equals("Admin");
    }
}
