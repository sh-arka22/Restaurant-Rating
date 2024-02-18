import constants.Order;
import entities.Restaurant;
import entities.Review;
import entities.User;
import repositories.*;
import services.RestaurantService;
import services.ReviewService;
import services.UserService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        if (args.length != 1){
//            throw new RuntimeException();
//        }
//        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
         String filePath = "./src/sample_input/sample_input_one.txt";
         List<String> commandLineArgs = new LinkedList<>();
         commandLineArgs.add("filePath=" + filePath);
        run(commandLineArgs);
    }

    public static void run(List<String> commandLineArgs){
        //initialsie repositories
        IRestaurantRepository restaurantRepository = new RestaurantRepository();
        IUserRepository userRepository = new UserRepository();
        IReviewRepository reviewRepository = new ReviewRepository();


        //initilise services
        RestaurantService restaurantService = new RestaurantService(restaurantRepository, reviewRepository);
        ReviewService reviewService = new ReviewService(reviewRepository, restaurantRepository);
        UserService userService = new UserService(userRepository);


        String inputFile = commandLineArgs.get(0).split("=")[1];

        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            while (true) {
                String line = reader.readLine();
                if (line == null){
                    break;
                }
                List<String> tokens = Arrays.asList(line.split(" "));
                switch(tokens.get(0)){
                    case "REGISTER_RESTAURANT" :
                    {
                        Restaurant restaurant = restaurantService.registerRestaurant(tokens.get(1));
                        System.out.println(restaurant);
                    }
                    break;

                    case "REGISTER_USER" :
                    {
                        User user = userService.userRegister(tokens.get(1));
                        System.out.println(user);
                    }
                    break;

                    case "ADD_RATING" :
                    {
                        Long rating = Long.parseLong(tokens.get(1));
                        Long userId = Long.parseLong(tokens.get(2));
                        Long restaurantId = Long.parseLong(tokens.get(3));
                        Review review = reviewService.addRating(rating, userId, restaurantId);
                        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
                        User user = userService.getUser(userId);
                        System.out.println(review + " added" + " successfully " + "for " + restaurant + " by " + user + "!");
                    }
                    break;

                    case "ADD_REVIEW" :
                    {
                        Long rating = Long.parseLong(tokens.get(1));
                        Long userId = Long.parseLong(tokens.get(2));
                        Long restaurantId = Long.parseLong(tokens.get(3));
                        String dishes = tokens.get(4);
                        List<String> dishList = Arrays.asList(dishes.split(" "));
                        String description = tokens.get(5);
                        Review review = reviewService.addReview(rating, userId, restaurantId, dishList, description);
                        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
                        User user = userService.getUser(userId);
                        System.out.println(review +  " added successfully for " + restaurant +" by " + user + "!");
                    }
                    break;

                    case "GET_REVIEWS":
                    {
                        List<Review> reviews;
                        Long restaurantId = Long.parseLong(tokens.get(1));
                        String order = tokens.get(2);
                        if(order.equals("RATING_DESC")){
                            reviews = restaurantService.getReviews(restaurantId, Order.RATING_DESC);
                        }
                        else reviews = restaurantService.getReviews(restaurantId, Order.RATING_ASC);

                        System.out.println(reviews);
                    }
                    break;

                    case "GET_REVIEWS_FILTER_ORDER" :
                    {
                        List<Review> reviews;
                        Long restaurantId = Long.parseLong(tokens.get(1));
                        Long low = Long.parseLong(tokens.get(2));
                        Long high = Long.parseLong(tokens.get(3));
                        String order = tokens.get(4);
                        if(order.equals("RATING_DESC")){
                            reviews = restaurantService.getFilteredReviews(restaurantId, low, high, Order.RATING_DESC);
                        }
                        else reviews = restaurantService.getFilteredReviews(restaurantId, low, high, Order.RATING_ASC);
                        System.out.println(reviews);
                    }
                    break;

                    case "DESCRIBE_RESTAURANT" :
                    {
                        Long restaurantId = Long.parseLong(tokens.get(1));
                        System.out.println(restaurantService.describeRestaturent(restaurantId));
                    }
                    break;

                    case "LIST_RESTAURANT" :
                    {
                        List<Restaurant> restaurants = restaurantService.listAllRestarant();
                        System.out.println(restaurants);
                    }
                    break;

                    default:
                        throw new RuntimeException("Invalid Command");
                }
            }
            reader.close();
        }
        catch (Exception e) {
            System.out.println("here is the issue");
            System.out.println(e);;
        }
    }
}