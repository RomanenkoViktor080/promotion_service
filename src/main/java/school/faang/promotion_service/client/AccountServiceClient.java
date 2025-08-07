package school.faang.promotion_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "account-service", url = "${account-service.host}:${account-service.port}/api/v1")
public interface AccountServiceClient {
    @GetMapping("/accounts/{id}/balance")
    Long getAccountBalance(@PathVariable("id") long id);
}
