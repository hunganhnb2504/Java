package com.example.crudapientry.services;

import com.example.crudapientry.dtos.EntryDTO;
import com.example.crudapientry.models.CreateEntryModel;
import com.example.crudapientry.models.UpdateEntryModel;

import java.util.List;

public interface EntryService {
    EntryDTO createEntry(CreateEntryModel createEntryModel);
    EntryDTO updateEntry(Long id, UpdateEntryModel updateEntryModel);
    void deleteEntry(Long id);
    EntryDTO getEntryById(Long id);
    List<EntryDTO> getAllEntries();
}
