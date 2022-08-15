package com.example.springpractice.data.mongodb.transaction.repository;

import com.example.springpractice.data.mongodb.transaction.document.Tx;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TxRepo extends MongoRepository<Tx, String> {
}
