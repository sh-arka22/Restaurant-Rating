package repositories;

import entities.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantRepository {
    Optional<Restaurant> save(Restaurant restaurant);
    Optional<Restaurant> getById(Long restaurantId);
    List<Restaurant> getAll();
}
