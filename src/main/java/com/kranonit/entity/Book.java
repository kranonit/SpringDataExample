package com.kranonit.entity;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;

    private int numberOfPages;

    public Book(String title, int numberOfPages) {
        super();
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (numberOfPages != book.numberOfPages) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + numberOfPages;
        return result;
    }
}
