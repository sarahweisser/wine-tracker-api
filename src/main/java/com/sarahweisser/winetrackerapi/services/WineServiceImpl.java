package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.Wine;
import com.sarahweisser.winetrackerapi.repositories.WineJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WineServiceImpl implements WineService {
    @Autowired
    private WineJpaRepository wineJpaRepository;

    public WineServiceImpl(WineJpaRepository wineJpaRepository) {
        this.wineJpaRepository = wineJpaRepository;
    }

    @Override
    public Optional<Wine> findWineById(Long id) {
        return wineJpaRepository.findById(id);
    }

    @Override
    public List<Wine> findAllWines() {
        return wineJpaRepository.findAll();
    }

    @Override
    public Wine createWine(Wine wineToAdd) {
        return wineJpaRepository.saveAndFlush(wineToAdd);
    }

    @Override
    public Wine updateWine(Wine wineToUpdate) throws Exception {
        try {
            Optional<Wine> existingWine = wineJpaRepository.findById(wineToUpdate.getWineId());
            if (existingWine.isPresent()) {
                return wineJpaRepository.saveAndFlush(wineToUpdate);
            } else {
                throw new Exception("TODO create Wine not found exception and base entity not found class");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteWineById(Long id) {
        wineJpaRepository.deleteById(id);
    }

    // TODO
    // submit wine request
    // edit wine request
}
