package com.example.crudapientry.controller;

import com.example.crudapientry.dtos.EntryDTO;
import com.example.crudapientry.dtos.ResponseObject;
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

    @GetMapping
    public ResponseEntity<ResponseObject> getAll() {
        List<EntryDTO> entries = entryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(true, 200, "OK", entries)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Long id) {
        EntryDTO entry = entryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(true, 200, "OK", entry)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseObject> create(@RequestBody EntryDTO entryDTO) {
        EntryDTO createdEntry = entryService.create(entryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject(true, 201, "Created", createdEntry)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable Long id, @RequestBody EntryDTO entryDTO) {
        EntryDTO updatedEntry = entryService.update(id, entryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(true, 200, "Updated", updatedEntry)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable Long id) {
        entryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(true, 200, "Deleted", null)
        );
    }
}
