package com.pawel.mielen.twitter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByLogin(String login) {
        return userRepository.get(login);
    }

    public User registerUser(String login) {
        User user = new User(generateUniqueUserId(), login);
        userRepository.add(user);

        return user;
    }

    private long generateUniqueUserId() {
        long val;
        do {
            val = UUID.randomUUID().getMostSignificantBits();
        } while (val < 0);

        return val;
    }
}