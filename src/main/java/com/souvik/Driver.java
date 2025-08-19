package com.souvik;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        List<Book> books = DatasetReader.readDataset("data.csv");
        BookService bookService = new BookService(books);
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 6) {
            System.out.println("\n---Menu ---");
            System.out.println("1. Total number of books by an author");
            System.out.println("2. See all authors in the dataset");
            System.out.println("3. Find all books by an author");
            System.out.println("4. Find all books with a specific user rating");
            System.out.println("5. Get the price of all books by an author");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter author's name: ");
                        String authorName = scanner.nextLine();
                        long count = bookService.countBooksByAuthor(authorName);
                        System.out.println("Author " + authorName + " has " + count + " books");
                        break;
                    case 2:
                        System.out.println("Authors in the dataset:");
                        List<String> authors = bookService.getDistinctAuthors();
                        authors.forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Enter author's name: ");
                        String aName = scanner.nextLine();
                        System.out.println("Books by " + aName + ":");
                        List<String> titles = bookService.findBookTitlesByAuthor(aName);
                        if (titles.isEmpty()) {
                            System.out.println("No books with author name: " + aName + " found");
                        } else {
                            titles.forEach(System.out::println);
                        }
                        break;
                    case 4:
                        System.out.println("Enter rating: ");
                        double rating = scanner.nextDouble();
                        List<Book> ratedBooks = bookService.findBooksByRating(rating);
                        if (ratedBooks.isEmpty()) {
                            System.out.println("No books found with " + rating + " rating.");
                        } else {
                            System.out.println("Books with " + rating + " rating:");
                            ratedBooks.forEach(Book::printDetails);
                        }
                        break;
                    case 5:
                        System.out.println("Enter author's name: ");
                        String authName = scanner.nextLine();
                        System.out.println("Book by " + authName + " with their prices :");
                        Map<String, Double> prices = bookService.getBookPricesByAuthor(authName);
                        if (prices.isEmpty()) {
                            System.out.println("No books with author name: " + authName + " found");
                        } else {
                            prices.forEach((title, price) ->
                                    System.out.printf("%s : %.2f%n", title, price));
                        }
                        break;
                    case 6:
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6");
                scanner.next();
            }
        }
        scanner.close();
    }
}
