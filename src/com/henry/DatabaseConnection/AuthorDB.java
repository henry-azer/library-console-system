package com.henry.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDB extends DatabaseConnection {
    private final AccountDB accountDB = new AccountDB();
    private int id = maxAuthorID();

    protected void authorsDB() {
        int authorID;
        String author;

        var query = "SELECT id, author_name FROM authors";
        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            while (resultSet.next()) {
                authorID =resultSet.getInt("id");
                author =resultSet.getString("author_name");
                System.out.println(authorID + "- " + author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    protected boolean addAuthorDB(String authorName) {
        id++;
        var query = "INSERT INTO authors Values (" + id + ", '" + authorName + "')";

        return runQuery(query);
    }

    protected boolean getBookAuthorDB(int authorID) {
        int i = 0;
        String book, author;

        var query = "SELECT b.book_name, a.author_name FROM books b" +
                " JOIN authors a ON b.author_id = a.id WHERE a.id = " + authorID;

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            while (resultSet.next()) {
                i++;
                author = resultSet.getString("a.author_name");
                book = i + "- " + resultSet.getString("b.book_name");
                System.out.println(book + " - " + author);
            }

            return resultSet.first();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return false;
    }

    protected String getAuthorDB(int id) {
        String category;
        var query = "SELECT author_name FROM authors WHERE id = " + id;

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            if (resultSet.next()) {
                category =resultSet.getString("author_name");
                return category;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return "\nDoesn't Exist !";
    }

    private int maxAuthorID() {
        var query = "SELECT MAX(id) as id FROM authors";
        return accountDB.returnID(query);
    }
}
