package com.souvik;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatasetReader {
    public static List<Book> readDataset(String filePath) {
        List<Book> bookList = new ArrayList<>();
        Set<String> seenBooks = new HashSet<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            csvReader.readNext();
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                if (values.length == 7) {
                    try {
                        String title = values[0];
                        String author = values[1];
                        String uniqueKey = title.toLowerCase() + "||" + author.toLowerCase();
                        if (!seenBooks.contains(uniqueKey)) {
                            seenBooks.add(uniqueKey);
                            double userRating = Double.parseDouble(values[2]);
                            long reviews = Long.parseLong(values[3]);
                            double price = Double.parseDouble(values[4]);
                            int year = Integer.parseInt(values[5]);
                            String genre = values[6];
                            bookList.add(new Book(title, author, userRating, reviews, price, year, genre));
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed line (number format issue): " + String.join(",", values));
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
