package services;

import entities.User;
import repositories.IUserRepository;

public class UserService {
    private final IUserRepository userRepository;
    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }


    /* 3
     * REGISTER_USER <name>
     * Example : REGISTER_USER ARKA
     * Output : User [id=1]
     */
    public User userRegister(String name){
        User user = new User(name);
        User savedUser = userRepository.save(user).orElseThrow();
        return savedUser;
    }


    public User getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user Id not found"));
    }
}
