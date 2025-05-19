package com.purplemango.app.repository.tenants;

import com.purplemango.app.aop.operations.BeforeTenantMongoOperation;
import com.purplemango.app.config.MultiTenantMongoDBFactory;
import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.TenantUser;
import com.purplemango.app.repository.MongoBaseRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@BeforeTenantMongoOperation
public class TenantUserRepositoryImpl extends MongoBaseRepository<TenantUser> implements TenantUserRepository {

    public static final String COLLECTION_NAME = "users";
    public static final String DATABASE_NAME = "app-tenants";
    private final MongoTemplate mongoTemplate;

    public TenantUserRepositoryImpl(MongoTemplate mongoTemplate) {
        super.setDatabaseName(DATABASE_NAME);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Collection<TenantUser> findAllUserByTenant(String tenant) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        Query query = new Query(Criteria.where("tenant").is(tenant));
        return mongoTemplate.find(query, TenantUser.class, COLLECTION_NAME);
    }

    @Override
    public Page<TenantUser> findAll(String tenant, Pageable pageable) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        Query query = new Query().with(pageable).with(pageable.getSort());
        List<TenantUser> filteredTenantUsers = mongoTemplate.find(query, TenantUser.class, COLLECTION_NAME);
        return PageableExecutionUtils.getPage( filteredTenantUsers, pageable, () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), TenantUser.class));
    }

    @Override
    public TenantUser findByTenantUserById(String tenant, ObjectId id)    {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, TenantUser.class, COLLECTION_NAME);
    }

    @Override
    public TenantUser findByTenantIdAndUserId(String tenant, ObjectId userId) {
        return null;
    }

    @Override
    public TenantUser save(TenantUser entity) {
        String databaseName = String.format("%s-%s", this.getTargetName(), entity.tenant().companyName());
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return save(entity, COLLECTION_NAME);
    }
}
