package com.tsystems.study.jamboree.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tsystems.study.jamboree.model.Event;

@Repository
public interface  EventRepository extends MongoRepository<Event, String> {
}
