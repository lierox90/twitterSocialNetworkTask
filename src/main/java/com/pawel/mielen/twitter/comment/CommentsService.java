package com.pawel.mielen.twitter.comment;

import com.pawel.mielen.twitter.user.User;
import com.pawel.mielen.twitter.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final UserService userService;

    public void postNewComment(String login, String content) {
        User user = userService.findByLogin(login);
        if (Objects.isNull(user)) {
            user = userService.registerUser(login);
        }

        if (content.length() <= 140) {
            int ordinal = user.getUserComments().size();
            Comment comment = new Comment(ordinal, content, user.getLogin());
            user.addComment(comment);
        }
    }

    public List<Comment> getOwnComments(String login) {
        User user = userService.findByLogin(login);
        if (Objects.isNull(user)) {
            throw new EntityNotFoundException(login);
        }
        Collections.sort(user.getUserComments());

        return user.getUserComments();
    }

    public List<Comment> getFollowedComments(String login) {
        List<Comment> followedUsersComments = new ArrayList<>();
        for (User followedUser : userService.findByLogin(login).getFollowedUsers()) {
            followedUsersComments.addAll(followedUser.getUserComments());
        }
        Collections.sort(followedUsersComments);

        return followedUsersComments;
    }
}
