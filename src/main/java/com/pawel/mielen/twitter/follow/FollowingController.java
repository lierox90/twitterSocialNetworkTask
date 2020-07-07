package com.pawel.mielen.twitter.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowingController {

    private final FollowingService followingService;

    @PatchMapping(value = "/follow")
    public void postNewComment(@RequestParam String followingLogin, @RequestParam String followedLogin) {
        followingService.followUser(followingLogin, followedLogin);
    }
}