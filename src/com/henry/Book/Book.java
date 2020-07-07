package com.henry.Book;

import com.henry.DatabaseConnection.BookDB;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Book extends BookDB {
    public int id;
    public int price;
    public String name;
    public int authorID;
    public int categoryID;
    public long serialNumber;

    public boolean addBook(String name, int authorID, int categoryID, long serialNumber, int price) {
        this.name = name;
        this.authorID = authorID;
        this.categoryID = categoryID;
        this.serialNumber = serialNumber;
        this.price = price;

        return addBookDB(name, authorID, categoryID, serialNumber, price);
    }

    public boolean getBookID(int bookID) {
        return getBookIDDB(bookID);
    }

    public void allBooks() {
        allBooksDB();
    }

    public boolean buyBook(int bookID) {
        this.id = bookID;

        if (bookCopies(bookID) == 0)
            return false;

        return buyBookDB(bookID);
    }

    public boolean borrowBook(byte bookID) {
        this.id = bookID;

        if (bookCopies(bookID) == 0)
            return false;

        return borrowBookDB(bookID);
    }

    public boolean returnBorrowBook(byte bookID) {
        this.id = bookID;

        return returnBorrowBookDB(bookID);
    }

    public boolean recentlyAdded(int dayBefore) {
        return recentlyAddedDB(date(dayBefore));
    }


    public boolean getBookName(String bookName) {
        this.name = bookName;
        return getBookNameDB(bookName);
    }

    public int bookCopies(int bookID) {
        this.id = bookID;
        return bookCopiesDB(bookID);
    }

    private String date(int dayBefore) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now.minusDays(dayBefore));
    }
}
