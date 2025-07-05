package com.purplemango.app.grpc.server;


import com.purplemango.app.model.tenant.ViewTenant;
import com.purplemango.app.proto.core.Empty;
import com.purplemango.app.proto.core.PageInfo;
import com.purplemango.app.proto.tenant.*;
import com.purplemango.app.service.tenants.TenantService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import static com.purplemango.app.grpc.server.GRPCServerTenantResponseUtils.*;

@GrpcService
public class TenantGrpcService extends TenantServiceGrpc.TenantServiceImplBase {
    private final TenantService tenantService;

    @Autowired
    public TenantGrpcService(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @Override
    public void getTenantById(TenantId request, StreamObserver<TenantResponse> responseObserver) {
        ViewTenant tenant = tenantService.getTenantById(new ObjectId(request.getId()));

        if (tenant != null) {
            TenantResponse tenantResponse = buildTenantResponse(tenant);
            responseObserver.onNext(tenantResponse);
        } else {
            responseObserver.onError(Status.NOT_FOUND.withDescription("User not found").asRuntimeException());
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
//        sendStreamingResponse(responseObserver, tenantService.getAllTenants());
    }

    @Override
    public void getTenantList(Empty request, StreamObserver<TenantResponse> responseObserver) {
        sendStreamingResponse(responseObserver, tenantService.getAllTenants().stream().toList());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteTenant(TenantId request, StreamObserver<Empty> responseObserver) {

        super.deleteTenant(request, responseObserver);
    }
}
