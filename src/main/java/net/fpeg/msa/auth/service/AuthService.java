package net.fpeg.msa.auth.service;

import net.fpeg.msa.auth.exception.UserExistException;
import net.fpeg.msa.common.dao.AuthorityDao;
import net.fpeg.msa.common.dao.UserDao;
import net.fpeg.msa.common.entity.Authority;
import net.fpeg.msa.common.entity.User;
import net.fpeg.msa.common.utils.JwtUtil;
import net.fpeg.msa.common.utils.MvcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserDao userDao;

    @Autowired
    AuthorityDao authorityDao;

    @Autowired
    JwtUtil jwtUtil;

    /**
     * @param username 登陆用户名
     * @param password 登陆密码
     * @return 生成的JWT
     */
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

    public void register(String username, String password) throws UserExistException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        Authority authority = MvcUtil.insertIfNotExist("admin", "name", authorityDao::getByName, authorityDao::save, Authority.class);
        user.setAuthorities(List.of(authority));
        try {
            userDao.save(user);
        }
        catch (DataIntegrityViolationException ex)
        {
            throw new UserExistException();
        }
    }


}
