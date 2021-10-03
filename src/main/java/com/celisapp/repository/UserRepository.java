package com.celisapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.celisapp.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
