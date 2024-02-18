package entities;

import java.util.List;

public class Review {
    private final Long id;
    private long rating;
    private final Long userId;
    private final Long restaurantId;
    private List<String> dishes;
    private String description;
    private String userRestaurantId;
    public Review(Long id, Long rating, Long userId, Long restaurantId, List<String> dishes, String description, String userReataurantId) {
        this.id = id;
        this.rating = rating;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
        this.description = description;
        this.userRestaurantId = userReataurantId;
    }

    public Review(Long raiting, Long userId, Long restaurantId) {
        this(null, raiting, userId, restaurantId, null, null, null);
    }

    public Review(Long raiting, Long userId, Long restaurantId, List<String> dishes, String description) {
        this(null, raiting, userId, restaurantId, dishes, description, null);
    }

    public Long getId() {
        return id;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }



    public List<String> getDishes() {
        return dishes;
    }

    public void setDishes(List<String> dishes) {
        this.dishes = dishes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getUserReataurantId() {
        return userRestaurantId;
    }

    public void setUserReataurantId(String userRestaurantId) {
        this.userRestaurantId = userRestaurantId;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + "]";
    }
}

