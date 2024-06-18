package com.home.api.clients;

import com.home.api.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "demo-cloud-user", url="${hostIp.cloud-user}")
public interface UserClient {

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") long id);
}
