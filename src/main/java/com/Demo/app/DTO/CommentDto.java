package com.Demo.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CommentDto {
    private long id;
    private String body;
    private String email;
    private String name;
}
