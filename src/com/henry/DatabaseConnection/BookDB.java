package com.henry.DatabaseConnection;

import com.henry.Author.Author;
import com.henry.Category.Categories;
import com.henry.Home.Home;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDB extends DatabaseConnection {
    private final AccountDB accountDB = new AccountDB();
    private final Categories categories = new Categories();
    private final Author authors = new Author();
    private final Home home = new Home();
    private int id = maxBookID();

    protected boolean addBookDB(String bookName, int authorID, int categoryID, long serialNumber, int price){
        id++;
        var query = "INSERT INTO books Values (" +
                id + ", '" + bookName + "', " + categoryID + ", " + authorID + ", " + serialNumber + ", "
                + price + ", '" + home.DateOfToday() + "');";

        return runQuery(query);
    }

    protected boolean getBookIDDB(int bookID) {
        var query = "SELECT id, book_name, category_id, author_id, book_serial_number, BookPrice, " +
                "release_date FROM books WHERE id = '" + bookID + "'" ;

        return getBookDB(query);
    }

    protected boolean getBookNameDB(String bookName) {
        var query = "SELECT id, book_name, category_id, author_id, book_serial_number, BookPrice, " +
                "release_date FROM books WHERE book_name = '" + bookName + "'" ;

        return getBookDB(query);
    }

    protected void allBooksDB() {
        int i = 0;
        String book;
        var query = "SELECT book_name FROM books";

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            while (resultSet.next()) {
                i++;
                book = i + "- " + resultSet.getString("book_name");
                System.out.println(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    protected boolean buyBookDB(int bookID) {
        var query = "UPDATE `library`.`books` SET `copies` = " + (bookCopiesDB(bookID) - 1) + " WHERE `id` = " + bookID;

        return runQuery(query);
    }

    protected boolean borrowBookDB(int bookID) {
        var query = "UPDATE `library`.`books` SET `copies` =" + (bookCopiesDB(bookID) + 1)
                + ", `borrow` =" + (bookBorrowCopiesDB(bookID) - 1) + " WHERE (`id` = " + bookID+ ")";

        return runQuery(query);
    }

    protected boolean returnBorrowBookDB(int bookID) {
        var query = "UPDATE `library`.`books` SET `copies` =" + (bookCopiesDB(bookID) + 1)
                + ", `borrow` = " + (bookBorrowCopiesDB(bookID) - 1) +" WHERE (`id` = " + bookID + ")";

        return runQuery(query);
    }


    protected boolean recentlyAddedDB(String date) {
        int i = 0;
        String book, releaseDate;
        var query = "SELECT book_name, release_date FROM books WHERE release_date > '" + date + "'";

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            while (resultSet.next()) {
                i++;
                book = i + "- " + resultSet.getString("book_name");
                releaseDate = " - " + resultSet.getString("release_date");
                System.out.println(book + releaseDate);
            }

            return resultSet.first();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return false;
    }

    private boolean getBookDB(String query) {
        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("book_name");
                int categoryID = resultSet.getInt("category_id");
                int authorID = resultSet.getInt("author_id");
                int serialNumber = resultSet.getInt("book_serial_number");
                int price = resultSet.getInt("bookPrice");
                String releaseDate = resultSet.getString("release_date");

                String  category = categories.getCategory(categoryID);
                String author = authors.getAuthor(authorID);

                System.out.println(
                        "\nName = " + name +
                                "\nBook ID = " + id +
                                "\nAuthor = " + author +
                                "\nCategory = " + category +
                                "\nPrice = " + price +
                                "\nSerial Number = " + serialNumber +
                                "\nRelease Date = " + releaseDate);

                return runQuery(query);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return false;
    }

    protected int bookCopiesDB(int id) {
        var query = "SELECT MAX(copies) as copies FROM books WHERE id = " + id;

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            if(resultSet.next()){
                return resultSet.getInt("copies");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return 0;
    }

    protected int bookBorrowCopiesDB(int id) {
        var query = "SELECT borrow FROM books WHERE id = " + id;

        try {
            ResultSet resultSet = connect().prepareStatement(query).executeQuery();

            if(resultSet.next()){
                return resultSet.getInt("borrow");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return 0;
    }

    private int maxBookID() {
        var query = "SELECT MAX(id) as id FROM books";
        return accountDB.returnID(query);
    }
}