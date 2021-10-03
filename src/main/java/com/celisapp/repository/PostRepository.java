package com.celisapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.celisapp.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
