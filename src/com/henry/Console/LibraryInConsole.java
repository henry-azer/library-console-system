package com.henry.Console;

import com.henry.Home.AboutUs;
import com.henry.Users.CreateAccount;
import com.henry.Category.Categories;
import com.henry.Author.Author;
import com.henry.Users.LogIn;
import com.henry.Home.Home;
import com.henry.Book.Book;

import java.sql.SQLException;
import java.util.Scanner;

public class LibraryInConsole {
    private static final CreateAccount createAccount = new CreateAccount();
    private static final Scanner scanner = new Scanner(System.in);
    private static final Categories category = new Categories();
    private static final AboutUs aboutUs = new AboutUs();
    private static final Author author = new Author();
    private static final LogIn logIn = new LogIn();
    private static final Home home = new Home();
    private static final Book book = new Book();

    protected static void LibrarySystemConsole() throws SQLException {
        logInPage();
    }

////////////////////// Log IN //////////////////////////////////

    static void logInPage() throws SQLException {
        System.out.println("\n\tLibrary Management System\n  ----------------------------");
        System.out.println("\t" + home.DateOfToday());

        System.out.print("1- I have account\n2- Create new account\nChoose: ");
        int acc = scanner.nextByte();

        if (acc == 1) {
            login();
            homePage();
        }
        else if (acc == 2) {
            createAccount();
            homePage();
        }
        else {
            System.out.println("\nInvalid input !");
            logInPage();
        }
    }

    private static void login() {
        System.out.println("\n\t\tLog In\n\t----------------");
        scanner.useDelimiter("\n");

        System.out.print("Email: ");

        String username = scanner.next();

        System.out.print("Password: ");
        String userPassword = scanner.next();

        while (!captcha()) {
            System.out.println("\nWrong captcha !");
            captcha();
        }

        if(!logIn.userLogIn(username,userPassword)) {
            login();
        }
    }

////////////////////// Create Account //////////////////////////////////

    private static void createAccount() {
        System.out.println("\n\t\tCreate Account\n\t-------------------");
        scanner.useDelimiter("\n");


        System.out.print("User name: ");
        String username = scanner.next();

        System.out.print("Password: ");
        String userPassword = scanner.next();

        System.out.print("Password Again: ");
        String userPassAgain = scanner.next();

        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Phone: ");
        String phone = scanner.next();

        while (!captcha()) {
            System.out.println("\nWrong captcha !");
            captcha();
        }

        if (createAccount.addAccount(username, userPassword, userPassAgain, email, phone)) {
            System.out.println("\nAccount Created Successfully.");
            login();
        } else {
            System.out.println("\nInvalid input !");
            createAccount();
        }
    }

////////////////////// Captcha //////////////////////////////////

    private static boolean captcha() {
        long captchaR = home.captcha();
        System.out.print(captchaR + "\nRetype captcha: ");
        long captcha = scanner.nextLong();

        return captcha == captchaR;
    }

////////////////////// Home Page //////////////////////////////////

    static void homePage() throws SQLException {
        System.out.println("\n\t\tLibrary Home\n\t-------------------");
        System.out.println("Menu List");
        System.out.print("1- Books\n2- Categories\n3- Authors" +
                "\n4- Recently Added\n5- About Us\n\nChoose: ");
        byte menu = scanner.nextByte();

        if (menu == 1) {
            books();
        } else if (menu == 2) {
            categories();
        } else if (menu == 3) {
            authors();
        } else if (menu == 4) {
            recentlyAdded();
        } else if (menu == 5) {
            aboutUs();
        } else {
            System.out.println("\nInvalid input !");
            homePage();
        }
    }

////////////////////// Books ///////////////////////////////////////////

    private static void books() throws SQLException {
        System.out.println("\n\t\tBooks\n\t-----------------");
        System.out.print("1-All books\n2-Book by name\n3-Add new book\n\nChoose: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            allBooks();
        } else if (choice == 2) {
            getBookName();
        }
        else if (choice == 3)
            addNewBook();

        else {
            System.out.println("\nInvalid input !");
            books();
        }
    }

    private static void getBookName() throws SQLException {
        System.out.println("\n\t Search in Books\n  -----------------");
        scanner.useDelimiter("\n");

        System.out.print("\nBook Name: ");
        String bookName = scanner.next();

        if (!book.getBookName(bookName)) {
            System.out.println("\nBook does not exist !\nOr invalid input !");
            getBookName();
        } else {
            System.out.println("\nYou are lucky It exists !\nGo and get it.");
            bookPurchase();
        }
    }

    private static void allBooks() throws SQLException {
        System.out.println("\n\t All Books\n  ---------------");
        book.allBooks();
        getBookID();
    }

    private static void getBookID() throws SQLException {
        scanner.useDelimiter("\n");

        System.out.print("\nBook ID: ");
        byte bookID = scanner.nextByte();

        if (!book.getBookID(bookID)) {
            System.out.println("\nBook does not exist !\nOr invalid input !");
            getBookID();
        } else {
            System.out.println("\nYou are lucky It exists !\nGo and get it.");
            bookPurchase();
        }
    }

