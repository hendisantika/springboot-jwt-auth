package com.hendisantika.springbootjwtauth.config;

import com.hendisantika.springbootjwtauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-auth
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/20/23
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {
    private final UserRepository userRepository;
}
