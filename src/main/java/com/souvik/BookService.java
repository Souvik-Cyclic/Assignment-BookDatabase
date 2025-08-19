package com.souvik;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookService {
    private final List<Book> books;

    public BookService(List<Book> books) {
        this.books = books;
    }

    public long countBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .count();
    }

    public List<String> getDistinctAuthors() {
        return books.stream()
                .map(Book::getAuthor)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> findBookTitlesByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByRating(double rating) {
        return books.stream()
                .filter(book -> book.getUserRating() == rating)
                .collect(Collectors.toList());
    }

    public Map<String, Double> getBookPricesByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toMap(Book::getTitle, Book::getPrice, (oldValue, newValue) -> newValue));
    }
}
