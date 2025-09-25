package com.example.inventory_service.controller;

import com.example.inventory_service.models.Item;
import com.example.inventory_service.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<Item> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Item one(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<Item> create(@Valid @RequestBody Item item) {
        return ResponseEntity.status(201).body(service.create(item));
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable Long id, @Valid @RequestBody Item item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Item> search(@RequestParam("q") String q) {
        return service.searchByName(q);
    }
}