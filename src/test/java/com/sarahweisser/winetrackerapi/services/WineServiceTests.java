package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.Wine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class WineServiceTests {
    @Autowired
    private WineServiceImpl wineService;

    private Wine wine1 = new Wine(1l, "wine_name_1", "winery_name_1", "2020");
    private Wine wine2 = new Wine(2l, "wine_name_2", "winery_name_2", "2021");
    private Wine updatedWine1 = new Wine(1l, "updated_wine_name_1", "winery_name_1", "2022");

    @BeforeEach
    public void setUpWineTestData() {
        wineService.createWine(wine1);
        wineService.createWine(wine2);
    }

    @Test
    @DisplayName("testFindAllWines")
    void testFindAllWines() {
        Assertions.assertEquals(2, wineService.findAllWines().size());
    }

    @Test
    @DisplayName("testUpdateWine")
    void testUpdateWine() throws Exception {
        wineService.updateWine(this.updatedWine1);
        Wine updatedTestWine = wineService.findWineById(this.updatedWine1.getWineId()).get();

        Assertions.assertEquals(this.updatedWine1.getWineName(), updatedTestWine.getWineName());
        Assertions.assertEquals(this.updatedWine1.getVintage(), updatedTestWine.getVintage());
    }

    @Test
    @DisplayName("testDeleteById")
    void testDeleteById() {
        Assertions.assertEquals(wine2.getWineName(),
                wineService.findWineById(wine2.getWineId()).get().getWineName());
        wineService.deleteWineById(wine2.getWineId());
        Optional<Wine> deletedWine = wineService.findWineById(wine2.getWineId());
        Assertions.assertFalse(deletedWine.isPresent());
    }
}
