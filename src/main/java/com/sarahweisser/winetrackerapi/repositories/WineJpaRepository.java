package com.sarahweisser.winetrackerapi.repositories;

import com.sarahweisser.winetrackerapi.models.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineJpaRepository extends JpaRepository<Wine, Long> {
    Iterable<Wine> findWineByWineName(String wineNane);
}
