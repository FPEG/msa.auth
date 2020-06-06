package net.fpeg.msa.auth.controller;

import net.fpeg.msa.auth.dto.WordUserDto;
import net.fpeg.msa.auth.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    final
    UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/info")
    public WordUserDto getWordUser() {
        return userInfoService.getWordUser();
    }
}
