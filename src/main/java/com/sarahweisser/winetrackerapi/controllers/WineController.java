package com.sarahweisser.winetrackerapi.controllers;

import com.sarahweisser.winetrackerapi.models.Wine;
import com.sarahweisser.winetrackerapi.repositories.WineJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class WineController {
    @Autowired
    WineJpaRepository wineJpaRepository;

    @GetMapping("/wines")
    Iterable<Wine> findAllWines() {
        return wineJpaRepository.findAll();
    }

    @GetMapping("/wines/{id}")
    Optional<Wine> findWineById(@PathVariable Long id) {
        return wineJpaRepository.findById(id);
    }

    @GetMapping("/wines/{wineName}")
    Iterable<Wine> findWineByWineName(@PathVariable String wineName) {
        return wineJpaRepository.findWineByWineName(wineName);
    }

    @PostMapping("/wines")
    Wine addWine(@RequestBody Wine wine) {
        return wineJpaRepository.saveAndFlush(wine);
    }

    @DeleteMapping("/wines/{id}")
    void deleteWineById(@PathVariable Long id) {
        wineJpaRepository.deleteById(id);
    }

    @PutMapping("/wines/{id}")
    Wine updateWine(@RequestBody Wine wine, @PathVariable Long id) {
        // TODO add validation check for exisiting wine
        wine.setWineId(id);
        return wineJpaRepository.saveAndFlush(wine);
    }
}

