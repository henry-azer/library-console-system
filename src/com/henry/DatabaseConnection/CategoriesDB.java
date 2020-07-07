package com.henry.DatabaseConnection;

import java.sql.ResultSet;

public class CategoriesDB extends DatabaseConnection {

    protected void categoriesDB() {
        int categoryID;
        String category;
        var query = "SELECT id, category_name FROM category ORDER BY id";

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            while (resultSet.next()) {
                categoryID =resultSet.getInt("id");
                category =resultSet.getString("category_name");
                System.out.println(categoryID + "- " + category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    protected boolean getBookCategoryDB(int categoryID) {
        int i = 0;
        String book, category;
        var query = "SELECT b.book_name, c.category_name FROM books b" +
                " JOIN category c ON b.category_id = c.id WHERE c.id = " + categoryID;

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            while (resultSet.next()) {
                i++;
                category = resultSet.getString("c.category_name");
                book = i + "- " + resultSet.getString("b.book_name");
                System.out.println(book + " - " + category);
            }
            return resultSet.first();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return false;
    }

    protected String getCategoryDB(int id) {
        String category;
        var query = "SELECT category_name FROM category WHERE id = " + id;

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            if (resultSet.next()) {
                category =resultSet.getString("category_name");
                return category;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return "\nDoesn't Exist !";
    }

    protected boolean addCategoryDB(String category) {
        var query = "INSERT INTO `library`.`category` (`category_name`) VALUES ('" + category + "')";
        return runQuery(query);
    }
}
