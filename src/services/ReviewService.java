package services;

import entities.Restaurant;
import entities.Review;
import repositories.IRestaurantRepository;
import repositories.IReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewService {
    private final IReviewRepository reviewRepository;
    private final IRestaurantRepository restaurantRepository;
    public ReviewService(IReviewRepository reviewRepository, IRestaurantRepository restaurantRepository){
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }



    /* 4
     * ADD_RATING <rating> <userId> <restaurantId>
     * Example : ADD_RATING 3 1 2
     * Output : Review [id=4] added successfully for Restaurant [id=1] by User [id=4]!
     */
    public Review addRating(Long rating, Long userId, Long restaurantId){
        //cheak If the same user gives a new rating for the same restaurant
        String key = userId + "&" + restaurantId;
        Optional<Review> reviewByKey = reviewRepository.getByUserIdRestaurantId(key);

        Review savedReview = null;
        if(reviewByKey.isPresent() == false){
            Review review = new Review(rating, userId, restaurantId);

            //setting the user&restaurantID
            review.setUserReataurantId(key);

            savedReview = reviewRepository.save(review).orElseThrow(()->new RuntimeException("review using user&reataurant ID not found"));

            //adding the reviewId on the restaurant
            addRestaurantReview(restaurantId, savedReview.getId());

            //updating the restaurant avg rating
            updateAvg(rating, restaurantId);
        }
        else{
            Review review = reviewByKey.get(); // returs the refference

            Long prevRating = review.getRating();

            Restaurant restaurant = restaurantRepository.getById(restaurantId).orElseThrow(() -> new RuntimeException("restaurent with id not found"));

            Long retaurantReviewCount = 1L*restaurant.getReviewIds().size();

            Double avgRating = restaurant.getAvgRating();

            Double totalRating = avgRating * retaurantReviewCount;

            Double newTotalRating = totalRating - prevRating + rating;

            Double newAvgRating = (1.0 * newTotalRating)/(1.0 * retaurantReviewCount);

            //updating throght the refferences
            review.setRating(rating);
            restaurant.setAvgRating(newAvgRating);

            savedReview = review;
        }

        return savedReview;
    }




    /* 5
     * ADD_REVIEW <rating>  <userId> <restaurantId> <list of dish_names> <description>
     * Example : ADD_REVIEW 4 1 2 “pav bhaji dosa” “pav bhaji and dosa was tasty”
     * Output : Review [id=4] added successfully for Restaurant [id=1] by User [id=4]!
     */
    public Review addReview(Long rating, Long userId, Long restaurantId, List<String> dishes, String description){
        String key = userId + "&" + restaurantId;
        Optional<Review> reviewByKey = reviewRepository.getByUserIdRestaurantId(key);

        Review savedReview = null;
        if(reviewByKey.isPresent() == false){
            Review review = new Review(rating, userId, restaurantId, dishes, description);

            //setting the user&restaurantID
            review.setUserReataurantId(key);

            savedReview = reviewRepository.save(review).orElseThrow();

            //adding the reviewId on the restaurant
            addRestaurantReview(restaurantId, savedReview.getId());

            //updating the restaurant avg rating
            updateAvg(rating, restaurantId);

        }
        else{
            Review review = reviewByKey.get(); // returs the refference

            Long prevRating = review.getRating();

            Restaurant restaurant = restaurantRepository.getById(restaurantId).orElseThrow(() -> new RuntimeException("restaurent with id not found"));

            Long restaurantReviewCount = 1L*restaurant.getReviewIds().size();

            Double avgRating = restaurant.getAvgRating();

            Double totalRating = avgRating * restaurantReviewCount;

            Double newTotalRating = totalRating - prevRating + rating;

            Double newAvgRating = (1.0 * newTotalRating)/(1.0 * restaurantReviewCount);

            //updating throght the refferences
            review.setRating(rating);
            review.setDescription(description);
            review.setDishes(new ArrayList<>(dishes)); //deep copying
            restaurant.setAvgRating(newAvgRating);

            review = savedReview;
        }

        return savedReview;
    }


    private void updateAvg(Long rating, Long restaurantId){
        Restaurant restaurant = restaurantRepository.getById(restaurantId).orElseThrow(() -> new RuntimeException("restaurent with id not found"));
        Double reviewCounts = 1.0 * restaurant.getReviewIds().size();
        Double previousTotalRating = restaurant.getAvgRating() * (reviewCounts-1) * 1.0;
        Double newToalRating = previousTotalRating + (1.0 * rating);
        Double newAverageRating = newToalRating / (1.0 * reviewCounts);

        restaurant.setAvgRating(newAverageRating);
    }


    private void addRestaurantReview(Long restaurantId, Long reviewId){
        Restaurant restaurant = restaurantRepository.getById(restaurantId).orElseThrow(() -> new RuntimeException("restaurent with id not found"));
        restaurant.getReviewIds().add(reviewId);
    }
}
