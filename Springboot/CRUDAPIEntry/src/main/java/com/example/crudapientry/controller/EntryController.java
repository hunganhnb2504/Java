package com.example.crudapientry.controller;

import com.example.crudapientry.dtos.EntryDTO;
import com.example.crudapientry.dtos.ResponseObject;
import com.example.crudapientry.models.CreateEntryModel;
import com.example.crudapientry.models.UpdateEntryModel;
import com.example.crudapientry.services.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entries")
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;

    @PostMapping
    public ResponseEntity<ResponseObject> createEntry(@RequestBody CreateEntryModel createEntryModel) {
        EntryDTO entryDTO = entryService.createEntry(createEntryModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseObject(true, HttpStatus.CREATED.value(), "Entry created successfully", entryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateEntry(@PathVariable Long id, @RequestBody UpdateEntryModel updateEntryModel) {
        EntryDTO entryDTO = entryService.updateEntry(id, updateEntryModel);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(true, HttpStatus.OK.value(), "Entry updated successfully", entryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteEntry(@PathVariable Long id) {
        entryService.deleteEntry(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(true, HttpStatus.OK.value(), "Entry deleted successfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getEntryById(@PathVariable Long id) {
        EntryDTO entryDTO = entryService.getEntryById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(true, HttpStatus.OK.value(), "Entry retrieved successfully", entryDTO));
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllEntries() {
        List<EntryDTO> entries = entryService.getAllEntries();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(true, HttpStatus.OK.value(), "Entries retrieved successfully", entries));
    }
}
