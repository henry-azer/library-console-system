package com.henry.Users;

import com.henry.DatabaseConnection.AccountDB;

public class LogIn extends AccountDB {
    private final Buyer buyer = new Buyer();
    private final Admin admin = new Admin();

    public boolean userLogIn(String username, String password) {

        if (admin.isAdmin(username,password)) {
            System.out.println("\nlog in successfully.\nWelcome Admin " + username + " !");
            return true;
        }
        else if (buyer.isBuyer(username, password)) {
            System.out.println("\nlog in successfully.\nWelcome " + returnName(username) + " !");
            return true;
        }
        else {
            System.out.println("\nInvalid username or password !");
            return false;
        }
    }
}
