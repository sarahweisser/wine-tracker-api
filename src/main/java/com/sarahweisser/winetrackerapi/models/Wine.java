package com.sarahweisser.winetrackerapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "wines")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wine_id")
    private Long wineId;

    // TODO ensure new wines have unique [ wineName | wineryName | vintage ]
    @NotBlank
    @Column(name = "wine_name")
    private String wineName;
    @NotBlank
    @Column(name = "winery_name")
    private String wineryName;
    @NotBlank
    @Column(name = "vintage")
    private String vintage;

    // TODO add attributes listed below
    //    @JsonIgnore
    //    @Column(name = "user_rating")
    //    private float userRating;
    //    @JsonIgnore
    //    @OneToMany(cascade = CascadeType.ALL)
    //    List<UserReview> userReviews;
    //    @JsonIgnore
    //    @OneToMany(cascade = CascadeType.ALL)
    //    List<Varietal> varietals;
    //    @JsonIgnore
    //    @JoinColumn(name = "appellation_id")
    //    @ManyToOne(cascade = CascadeType.ALL)
    //    Appellation appellation;

    public Wine(Long wineId, String wineName, String wineryName, String vintage) {
        this.wineId = wineId;
        this.wineName = wineName;
        this.wineryName = wineryName;
        this.vintage = vintage;
    }

    public Wine() {
    }

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public String getWineryName() {
        return wineryName;
    }

    public void setWineryName(String wineryName) {
        this.wineryName = wineryName;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }
}