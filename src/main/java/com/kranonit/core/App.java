package com.kranonit.core;

import com.kranonit.config.SpringMongoConfig;
import com.kranonit.dao.BookDaoMemcachedImpl;
import com.kranonit.entity.Book;
import com.kranonit.entity.User;
import com.kranonit.service.BookManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.IOException;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class App {

    public static void main(String[] args) {
        // For XML
        // ApplicationContext ctx = new
        // GenericXmlApplicationContext("mongo-config.xml");

        // For Annotation
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
        User user = new User("kranonit", "password123");
        // save
        mongoOperation.save(user, "users");
        // find
        User savedUser = mongoOperation.findOne(new Query(where("username").is("kranonit")), User.class, "users");
        System.out.println("savedUser : " + savedUser);
        // update
        mongoOperation.updateMulti(new Query(where("username").is("kranonit")), Update.update("password", "new password"), "users");
        // find
        User updatedUser = mongoOperation.findOne(new Query(where("username").is("kranonit")), User.class, "users");
        System.out.println("updatedUser : " + updatedUser);
        // delete
        mongoOperation.remove(new Query(where("username").is("kranonit")), "users");
        // List
        List<User> listUser = mongoOperation.findAll(User.class, "users");
        System.out.println("Number of user = " + listUser.size());

        //Create new book
        BookManager bookManager = new BookManager();
        Book book = new Book("some book", 321);
        System.out.println("Book " + bookManager.create(book) + "was added successful");
        System.out.println("Book " + bookManager.createWithMemcache(book) + "was added successful to memcache");

        BookDaoMemcachedImpl bookDaoMemcached = new BookDaoMemcachedImpl();
        try {
            System.out.println("book memTest" + bookDaoMemcached.memTest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
