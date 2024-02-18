package repositories;

import entities.Restaurant;

import java.util.*;

public class RestaurantRepository implements IRestaurantRepository{
    private final Map<Long, Restaurant> retaurantMap;
    private Long autoincreament = 1L;
    public RestaurantRepository(){
        this.retaurantMap = new HashMap<>();
    }
    @Override
    public Optional<Restaurant> save(Restaurant restaurant) {
        // TODO Auto-generated method stub
        Restaurant savedRestaurant = new Restaurant(autoincreament, restaurant.getRestaurantName(), restaurant.getReviewIds(), restaurant.getAvgRating());
        retaurantMap.put(autoincreament, savedRestaurant);
        autoincreament++;
        return Optional.ofNullable(savedRestaurant);
    }

    @Override
    public Optional<Restaurant> getById(Long restaurantId) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(retaurantMap.get(restaurantId));
    }

    @Override
    public List<Restaurant> getAll() {
        // TODO Auto-generated method stub
        return new ArrayList<>(retaurantMap.values());
    }

}
