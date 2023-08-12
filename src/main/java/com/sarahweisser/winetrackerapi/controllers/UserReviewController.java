package com.sarahweisser.winetrackerapi.controllers;

import com.sarahweisser.winetrackerapi.models.UserReview;
import com.sarahweisser.winetrackerapi.repositories.UserReviewJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class UserReviewController {
    @Autowired
    UserReviewJpaRepository userReviewJpaRepository;

    @GetMapping("/reviews")
    Iterable<UserReview> findAllUserReviews() {
        return userReviewJpaRepository.findAll();
    }

    @GetMapping("/reviews/{id}")
    Optional<UserReview> findUserReviewById(@PathVariable Long id) {
        return userReviewJpaRepository.findById(id);
    }

    @PostMapping("/reviews")
    UserReview createUserReview(@RequestBody UserReview userReview) {
        // user should be able to create multiple reviews for the same wine if tasted more than once
        // TODO determine how to differentiate same-wine reviews for client (date?)
        return userReviewJpaRepository.saveAndFlush(userReview);
    }

    @PutMapping("/reviews/{id}")
    UserReview updateUserReview(@RequestBody UserReview userReview, @PathVariable Long id) {
        Optional<UserReview> existingUserReview = userReviewJpaRepository.findById(userReview.getUserReviewId());
        if (existingUserReview.isPresent()) {
            return userReviewJpaRepository.saveAndFlush(userReview);
        }
        // TODO set up error message for client if user is not found in DB
        // TODO add timeStamp for updates
        return userReview;
    }

    @DeleteMapping("/reviews/{id}")
    void deleteUserReview(@PathVariable Long id) {
        userReviewJpaRepository.deleteById(id);
    }
}