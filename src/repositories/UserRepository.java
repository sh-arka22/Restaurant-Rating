package repositories;

import entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository implements IUserRepository{
    private final Map<Long, User> userMap;
    private Long autoincreament = 1L;
    public UserRepository(){
        userMap = new HashMap<>();
    }

    @Override
    public Optional<User> save(User user) {
        // TODO Auto-generated method stub
        User savedUser = new User(autoincreament, user.getName());
        userMap.put(autoincreament, savedUser);
        autoincreament++;
        return Optional.ofNullable(savedUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(userMap.get(id));
    }
}
