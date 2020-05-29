package net.fpeg.msa.auth.service;

import net.fpeg.msa.auth.dto.UserInfoDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static net.fpeg.msa.common.utils.MvcUtil.getUsername;

@Service
public class UserInfoService {
    public UserInfoDto getUserInfo(){
        String username = getUsername();
        return UserInfoDto.builder().username(username).build();
    }
}
