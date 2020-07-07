package com.pawel.mielen.twitter.comment;

import com.pawel.mielen.twitter.user.User;
import com.pawel.mielen.twitter.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CommentsServiceTest {

    private static final String TEST_USER = "TEST_USER";
    private static final String TEST_COMMENT = "TEST_COMMENT";
    private static final String TOO_BIG_TEST_COMMENT = "AAAAAAAAAAAAAAAAAAAAAAAAAAA" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

    private final UserService userServiceMock = mock(UserService.class);

    private CommentsService commentsService;

    @BeforeEach
    public void setUp() {
        commentsService = new CommentsService(userServiceMock);
    }

    @Test
    public void ifUserIsAlreadyRegisteredShouldAddComment() {
        User user = new User(0, TEST_USER);
        when(userServiceMock.findByLogin(TEST_USER)).thenReturn(user);

        commentsService.postNewComment(TEST_USER, TEST_COMMENT);

        assertThat(user.getUserComments()).hasSize(1);
        assertThat(user.getUserComments().get(0).getContent()).isEqualTo(TEST_COMMENT);
    }

    @Test
    public void ifUserIsNotRegisteredShouldRegister() {
        doReturn(null).when(userServiceMock).findByLogin(TEST_USER);
        doReturn(new User(0, TEST_USER)).when(userServiceMock).registerUser(TEST_USER);

        commentsService.postNewComment(TEST_USER, TEST_COMMENT);

        verify(userServiceMock, times(1)).registerUser(TEST_USER);
    }

    @Test
    public void ifMessageExceedsLimitItIsNotAdded() {
        User user = new User(0, TEST_USER);
        when(userServiceMock.findByLogin(TEST_USER)).thenReturn(user);

        commentsService.postNewComment(TEST_USER, TOO_BIG_TEST_COMMENT);

        assertThat(user.getUserComments()).hasSize(0);
    }
}