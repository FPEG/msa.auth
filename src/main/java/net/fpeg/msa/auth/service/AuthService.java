package net.fpeg.msa.auth.service;

import net.fpeg.msa.common.dao.UserDao;
import net.fpeg.msa.common.eneity.User;
import net.fpeg.msa.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtil jwtUtil;

    public String login(String username, String password) {
        Optional<User> optionalUser = Optional.ofNullable(userDao.getUserByUsername(username));
        if (optionalUser.isPresent()) {
            if (!bCryptPasswordEncoder.matches(password, optionalUser.get().getPassword())) {
                throw new BadCredentialsException("密码错误");
            } else {
                //发签证
                return jwtUtil.generate(optionalUser.get());
            }
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }

}
