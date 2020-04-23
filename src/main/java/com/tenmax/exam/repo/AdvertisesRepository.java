package com.tenmax.exam.repo;

import com.tenmax.exam.model.Advertise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisesRepository extends MongoRepository<Advertise, String> {
    @Query("{title:{'$regex': ?0, '$options' : 'i'}}")
    public List<Advertise> findByTitle(String title);
}
