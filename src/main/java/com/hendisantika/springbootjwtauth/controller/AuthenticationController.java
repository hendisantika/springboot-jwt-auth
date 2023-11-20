package com.hendisantika.springbootjwtauth.controller;

import com.hendisantika.springbootjwtauth.service.AuthenticationService;
import com.hendisantika.springbootjwtauth.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-jwt-auth
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/20/23
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
}
