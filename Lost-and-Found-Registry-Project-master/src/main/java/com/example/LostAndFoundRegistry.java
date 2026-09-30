package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*") // Allows our frontend webpage to talk to our backend server smoothly
public class LostAndFoundRegistry {

    private static int idCounter = 0;
    private static Map<Integer, Item> registryMap = new HashMap<>();

    public static void main(String[] args) {
        // Starts the background local web server on port 8080!
        SpringApplication.run(LostAndFoundRegistry.class, args);
    }

    // WEB API: Add an item from the webpage form submission
    @PostMapping
    public String addItem(@RequestBody Map<String, String> payload) {
        try {
            idCounter++;
            Item item = new Item(
                    idCounter,
                    payload.get("name"),
                    payload.get("category"),
                    payload.get("location"),
                    LocalDate.parse(payload.get("dateFound"))
            );
            registryMap.put(item.getId(), item);
            return "Success! Registered with ID: " + item.getId();
        } catch (Exception e) {
            return "Error parsing item data: " + e.getMessage();
        }
    }

    // WEB API: Get all items to display on the inventory dashboard
    @GetMapping
    public List<Item> getAllItems() {
        return new ArrayList<>(registryMap.values());
    }

    // WEB API: O(1) Search by explicit ID
    @GetMapping("/search/id/{id}")
    public List<Item> searchById(@PathVariable int id) {
        List<Item> results = new ArrayList<>();
        Item item = registryMap.get(id);
        if (item != null) results.add(item);
        return results;
    }

    // WEB API: O(n) Adaptive Searches (Name, Category, Location, Date)
    @GetMapping("/search")
    public List<Item> searchItems(@RequestParam String field, @RequestParam String value) {
        List<Item> results = new ArrayList<>();
        for (Item item : registryMap.values()) {
            boolean match = false;
            if (field.equalsIgnoreCase("name")) match = item.getName().equalsIgnoreCase(value);
            else if (field.equalsIgnoreCase("category")) match = item.getCategory().equalsIgnoreCase(value);
            else if (field.equalsIgnoreCase("location")) match = item.getLocation().equalsIgnoreCase(value);
            else if (field.equalsIgnoreCase("date")) match = item.getDateFound().toString().equals(value);

            if (match) results.add(item);
        }
        return results;
    }

    // WEB API: O(1) Deletion / Claim processing
    @DeleteMapping("/{id}")
    public String removeItem(@PathVariable int id) {
        Item removed = registryMap.remove(id);
        if (removed != null) {
            return "🗑️ Removed item: '" + removed.getName() + "' permanently from database records.";
        }
        return "Error: com.example.example.Item ID " + id + " not found.";
    }
}