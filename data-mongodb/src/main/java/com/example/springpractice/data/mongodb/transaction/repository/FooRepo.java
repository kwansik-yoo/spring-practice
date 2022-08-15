package com.example.springpractice.data.mongodb.transaction.repository;

import com.example.springpractice.data.mongodb.transaction.document.Foo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FooRepo extends MongoRepository<Foo, String> {
}
