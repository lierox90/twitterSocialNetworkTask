package com.pawel.mielen.twitter.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping(value = "/post-new-comment", consumes = "application/json")
    public void postNewComment(@RequestParam String login, @RequestBody String comment) {
        commentsService.postNewComment(login, comment);
    }

    @GetMapping(value = "/get-own-comments")
    public List<Comment> getOwnComments(@RequestParam String login) {
        return commentsService.getOwnComments(login);
    }

    @GetMapping(value = "/get-followed-comments")
    public List<Comment> getFollowedComments(@RequestParam String login) {
        return commentsService.getFollowedComments(login);
    }
}