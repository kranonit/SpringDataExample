package com.kranonit.service;

import com.kranonit.dao.BookDaoImpl;
import com.kranonit.dao.BookDaoMemcachedImpl;
import com.kranonit.entity.Book;

public class BookManager {


    private BookDaoImpl bookDao = new BookDaoImpl();
    private BookDaoMemcachedImpl bookDaoMemcached = new BookDaoMemcachedImpl();

    public BookManager() {
    }

    public Book create(Book book) {
        bookDao.save(book);
        return bookDao.find(book.getTitle());
    }

    public Book createWithMemcache(Book book) {
        bookDaoMemcached.save(book);
        return bookDaoMemcached.find(book.getTitle());
    }



}
