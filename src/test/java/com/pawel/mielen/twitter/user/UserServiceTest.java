package com.pawel.mielen.twitter.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private static final String TEST_USER = "TEST_USER";

    private final UserRepository userRepositoryMock = mock(UserRepository.class);

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepositoryMock);
    }

    @Test
    public void verifyThatServiceCallsRepository() {
        userService.findByLogin(TEST_USER);

        verify(userRepositoryMock, times(1)).get(TEST_USER);
    }

    @Test
    public void verifyThatServiceRegistersNewUser() {
        User registeredUser = userService.registerUser(TEST_USER);

        verify(userRepositoryMock, times(1)).add(registeredUser);
        assertThat(registeredUser.getLogin()).isEqualTo(TEST_USER);
    }

    @Test
    public void VerifyThatThrowsNullPointerExceptionWhenUserHasNotGivenHisLogin() {
        assertThatThrownBy(() -> userService.registerUser(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("login is marked non-null but is null");
    }
}