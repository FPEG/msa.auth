package net.fpeg.msa.auth.controller;

import net.fpeg.msa.auth.exception.UserExistException;
import net.fpeg.msa.auth.service.AuthService;
import net.fpeg.msa.common.dto.BaseDto;
import net.fpeg.msa.common.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * Login base dto.
     *
     * @param loginDto the login dto
     * @return the base dto
     */
    @PostMapping("/login")
    public BaseDto login(@RequestBody LoginDto loginDto) {
        return new BaseDto(authService.login(loginDto.getUsername(), loginDto.getPassword()));
    }

    /**
     * Register.
     *
     * @param loginDto the login dto
     */
    @PostMapping("/register")
    public void register(@RequestBody LoginDto loginDto) throws UserExistException {
        authService.register(loginDto.getUsername(), loginDto.getPassword());
    }
}
