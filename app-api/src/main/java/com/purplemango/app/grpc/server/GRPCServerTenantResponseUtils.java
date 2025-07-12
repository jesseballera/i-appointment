package com.purplemango.app.grpc.server;

import com.purplemango.app.model.tenant.ViewTenant;
import com.purplemango.app.proto.tenant.TenantGrpc;
import com.purplemango.app.proto.tenant.TenantResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

public class GRPCServerTenantResponseUtils {
    protected static void sendStreamingResponse(StreamObserver<TenantResponse> responseObserver, List<ViewTenant> tenants) {
        tenants.iterator()
                .forEachRemaining(document -> responseObserver.onNext(buildTenantResponse(document)));

    }

    protected static void sendStreamingResponse(StreamObserver<TenantResponse> responseObserver, Page<ViewTenant> tenants) {
        tenants.iterator()
                .forEachRemaining(document -> responseObserver.onNext(buildTenantResponse(document)));

    }

    protected static TenantResponse buildTenantResponse(ViewTenant tenant) {
        return TenantResponse.newBuilder()
                .setTenant(documentToTenant(tenant))
                .build();
    }

    protected static TenantGrpc documentToTenant(ViewTenant tenant) {
        return TenantGrpc.newBuilder()
                .setId(tenant.id().toHexString())
                .setCompanyName(tenant.companyName())
                .setCompanyCode(tenant.companyCode())
                .build();

    }
}
