package com.henry.DatabaseConnection;

import com.henry.Home.Home;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDB extends DatabaseConnection {
    private final Home home = new Home();
    private int id = maxID();
    private String buyerID = "BID0" + id;

    public boolean createAccountDB(String username, String userpass, String email, String phone) {
        id++;
        buyerID += 1;
        var query = "INSERT INTO buyer VALUES (" + id + ",'" + buyerID + "', '" + username + "', '"
                + email + "', '" + userpass + "', '" + phone + "', '" + home.DateOfToday() + "')";

        return runQuery(query);
    }

    public String adminLogCheck(String username, String userpass) {
        var query = "SELECT user_name, email, a_password FROM admins WHERE (user_name = '"
                + username + "' OR email = '" + username + "') AND a_password = '" + userpass + "'";

        if (logCheck(query,username,userpass))
            return "Admin";

        return "Doesn't Exist !";
    }

    public String buyerLogCheck(String username, String userpass) {
        var query = "SELECT user_name, email, a_password FROM buyer WHERE (user_name = '"
                + username + "' OR email = '" + username + "') AND a_password = '" + userpass + "'";

        if (logCheck(query,username,userpass))
            return "Buyer";

        return "Doesn't Exist !";
    }

    public int returnID(String query) {
        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            if(resultSet.next()){
                int reID = resultSet.getInt("id");
                return reID;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return -1;
    }

    public String returnName(String email) {
        var query = "SELECT user_name FROM buyer WHERE email = '" + email + "'";

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            if(resultSet.next()){
                return resultSet.getString("user_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return null;
    }

    private int maxID() {
        var query = "SELECT MAX(id) as id FROM buyer";
        return returnID(query);
    }

    private boolean logCheck(String query, String username, String userpass) {
        String name = "", email = "", pass = "";

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            if (resultSet.next()) {
                name += resultSet.getString("user_name") + " ";
                pass += resultSet.getString("a_password") + " ";
                email += resultSet.getString("email") + " ";

            }
            return ((name.compareTo(username) > 0 || email.compareTo(username) > 0) && pass.compareTo(userpass) > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return false;
    }
}

