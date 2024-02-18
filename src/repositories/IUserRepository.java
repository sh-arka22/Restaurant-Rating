package repositories;

import entities.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> save(User user);
    Optional<User> findById(Long id);
}
