package com.auth1.auth.learning.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDTO {
    private String from;
    private String to;
    private String body;
    private String subject;
}
