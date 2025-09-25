package com.example.inventory_service.service;

import com.example.inventory_service.models.Item;
import com.example.inventory_service.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repo;

    public ItemService(ItemRepository repo) { this.repo = repo; }

    public List<Item> getAll() { return repo.findAll(); }

    public Item getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Item not found with id " + id));
    }

    public Item create(Item item) {
        return repo.save(item);
    }

    public Item update(Long id, Item item) {
        Item exist = getById(id);
        exist.setName(item.getName());
        exist.setDescription(item.getDescription());
        exist.setQuantity(item.getQuantity());
        exist.setPrice(item.getPrice());
        return repo.save(exist);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Item> searchByName(String q) {
        return repo.findByNameContainingIgnoreCase(q);
    }
}
