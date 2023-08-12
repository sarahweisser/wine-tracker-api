package com.sarahweisser.winetrackerapi.repositories;

import com.sarahweisser.winetrackerapi.models.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReviewJpaRepository extends JpaRepository<UserReview, Long> {
    List<UserReview> findUserReviewsByUserId(Long userId);
    List<UserReview> findUserReviewsByWineId(Long wineId);
}
