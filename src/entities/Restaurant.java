package entities;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final Long id;
    private final String restaurantName;
    private List<Long> reviewIds;
    private double avgRating;

    public Restaurant(Long id, String restaurantName, List<Long> reviewIds, double avgRating) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.reviewIds = reviewIds;
        this.avgRating = avgRating;
    }

    public Restaurant(Long id, String restaurantName) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.reviewIds = new ArrayList<>();
    }

    public Restaurant(String restaurantName) {
        this(null, restaurantName);
    }



    public Long getId() {
        return id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<Long> getReviewIds() {
        return reviewIds;
    }

    public void setReviewIds(List<Long> reviewIds) {
        this.reviewIds = reviewIds;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    @Override
    public String toString() {
        return "Restaurant [id=" + id + "]";
    }

}

