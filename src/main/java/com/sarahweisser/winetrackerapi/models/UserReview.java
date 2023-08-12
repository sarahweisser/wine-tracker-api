package com.sarahweisser.winetrackerapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "user_reviews")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_review_id")
    private Long userReviewId;

    // TODO add userRating column

    @Column(name = "user_review_content")
    private String reviewContent;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "wine_id")
    private Long wineId;

    public UserReview(Long userReviewId, String reviewContent, Long wineId, Long userId) {
        this.userReviewId = userReviewId;
        this.reviewContent = reviewContent;
        this.userId = userId;
        this.wineId = wineId;
    }

    public UserReview() {
    }

    public Long getUserReviewId() {
        return userReviewId;
    }

    public void setUserReviewId(Long userReviewId) {
        this.userReviewId = userReviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }
}
