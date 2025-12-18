package com.library_management;

import com.library_management.dao.BookCrudOperations;
import com.library_management.model.Book;

import java.util.Scanner;

public class LibraryApp {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        BookCrudOperations dao = new BookCrudOperations();

        while (true) {
            System.out.println("""
            ============================
            1. Add Book
            2. Get Book By ID
            3. Get All Books
            4. Update Availability
            5. Delete Book
            0. Exit
            ============================
            """);

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Author: ");
                    String author = sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                    System.out.print("Available (true/false): ");
                    boolean status = sc.nextBoolean();

                    dao.addBook(new Book(id, title, author, category, status));
                }

                case 2 -> {
                    System.out.print("Book ID: ");
                    dao.getBookById(sc.nextInt());
                }

                case 3 -> dao.getAllBooks();

                case 4 -> {
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    System.out.print("Status (true/false): ");
                    dao.updateAvailability(id, sc.nextBoolean());
                }

                case 5 -> {
                    System.out.print("Book ID: ");
                    dao.deleteBook(sc.nextInt());
                }

                case 0 -> {
                    System.out.println("👋 Application terminated");
                    System.exit(0);
                }

                default -> System.out.println("❌ Invalid choice");
            }
        }
    }
}
