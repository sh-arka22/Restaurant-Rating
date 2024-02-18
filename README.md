# Restaurant Rating System

## Full working code:

This is a command-line based restaurant management system that allows users to register restaurants, users, add ratings and reviews for restaurants, get reviews, and perform various other operations.

## Features

1. **REGISTER_RESTAURANT `<name>`**
   - Registers a new restaurant with the given name.
   - Example: `REGISTER_RESTAURANT "Mama's Kitchen"`
   - Output: `Restaurant [id=1]`

2. **REGISTER_USER `<name>`**
   - Registers a new user with the given name.
   - Example: `REGISTER_USER ANAND`
   - Output: `User [id=1]`

3. **ADD_RATING `<rating>` `<userId>` `<restaurantId>`**
   - Adds a rating for a restaurant given by a user.
   - Example: `ADD_RATING 3 1 2`
   - Output: `Review [id=4] added successfully for Restaurant [id=1] by User [id=4]!`

4. **ADD_REVIEW `<rating>` `<userId>` `<restaurantId>` `<list of dish_names>` `<description>`**
   - Adds a detailed review for a restaurant given by a user including dish names and description.
   - Example: `ADD_REVIEW 4 1 2 "pav bhaji dosa" "pav bhaji and dosa was tasty"`
   - Output: `Review [id=4] added successfully for Restaurant [id=1] by User [id=4]!`

5. **GET_REVIEWS `<restaurant_id>` `<order>`**
   - Retrieves reviews for a restaurant based on the specified order.
   - Example: `GET_REVIEWS 2 RATING_DESC`
   - Output: `[Review [id=7], Review [id=5], Review [id=6], Review [id=8]]`

6. **GET_REVIEWS_FILTER_ORDER `<restaurant_id>` `<low>` `<high>` `<order>`**
   - Retrieves filtered reviews for a restaurant based on rating range and order.
   - Example: `GET_REVIEWS_FILTER_ORDER 2 2 5 RATING_DESC`
   - Output: `[Review [id=7], Review [id=5], Review [id=6], Review [id=8]]`

7. **DESCRIBE_RESTAURANT `<restaurant_id>`**
   - Describes a restaurant including its name, id, and average rating.
   - Example: `DESCRIBE_RESTAURANT 2`
   - Output: `Restaurant [id=1, name=A2B, rating=2.6]`

8. **LIST_RESTAURANTS**
   - Lists restaurants based on descending order of average rating.
   - Example: `LIST_RESTAURANTS`
   - Output: `[Restaurant [id=1], Restaurant [id=2]]`

