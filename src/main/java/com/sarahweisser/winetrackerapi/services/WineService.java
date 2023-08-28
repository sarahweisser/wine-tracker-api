package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.Wine;

import java.util.List;
import java.util.Optional;

public interface WineService {
    Optional<Wine> findWineById(Long id);

    List<Wine> findAllWines();

    Wine createWine(Wine wineToAdd);

    Wine updateWine(Wine wineToUpdate) throws Exception;

    void deleteWineById(Long id);
}
