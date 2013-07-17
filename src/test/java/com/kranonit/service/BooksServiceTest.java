package com.kranonit.service;

import com.kranonit.entity.Book;
import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDbConfigurationBuilder.inMemoryMongoDb;

@Ignore
public class BooksServiceTest {

    @ClassRule
    public static InMemoryMongoDb inMemoryMongoDb = new InMemoryMongoDb();

    @Rule
    public MongoDbRule remoteMongoDbRule = new MongoDbRule(inMemoryMongoDb().databaseName("test").build());

    @Test
    @UsingDataSet(locations="initialData.json", loadStrategy= LoadStrategyEnum.CLEAN_INSERT)
    @ShouldMatchDataSet(location="expectedData.json")
    public void book_should_be_inserted_into_repository() {
        BookManager bookManager = new BookManager();
        Book book = new Book("The Lord Of The Rings", 1299);
        bookManager.create(book);
    }
}
