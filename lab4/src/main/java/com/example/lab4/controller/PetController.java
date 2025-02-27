package com.example.lab4.controller;

import com.example.lab4.model.Pet;
import com.example.lab4.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PutMapping
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) {
        Pet updatedPet = petService.updatePet(pet);
        return ResponseEntity.ok(updatedPet);
    }

    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        Pet newPet = petService.addPet(pet);
        return ResponseEntity.ok(newPet);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long petId) {
        Optional<Pet> pet = petService.getPetById(petId);
        return pet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{petId}")
    public ResponseEntity<Void> updatePetWithForm(@PathVariable Long petId, @RequestParam String name, @RequestParam String status) {
        // Логика обновления питомца по форме
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Long petId) {
        petService.deletePet(petId);
        return ResponseEntity.ok().build();
    }
}