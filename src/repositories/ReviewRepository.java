package repositories;

import entities.Review;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ReviewRepository implements IReviewRepository{
    private final Map<String, Review> reviewMap;
    private final Map<Long, String> idKeyMap;
    private Long autoincreament = 1L;
    public ReviewRepository(){
        this.reviewMap = new HashMap<>();
        this.idKeyMap = new HashMap<>();
    }
    @Override
    public Optional<Review> save(Review review) {
        // TODO Auto-generated method stub
        String userRestaurantId = review.getUserId() + "&" + review.getRestaurantId();
        Review savedReview = new Review(autoincreament, review.getRating(), review.getUserId(), review.getRestaurantId(), review.getDishes(),review.getDescription(), userRestaurantId);
        reviewMap.put(userRestaurantId, savedReview);
        idKeyMap.put(autoincreament, userRestaurantId);
        autoincreament++;
        return Optional.ofNullable(savedReview);
    }

    @Override
    public Optional<Review> getById(Long id) {
        // TODO Auto-generated method stub
        String userRestaurantId = idKeyMap.get(id);
        return getByUserIdRestaurantId(userRestaurantId);
    }

    @Override
    public Optional<Review> getByUserIdRestaurantId(String key) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(reviewMap.get(key));
    }
}

