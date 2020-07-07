package com.pawel.mielen.twitter.follow;

import com.pawel.mielen.twitter.user.User;
import com.pawel.mielen.twitter.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class FollowingServiceTest {

    private static final String USER_A = "A";
    private static final String USER_B = "B";

    private final UserService userServiceMock = mock(UserService.class);

    private FollowingService followingService;

    @BeforeEach
    public void setUp() {
        followingService = new FollowingService(userServiceMock);
    }

    @Test
    public void verifyThatWhenAFollowsBItsOnFollowedList() {
        User followingUser = new User(0, USER_A);
        doReturn(followingUser).when(userServiceMock).findByLogin(USER_A);
        User followedUser = new User(1, USER_B);
        doReturn(followedUser).when(userServiceMock).findByLogin(USER_B);

        followingService.followUser(USER_A, USER_B);

        assertThat(followingUser.getFollowedUsers()).hasSize(1);
        assertThat(followingUser.getFollowedUsers().iterator().next().getLogin()).isEqualTo(USER_B);
    }

    @Test
    public void verifyThatWhenAFollowsBAndBDoesNotExistItIsNotFollowed() {
        User followingUser = new User(0, USER_A);
        doReturn(followingUser).when(userServiceMock).findByLogin(USER_A);
        doReturn(null).when(userServiceMock).findByLogin(USER_B);

        followingService.followUser(USER_A, USER_B);

        assertThat(followingUser.getFollowedUsers()).hasSize(0);
    }
}