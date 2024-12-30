package com.roman.bookshelf.controller;

import com.roman.bookshelf.domain.collection.CollectionRequestDto;
import com.roman.bookshelf.domain.collection.CollectionResponseDto;
import com.roman.bookshelf.domain.collection.CollectionStatusDto;
import com.roman.bookshelf.domain.collection.CollectionWithPageCountResponseDto;
import com.roman.bookshelf.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collections")
public class CollectionController {
    private final CollectionService collectionService;
    @GetMapping("/{login}")
    public ResponseEntity<CollectionWithPageCountResponseDto> getCollections(@PathVariable String login, @RequestParam(defaultValue = "0", required = false) Integer page){
        return ResponseEntity.ok(collectionService.getCollectionByUserLogin(login, page));
    }

    @PostMapping
    public ResponseEntity<CollectionResponseDto> addToCollection(@RequestBody CollectionRequestDto collectionRequestDto){
        try {
            CollectionResponseDto responseDto = collectionService.addToCollection(collectionRequestDto);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long id){
        collectionService.deleteCollection(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionResponseDto> updateBookStatus(@PathVariable Long id, @RequestBody CollectionStatusDto newStatus) {
            return ResponseEntity.ok(collectionService.updateBookStatus(id, newStatus));
    }

}
