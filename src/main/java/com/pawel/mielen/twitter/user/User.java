package com.pawel.mielen.twitter.user;

import com.pawel.mielen.twitter.comment.Comment;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class User {

    private long id;
    private String login;
    private List<Comment> userComments;
    private Set<User> followedUsers;

    public User(long id, @NonNull String login) {
        this.id = id;
        this.login = login;
        this.userComments = new ArrayList<>();
        this.followedUsers = new HashSet<>();
    }

    public void addComment(Comment comment) {
        userComments.add(comment);
    }

    public void addFollowed(User userToFollower) {
        followedUsers.add(userToFollower);
    }
}