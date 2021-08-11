package com.security.template.provider.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SendMailDto {
    private String to;
    private String subject;
    private String text;
}
