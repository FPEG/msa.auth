package net.fpeg.msa.auth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("wordbase")
@RequestMapping(headers = {"eyJ0eX=bWluOCIsImV4cCI6MTU5MTYxOTI5NywiaWF0IjoxNTkwNzU1M"})
public interface FeignWordUserService {
    @PostMapping("/register/{userId}")
    void register(@PathVariable("userId") Long userId);
}
