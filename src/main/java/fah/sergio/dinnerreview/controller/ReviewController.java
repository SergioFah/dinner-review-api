package fah.sergio.dinnerreview.controller;


//As a registered user, I want to submit a dining review.
//As an admin, I want to get the list of all dining reviews that are pending approval.
//As an admin, I want to approve or reject a given dining review.
//As part of the backend process that updates a restaurant’s set of scores, I want to fetch the set of all approved dining reviews belonging to this restaurant.

import fah.sergio.dinnerreview.model.AdminReviewStatus;
import fah.sergio.dinnerreview.model.Review;
import fah.sergio.dinnerreview.repository.RestaurantRepository;
import fah.sergio.dinnerreview.repository.ReviewRepository;
import fah.sergio.dinnerreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/dining-review")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Review create(@RequestBody Review review){
        //verify restaurant
        if(this.restaurantRepository.findById(review.getRestaurantId()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This restaurant doesn't exist");
        }
        //verify user
        if(this.userRepository.findByName(review.getReviewedBy()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user doesn't exist");
        }
        //post review
        return this.reviewRepository.save(review);
    }

    @GetMapping("/accepted/{id}")
    public Iterable<Review> getByRestaurantId(@PathVariable Long id){
        //find all accepted reviews of a restaurant
        return this.reviewRepository.findByRestaurantIdAndReviewStatus(id, AdminReviewStatus.APPROVED);
    }

    @GetMapping("/pending")
    public Iterable<Review> getPending(){
        return this.reviewRepository.findByReviewStatus(AdminReviewStatus.PENDING);
    }

    @PutMapping("/pending/{id}/accept")
    public Review approveReview(@PathVariable("id") Long id){
        Optional<Review> reviewToBeAcceptedOptional = this.reviewRepository.findById(id);
        if(reviewToBeAcceptedOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review doesn't exist");
        }
        if(reviewToBeAcceptedOptional.get().getReviewStatus() == AdminReviewStatus.APPROVED){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review was already accepted");
        }
        Review reviewToBeAccepted = reviewToBeAcceptedOptional.get();
        reviewToBeAccepted.setReviewStatus(AdminReviewStatus.APPROVED);
        return this.reviewRepository.save(reviewToBeAccepted);
    }

    @PutMapping("/pending/{id}/reject")
    public Review rejectReview(@PathVariable("id") Long id){
        Optional<Review> reviewToBeAcceptedOptional = this.reviewRepository.findById(id);
        if(reviewToBeAcceptedOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review doesn't exist");
        }
        if(reviewToBeAcceptedOptional.get().getReviewStatus() == AdminReviewStatus.REJECTED){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review was already rejected");
        }
        Review reviewToBeAccepted = reviewToBeAcceptedOptional.get();
        reviewToBeAccepted.setReviewStatus(AdminReviewStatus.REJECTED);
        return this.reviewRepository.save(reviewToBeAccepted);
    }


}
