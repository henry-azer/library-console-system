package com.henry.Users;

import com.henry.DatabaseConnection.AccountDB;

public class Buyer extends AccountDB {
    private String username;
    private String password;

    public boolean isBuyer(String username, String userpass) {
        return buyerLogCheck(username, userpass).equals("Buyer");
    }
}