    private static void addNewBook() throws SQLException {
        System.out.println("\n\t\tAdd New Book\n\t----------------");
        scanner.useDelimiter("\n");

        System.out.print("Book Name: ");
        String name = scanner.next();

        System.out.print("Author ID: ");
        int authorID = scanner.nextByte();

        System.out.print("Category ID: ");
        int categoryID = scanner.nextByte();

        System.out.print("Serial Number: ");
        long serialNumber = scanner.nextLong();

        System.out.print("Price: ");
        int price = scanner.nextInt();

        if(book.addBook(name,authorID,categoryID,serialNumber,price)) {
            System.out.println("\nAdded successfully !");
            books();
        }
        else {
            System.out.println("\nInvalid inputs !");
            addNewBook();
        }
    }

////////////////////// Book Purchase ///////////////////////////////////////////

    private static void bookPurchase() throws SQLException {
        System.out.println("\n\t Book Purchase\n  ------------------");
        System.out.print("Book ID: ");
        byte bookID = scanner.nextByte();

        System.out.print("1- Buy\n2- Borrow\n3- Return\n\nChoose: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            if (book.buyBook(bookID)) {
                System.out.println("Here you are. enjoy !");
                back();
            } else {
                System.out.println("Invalid input !\nOr All copies is sale.");
                bookPurchase();
            }
        }else if (choice == 2) {
            if (book.borrowBook(bookID)) {
                System.out.println("It's yours ! enjoy. \nYou must get it back in 7 days.");
                back();
            } else {
                System.out.println("Invalid input !\nOr All copies is borrowed.");
                bookPurchase();
            }
        }else  if (choice == 3) {
            if (book.returnBorrowBook(bookID)) {
                System.out.println("Thank you for return !\n We hope you enjoyed the book.");
                back();
            }  else {
                System.out.println("Invalid input !");
                bookPurchase();
            }
        } else {
            System.out.println("Invalid input !");
            bookPurchase();
        }

    }

////////////////////// Categories //////////////////////////////////

    private static void categories() throws SQLException {
        System.out.println("\n\t\tCategories\n\t-----------------");
        System.out.print("1- View Categories\n2- Add category\nChoose: ");
        byte choice = scanner.nextByte();

        if (choice == 1) {
            System.out.println();
            category.categories();
            bookOFCategory();
        }else if (choice == 2) {
            addCategory();
        } else {
            System.out.println("\nInvalid input !");
            categories();
        }
    }

    private static void addCategory() throws SQLException {
        System.out.println("\n\t\tAdd Category\n\t-----------------");
        System.out.print("Category Name: ");

        scanner.useDelimiter("\n");
        String categoryName = scanner.next();

        if (category.addCategory(categoryName)) {
            System.out.println("Category Added Successfully !");
            back();
        }  else {
            System.out.println("\nInvalid input !");
            addCategory();
        }
    }

    private static void bookOFCategory() throws SQLException {
        System.out.print("\nChoose: ");
        byte categoryNum = scanner.nextByte();

        if (category.getBookCategory(categoryNum)) {
            System.out.print("\n1- Buy\n2- Back\nChoose: ");
            byte choice = scanner.nextByte();

            if (choice == 1) {
                allBooks();
                bookPurchase();
            }else if (choice == 2) {
                back();
            } else {
                System.out.println("\nInvalid input !");
                bookOFCategory();
            }
        } else {
            System.out.println("There are no books for this category yet.");
            back();
        }
    }

////////////////////// Authors ////////////////////////////////////////

    private static void authors() throws SQLException {
        System.out.println("\n\t\tAuthors\n\t-----------------");
        System.out.print("1- View Authors \n2- Add New author\n\nChoose: ");
        byte choice = scanner.nextByte();

        if (choice == 1)
            viewAuthors();
        else if (choice == 2)
            addAuthor();
        else
            System.out.println("\nInvalid input !");
    }

    private static void viewAuthors() throws SQLException {
        System.out.println("\n\t\tAll Authors\n\t-----------------");
        author.authors();

        System.out.print("\nChoose: ");
        byte authorNum = scanner.nextByte();

        if(author.getBookAuthor(authorNum)) {
            System.out.println("\nThese are his books.\nGo and get yours !");
            bookPurchase();
        } else {
            System.out.println("\nInvalid inputs !\nOr This Author hasn't books yet.");
            back();
        }
    }

    private static void addAuthor() throws SQLException {
        System.out.println("\n\t\tAdd New Author\n\t-----------------");
        scanner.useDelimiter("\n");

        System.out.print("Author name: ");
        String authorName = scanner.next();


        if(author.addAuthor(authorName)) {
            System.out.println("\nAdded successfully !");
            authors();
        }
        else {
            System.out.println("\nInvalid inputs !");
            addAuthor();
        }
    }

////////////////////// Recently Added //////////////////////////////////

    private static void recentlyAdded() throws SQLException {
        System.out.println("\n\t\tRecently Added\n\t----------------------");
        if (book.recentlyAdded(10)) {
            System.out.println("\nThis our new Books.\nget one now !");
            bookPurchase();
        } else {
            System.out.println("\nNo new Books this week !\nCome back soon.");
            back();
        }
    }

////////////////////// About Us //////////////////////////////////

    private static void aboutUs() throws SQLException {
        System.out.println(aboutUs.AboutUs());
        back();
    }

////////////////////// Back //////////////////////////////////////////////////

    private static void back() throws SQLException {
        System.out.print("\n\t\tAnything else\n\t ------------------ \n(Y or N): ");
        String choice = scanner.next().toUpperCase();

        if (choice.equals("Y"))
            homePage();
        else if (choice.equals("N")) {
            System.out.println("\n\t\tClosing\n\t----------------");
            System.out.println("Thank you for using our service !\nWe hope we have helped you.");
        } else {
            System.out.print("\nInvalid input !");
            back();
        }
    }
}