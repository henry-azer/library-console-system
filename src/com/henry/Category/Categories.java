package com.henry.Category;

import com.henry.DatabaseConnection.CategoriesDB;

public class Categories extends CategoriesDB {

    public void categories() {
        categoriesDB();
    }

    public boolean getBookCategory(int id) {
        return getBookCategoryDB(id);
    }

    public String getCategory(int id) {
        return getCategoryDB(id);
    }

    public boolean addCategory(String category) {
        return addCategoryDB(category);
    }
}