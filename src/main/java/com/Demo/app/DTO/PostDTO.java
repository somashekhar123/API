package com.Demo.app.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class PostDTO {

    private long id;
    @NotNull
    @Size(min=5,message = "title should be atleast 5 charecters")
    private String title;
    @NotNull
    @Size(max=5,message = "field description maximum 5 charecters")
    private String description;

@NotEmpty
    private String content;

}
