package repositories;

import entities.Review;

import java.util.Optional;

public interface IReviewRepository {
    Optional<Review> save(Review review);
    Optional<Review> getById(Long id);
    Optional<Review> getByUserIdRestaurantId(String key);
}
