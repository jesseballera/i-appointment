package com.purplemango.app.repository.accommodations;

import com.purplemango.app.aop.operations.BeforeGlobalMongoOperation;
import com.purplemango.app.config.MultiTenantMongoDBFactory;
import com.purplemango.app.model.accommodation.Accommodation;
import com.purplemango.app.repository.MongoBaseRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@BeforeGlobalMongoOperation
public class AccommodationRepositoryImpl extends MongoBaseRepository<Accommodation> implements AccommodationRepository {
    public static final String COLLECTION_NAME = "accommodations";
    public static final String DATABASE_NAME = "tenants";
    private final MongoTemplate mongoTemplate;

    public AccommodationRepositoryImpl(MongoTemplate mongoTemplate) {
        super.setDatabaseName(DATABASE_NAME);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Accommodation save(Accommodation accommodation) {
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(this.getTargetName());
        return mongoTemplate.save(accommodation, COLLECTION_NAME);
    }

    @Override
    public List<Accommodation> findAll(String tenant) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return mongoTemplate.findAll(Accommodation.class, COLLECTION_NAME);
    }
}
