package com.security.template.domain.carro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "carro")
public class CarroApi {

    @GetMapping
    public String getCarro() {
        return "celta rebaixado";
    }
}
