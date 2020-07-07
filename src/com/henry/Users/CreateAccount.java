package com.henry.Users;

import com.henry.DatabaseConnection.AccountDB;

public class CreateAccount extends AccountDB {

    public boolean addAccount(String username, String userpass, String userpassSure , String email, String phone) {
        if (!userpass.equals(userpassSure)) {
            System.out.println("Password don't match !");
            return false;
        }

        return createAccountDB(username, userpass, email, phone);
    }
}
