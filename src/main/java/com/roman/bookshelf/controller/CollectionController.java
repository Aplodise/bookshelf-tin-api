package com.roman.bookshelf.controller;

import com.roman.bookshelf.domain.collection.CollectionRequestDto;
import com.roman.bookshelf.domain.collection.CollectionResponseDto;
import com.roman.bookshelf.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collections")
public class CollectionController {
    private final CollectionService collectionService;
    @GetMapping("/{login}")
    public ResponseEntity<List<CollectionResponseDto>> getCollections(@PathVariable String login, @RequestParam(defaultValue = "0", required = false) Integer page){
        return ResponseEntity.ok(collectionService.getCollectionByUserLogin(login, page));
    }

    @PostMapping
    public ResponseEntity<CollectionResponseDto> addToCollection(@RequestBody CollectionRequestDto collectionRequestDto){
        return ResponseEntity.ok(collectionService.addToCollection(collectionRequestDto));
    }
}
