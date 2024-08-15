package com.example.crudapientry.services;

import com.example.crudapientry.dtos.EntryDTO;
import com.example.crudapientry.entities.Entry;
import com.example.crudapientry.exceptions.AppException;
import com.example.crudapientry.exceptions.ErrorCode;
import com.example.crudapientry.mappers.EntryMapper;
import com.example.crudapientry.repositories.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntryService {
    private final EntryRepository entryRepository;
    private final EntryMapper entryMapper;

    public List<EntryDTO> findAll() {
        return entryRepository.findAll()
                .stream()
                .map(entryMapper::toEntryDTO)
                .collect(Collectors.toList());
    }

    public EntryDTO findById(Long id) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        return entryMapper.toEntryDTO(entry);
    }

    public EntryDTO create(EntryDTO entryDTO) {
        Entry entry = entryMapper.toEntity(entryDTO);
        entry = entryRepository.save(entry);
        return entryMapper.toEntryDTO(entry);
    }

    public EntryDTO update(Long id, EntryDTO entryDTO) {
        Entry existingEntry = entryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));

        existingEntry.setTitle(entryDTO.getTitle());
        existingEntry.setContent(entryDTO.getContent());
        existingEntry.setRate(entryDTO.getRate());
        existingEntry.setCreated(entryDTO.getCreated());
        existingEntry.setAuthor(entryDTO.getAuthor());

        entryRepository.save(existingEntry);
        return entryMapper.toEntryDTO(existingEntry);
    }

    public void delete(Long id) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));
        entryRepository.delete(entry);
    }
}
