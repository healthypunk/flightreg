package com.flightreg.flightreg.service;

import com.flightreg.flightreg.model.User;
import com.flightreg.flightreg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String email, String password) {
        //TODO: fix if clause always being called
//        if (userRepository.findByUsername(username) != null) {
//            throw new RuntimeException("Username already exists");
//        }
        System.out.print(userRepository.findByUsername(username));
        var builder = User.builder()
                .username(username)
                .email(email)
                .password(password);

        return userRepository.save(builder.build());
    }

    public void deleteUser(int id){
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
