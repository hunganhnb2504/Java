package com.example.crudapientry.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryDTO {
    private Long id;
    private String title;
    private String content;
    private Integer rate;
    private LocalDateTime created;
    private String author;
}
