package com.ToyStory101.instagram.global.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {
    private CODE Code;
    private String message;
    private Object data;
}
