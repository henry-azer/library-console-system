package com.henry.Author;

import com.henry.DatabaseConnection.AuthorDB;

public class Author extends AuthorDB {

    public void authors() {
        authorsDB();
    }

    public boolean addAuthor(String authorName) {
        return addAuthorDB(authorName);
    }

    public boolean getBookAuthor(int id) {
        return getBookAuthorDB(id);
    }

    public String getAuthor(int id) {
        return getAuthorDB(id);
    }
}