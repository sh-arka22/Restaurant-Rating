package services;

import constants.Order;
import entities.Restaurant;
import entities.Review;
import repositories.IRestaurantRepository;
import repositories.IReviewRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantService {
    private final IRestaurantRepository restaurantRepository;
    private final IReviewRepository reviewRepository;
    public RestaurantService(IRestaurantRepository restaurantRepository, IReviewRepository reviewRepository){
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }


    /* 1
     * REGISTER_RESTAURANT <name>
     * Example : REGISTER_RESTAURANT “Mama’s Kitchen”
     * Output : Restaurant [id=1]
     */
    public Restaurant registerRestaurant(String restaurantName){
        Restaurant restaurant = new Restaurant(restaurantName);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant).orElseThrow(() -> new RuntimeException("restaurent name not found"));
        return savedRestaurant;
    }



    /* 6
     * GET_REVIEWS <restaurant_id> <order>
     * Example : GET_REVIEWS 2 RATING_DESC
     * Output : [Review [id=7], Review [id=5], Review [id=6], Review [id=8]]
     */
    public List<Review> getReviews(Long restaurantId, Order order){
        List<Long>reviewIds = restaurantRepository.getById(restaurantId).orElseThrow(() -> new RuntimeException("restaurent with id not found")).getReviewIds();

        List<Review> reviews = new ArrayList<>();

        for(Long reviewId: reviewIds){
            Review review = reviewRepository.getById(reviewId).orElseThrow(() -> new RuntimeException("review with reviewId not found"));
            // System.out.println("i also came here");
            reviews.add(review);
        }

        return orderedReviews(reviews, order);
    }



    /* 7
     * GET_REVIEWS_FILTER_ORDER  <restaurant_id> <low> <high> <order>
     * Example : GET_REVIEWS_FILTER_ORDER 2 2 5 RATING_DESC
     * Output : [Review [id=7], Review [id=5], Review [id=6], Review [id=8]]
     */
    public List<Review> getFilteredReviews(Long restaurantId, Long low, Long high, Order order){
        List<Long>reviewIds = restaurantRepository.getById(restaurantId).orElseThrow(() -> new RuntimeException("restaurent with id not found")).getReviewIds();

        List<Review> reviews = new ArrayList<>();

        for(Long reviewId: reviewIds){
            Review review = reviewRepository.getById(reviewId).get();
            reviews.add(review);
        }
        List<Review>notFilteredReview = orderedReviews(reviews, order);

        return notFilteredReview.stream().filter(r -> r.getRating() >= low && r.getRating() <= high).collect(Collectors.toList());
    }


    /* 8
     * DESCRIBE_RESTAURANT <restaurant_id>
     * Example : DESCRIBE_RESTAURANT 2
     * Output: Restaurant [id=1, name=A2B, rating=2.6]
     */
    public String describeRestaturent(Long restaurantId){
        Restaurant restaurant = restaurantRepository.getById(restaurantId).orElseThrow(() -> new RuntimeException("restaurent with id not found"));
        String ans = "";
        ans  = ans + "Restaurant " + "[id=" + restaurantId + ", " + "name=" + restaurant.getRestaurantName() + ", " + "rating=" + restaurant.getAvgRating() + "]";
        return ans;
    }


    public Restaurant getRestaurant(Long restaurantId){
        return restaurantRepository.getById(restaurantId).orElseThrow(()->new RuntimeException("restaturant Id not found"));
    }


    /* 9
     * LIST_RESTAURANTS
     * List restaurants based on descending order of (average) rating.
     * Example : LIST_RESTAURANTS
     * Output : [Restaurant [id=1], Restaurant [id=2]]
     */
    public List<Restaurant> listAllRestarant(){
        List<Restaurant> notOrdered = restaurantRepository.getAll();

        //returning in descending order wrt avg-rating
        return orderedRestaurants(notOrdered);
    }


    //Utill mthethod
    List<Restaurant> orderedRestaurants(List<Restaurant>restaurants){
        Comparator<Restaurant> cmpASC = Comparator.comparingDouble(Restaurant::getAvgRating);
        Comparator<Restaurant> cmpDSC = cmpASC.reversed();

        restaurants.sort(cmpDSC);
        return new ArrayList<>(restaurants);
    }
    List<Review> orderedReviews(List<Review>reviews, Order order){
        Comparator<Review> cmpASC = (a, b) -> {
            return a.getRating().intValue() - b.getRating().intValue();
        };
        Comparator<Review> cmpDSC = (a, b) -> {
            return b.getRating().intValue() - a.getRating().intValue();
        };

        Comparator<Review> cmp;
        if(Order.RATING_ASC.equals(order))
            cmp = cmpASC;
        else cmp = cmpDSC;

        // Collections.sort(reviews, cmp);
        reviews.sort(cmp);

        return reviews;
    }
}
