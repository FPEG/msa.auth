package net.fpeg.msa.auth.service;

import net.fpeg.msa.auth.dto.WordUserDto;
import org.springframework.stereotype.Service;

import static net.fpeg.msa.common.utils.MvcUtil.getUsername;

@Service
public class UserInfoService {
    public WordUserDto getWordUser(){
        String username = getUsername();
        return WordUserDto.builder().username(username).build();
    }
}
