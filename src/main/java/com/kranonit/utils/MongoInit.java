package com.kranonit.utils;

import com.kranonit.config.SpringMongoConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

public class MongoInit {
    public static MongoOperations getMongoOperations() {
        // For Annotation
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        return mongoOperation;
    }
}
