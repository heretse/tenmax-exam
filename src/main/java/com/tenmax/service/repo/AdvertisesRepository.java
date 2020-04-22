package com.tenmax.service.repo;

import com.tenmax.service.model.Advertise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisesRepository extends MongoRepository<Advertise, String> {
    @Query("{title:'?0'}")
    public List<Advertise> findByTitle(String title);
}
