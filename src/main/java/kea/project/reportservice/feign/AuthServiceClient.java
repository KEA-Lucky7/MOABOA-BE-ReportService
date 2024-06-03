package kea.project.reportservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-service", configuration = HeaderConfiguration.class)
public interface AuthServiceClient {

    @PostMapping("/token/server-validation")
    ResponseEntity<Long> validateToken();

}

