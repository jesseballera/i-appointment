package com.purplemango.app.grpc.client;

import com.purplemango.app.proto.core.Empty;
import com.purplemango.app.proto.tenant.TenantGrpc;
import com.purplemango.app.proto.tenant.TenantId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GRPCServerTenantRequestUtils {

    static Empty buildEmptyResponse(){
        return Empty.newBuilder().build();
    }

    static TenantId buildTenantId(String tenantId) {
        return TenantId.newBuilder()
                .setId(tenantId)
                .build();
    }

    static void printTenantDetails(TenantGrpc tenant, String message) {
        log.info(message);
        log.info(tenant.toString());
        log.info("DONE");
    }
}
