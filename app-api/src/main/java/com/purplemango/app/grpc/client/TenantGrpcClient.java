package com.purplemango.app.grpc.client;

import com.purplemango.app.grpc.server.TenantGrpcService;
import com.purplemango.app.proto.tenant.TenantGrpc;
import com.purplemango.app.proto.tenant.TenantId;
import com.purplemango.app.proto.tenant.TenantServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import static com.purplemango.app.grpc.client.GRPCServerTenantRequestUtils.*;

@Service
public class TenantGrpcClient {

    private final TenantServiceGrpc.TenantServiceBlockingStub blockingStub;

    public TenantGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        this.blockingStub = TenantServiceGrpc.newBlockingStub(channel);
    }


    public void readAllTenant() {
//        tenantClient.getTenantList(buildEmptyResponse()).forEachRemaining(response -> printUserDetails(response.getUser(), "getAllUsers call"));
    }

    public TenantGrpc readTenantById(String tenantId) {
        TenantId request = buildTenantId(tenantId);
        TenantGrpc tenant = blockingStub.getTenantById(request).getTenant();
        printTenantDetails(tenant, "readTenantById call");
        return tenant;
    }
}
