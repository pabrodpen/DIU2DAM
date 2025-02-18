package com.example.tutorials.Tutorials.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document

public class TutorialsVO {

    @Id
    private String id;
    private String title;
    private String description;
    private Boolean published;
    private String url_image;

}

