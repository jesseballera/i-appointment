package com.purplemango.app.repository.accommodations;

import com.purplemango.app.aop.operations.BeforeGlobalMongoOperation;
import com.purplemango.app.config.MultiTenantMongoDBFactory;
import com.purplemango.app.model.accommodation.AccommodationType;
import com.purplemango.app.repository.MongoBaseRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@BeforeGlobalMongoOperation
public class AccommodationTypeRepositoryImpl extends MongoBaseRepository<AccommodationType> implements AccommodationTypeRepository {
    public static final String COLLECTION_NAME = "accommodation_types";
    private final MongoTemplate mongoTemplate;

    public AccommodationTypeRepositoryImpl(MongoTemplate mongoTemplate,
                                           @Value("${spring.data.mongodb.database}") String databaseName) {
        super.setDatabaseName(databaseName);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public AccommodationType save(AccommodationType entity) {
//        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(this.getTargetName());
        return save(entity, COLLECTION_NAME);
    }

    @Override
    public List<AccommodationType> findAll() {
        return mongoTemplate.findAll(AccommodationType.class, COLLECTION_NAME);
    }

    @Override
    public Page<AccommodationType> findAll(Pageable pageable) {
        Query query = new Query().with(pageable).with(pageable.getSort());
        List<AccommodationType> filteredTenants = mongoTemplate.find(query, AccommodationType.class, COLLECTION_NAME);
        return PageableExecutionUtils.getPage( filteredTenants, pageable, () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), AccommodationType.class));
    }

    @Override
    public Optional<AccommodationType> findById(ObjectId entityId) {
        Query query = new Query(Criteria.where("id").is(entityId));
        return Optional.ofNullable(findOneByQuery(query, AccommodationType.class, COLLECTION_NAME));
    }

    @Override
    public Optional<AccommodationType> findByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return Optional.ofNullable(findOneByQuery(query, AccommodationType.class, COLLECTION_NAME));
    }

    @Override
    public void deleteById(ObjectId id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, AccommodationType.class, COLLECTION_NAME);
    }
}
