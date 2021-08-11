package com.security.template.auth;

import com.security.template.auth.dto.LoginDto;
import com.security.template.auth.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = AutenticacaoApi.PATH)
public class AutenticacaoApi {

    public static final String PATH = "auth";

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public TokenDto autenticar(@RequestBody LoginDto dto) {
        UsernamePasswordAuthenticationToken auth = dto.convert();

        Authentication authentication = manager.authenticate(auth);
        String token = tokenService.gerarToken(authentication);

        return new TokenDto(token, authentication.getPrincipal());
    }
}
