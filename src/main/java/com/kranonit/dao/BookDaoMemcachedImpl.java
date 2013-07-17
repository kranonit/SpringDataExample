package com.kranonit.dao;

import com.kranonit.entity.Book;
import com.kranonit.utils.MongoInit;
import net.spy.memcached.MemcachedClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class BookDaoMemcachedImpl {
    private MongoOperations mongoOperations = MongoInit.getMongoOperations();

    public void save(Book book) {
        mongoOperations.save(book, "books");
    }

    public Book find(String title) {
        MemcachedClient c = null;
        Book bookFind;
        try {
            c = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookFind = (Book) c.get("SomeKeyFromFind");
        if (bookFind == null || StringUtils.isEmpty(bookFind.toString())) {
            bookFind = mongoOperations.findOne(new Query(where("title").is(title)), Book.class, "books");
            c.set("SomeKeyFromFind", 60, bookFind);
        }
        return bookFind;
    }

    public List<Book> findAll() {
        List<Book> books = mongoOperations.findAll(Book.class, "books");
        return books;
    }

    public Book memTest() throws IOException {
        MemcachedClient c = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
        Book book = new Book("test", 200);

        // Store a value (async) for one hour
        c.set("someKey", 60, book);
        // Retrieve a value (synchronously).
        Book myObject = (Book) c.get("someKey");
        return myObject;
    }
}
