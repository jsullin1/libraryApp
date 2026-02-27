package model;

import java.time.Year;

/**
 * This class represents a Book object in the DB
 */
public class Book {
    private final int bookId;
    private final String ISBN;
    private final String title;
    private final Year year;
    private final int totalCopies;

    public Book(int id, String ISBN, String title, Year year, int totalCopies) {
        this.bookId = id;
        this.ISBN = ISBN;
        this.title = title;
        this.year = year;
        this.totalCopies = totalCopies;
    }


    public int getBookId() {
        return bookId;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public Year getYear() {
        return year;
    }

    public int getTotalCopies() {
        return totalCopies;
    }
}
