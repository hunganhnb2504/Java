package com.example.crudapientry.mappers;

import com.example.crudapientry.dtos.EntryDTO;
import com.example.crudapientry.entities.Entry;
import org.springframework.stereotype.Component;

@Component
public class EntryMapper {
    public EntryDTO toEntryDTO(Entry entry) {
        return EntryDTO.builder()
                .id(entry.getId())
                .title(entry.getTitle())
                .content(entry.getContent())
                .rate(entry.getRate())
                .created(entry.getCreated())
                .author(entry.getAuthor())
                .build();
    }

    public Entry toEntity(EntryDTO entryDTO) {
        return Entry.builder()
                .id(entryDTO.getId())
                .title(entryDTO.getTitle())
                .content(entryDTO.getContent())
                .rate(entryDTO.getRate())
                .created(entryDTO.getCreated())
                .author(entryDTO.getAuthor())
                .build();
    }
}
