package com.purplemango.app.repository.accommodations;

import com.purplemango.app.aop.operations.BeforeGlobalMongoOperation;
import com.purplemango.app.aop.operations.BeforeTenantMongoOperation;
import com.purplemango.app.config.MultiTenantMongoDBFactory;
import com.purplemango.app.model.accommodation.Accommodation;
import com.purplemango.app.repository.MongoBaseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@BeforeTenantMongoOperation
public class AccommodationRepositoryImpl extends MongoBaseRepository<Accommodation> implements AccommodationRepository {
    public static final String COLLECTION_NAME = "accommodations";
    private final MongoTemplate mongoTemplate;

    public AccommodationRepositoryImpl(MongoTemplate mongoTemplate,
            @Value("${spring.data.mongodb.database}") String databaseName) {
        super.setDatabaseName(databaseName);
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
