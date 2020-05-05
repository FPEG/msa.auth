package net.fpeg.msa.auth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("wordbase")
public interface FeignWordUserService {
    @PostMapping("/register/{userId}")
    void register(@PathVariable("userId") Long userId);
}
