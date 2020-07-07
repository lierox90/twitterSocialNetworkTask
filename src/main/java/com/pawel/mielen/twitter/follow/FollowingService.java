package com.pawel.mielen.twitter.follow;

import com.pawel.mielen.twitter.user.User;
import com.pawel.mielen.twitter.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FollowingService {

    private final UserService userService;

    public void followUser(String followingLogin, String followedLogin) {
        User followingUser = userService.findByLogin(followingLogin);
        User followedUser = userService.findByLogin(followedLogin);

        if (!Objects.isNull(followingUser) && !Objects.isNull(followedUser)) {
            followingUser.addFollowed(followedUser);
        }
    }
}
