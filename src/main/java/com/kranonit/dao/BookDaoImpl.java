package com.kranonit.dao;

import com.kranonit.entity.Book;
import com.kranonit.utils.MongoInit;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class BookDaoImpl {
    private MongoOperations mongoOperations = MongoInit.getMongoOperations();

    public void save(Book book) {
        mongoOperations.save(book, "books");
    }

    public Book find(String title) {
        // find
       Book bookFind = mongoOperations.findOne(
                new Query(Criteria.where("title").is(title)), Book.class,
                "books");
        return bookFind;
    }

    public List<Book> findAll() {
        List<Book> books = mongoOperations.findAll(Book.class, "books");
        return books;
    }
}
