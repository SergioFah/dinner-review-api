package fah.sergio.dinnerreview.repository;

import fah.sergio.dinnerreview.model.AdminReviewStatus;
import fah.sergio.dinnerreview.model.Restaurant;
import fah.sergio.dinnerreview.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Iterable<Review> findByReviewStatus(AdminReviewStatus reviewStatus);
    Iterable<Review> findByRestaurantIdAndReviewStatus(Long restaurantId, AdminReviewStatus reviewStatus);
}
