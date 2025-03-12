package org.example.demomongodb.repo;

import org.example.demomongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
}
