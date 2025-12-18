package com.library_management.dao;

import com.library_management.config.MongoConfig;
import com.library_management.model.Book;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class BookCrudOperations {

    private final MongoCollection<Document> collection =
            MongoConfig.getDatabase().getCollection("books");

    public void addBook(Book book) {
        Document doc = new Document("bookId", book.bookId())
                .append("title", book.title())
                .append("author", book.author())
                .append("category", book.category())
                .append("availableStatus", book.availableStatus());

        collection.insertOne(doc);
        System.out.println("✅ Book added successfully");
    }

    public void getBookById(int bookId) {
        Document doc = collection.find(eq("bookId", bookId)).first();
        System.out.println(doc != null ? doc.toJson() : "❌ Book not found");
    }

    public void getAllBooks() {
        collection.find().forEach(doc -> System.out.println(doc.toJson()));
    }

    public void updateAvailability(int bookId, boolean status) {
        collection.updateOne(eq("bookId", bookId),
                new Document("$set", new Document("availableStatus", status)));
        System.out.println("🔄 Availability updated");
    }

    public void deleteBook(int bookId) {
        collection.deleteOne(eq("bookId", bookId));
        System.out.println("🗑️ Book deleted");
    }
}
