package net.fpeg.msa.auth.controller;

import net.fpeg.msa.auth.dto.UserInfoDto;
import net.fpeg.msa.auth.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserInfoDto getUserInfo() {
        return userInfoService.getUserInfo();
    }
}
