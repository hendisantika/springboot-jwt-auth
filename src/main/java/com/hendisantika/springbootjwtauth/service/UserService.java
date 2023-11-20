package com.hendisantika.springbootjwtauth.service;

import com.hendisantika.springbootjwtauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-auth
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/20/23
 * Time: 10:46
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

}
