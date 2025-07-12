package com.purplemango.app.grpc.server;


import com.purplemango.app.exceptions.TenantNotFoundException;
import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.ViewTenant;
import com.purplemango.app.proto.core.Empty;
import com.purplemango.app.proto.core.PageInfo;
import com.purplemango.app.proto.tenant.*;
import com.purplemango.app.repository.tenants.TenantRepository;
import com.purplemango.app.service.tenants.TenantService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.purplemango.app.grpc.server.GRPCServerTenantResponseUtils.*;

@GrpcService
public class TenantGrpcService extends TenantServiceGrpc.TenantServiceImplBase {
    private final TenantRepository tenantRepository;

    @Autowired
    public TenantGrpcService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public void getTenantById(TenantId request, StreamObserver<TenantResponse> responseObserver) {
        ViewTenant tenant = ViewTenant
                .of(tenantRepository.findById(new ObjectId(request.getId())).orElseThrow(() -> new TenantNotFoundException("Tenant not found")));

        if (tenant != null) {
            TenantResponse tenantResponse = buildTenantResponse(tenant);
            responseObserver.onNext(tenantResponse);
        } else {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Tenant not found").asRuntimeException());
        }
        responseObserver.onCompleted();

    }

    @Override
    public void createTenant(CreateTenantRequest request, StreamObserver<TenantResponse> responseObserver) {
        super.createTenant(request, responseObserver);
    }

    @Override
    public void updateTenant(UpdateTenantRequest request, StreamObserver<TenantResponse> responseObserver) {
        super.updateTenant(request, responseObserver);
    }

    @Override
    public void getAllTenants(PageInfo request, StreamObserver<PaginatedTenantResponse> responseObserver) {
        if (request == null)
            return;

        Page<Tenant> page = tenantRepository.findAll(PageRequest.of(request.getPageNumber(),
                request.getPageSize(),
                Sort.by(Sort.Direction.ASC, request.getSort()))
        );
//        sendStreamingResponse(responseObserver, page.map(ViewTenant::of));
    }

    @Override
    public void getTenantList(Empty request, StreamObserver<TenantResponse> responseObserver) {
        List<ViewTenant> tenants = tenantRepository.findAll().stream().map(ViewTenant::of).toList();
        sendStreamingResponse(responseObserver, tenants);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteTenant(TenantId request, StreamObserver<Empty> responseObserver) {

        super.deleteTenant(request, responseObserver);
    }
}
