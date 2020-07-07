package com.pawel.mielen.twitter.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment implements Comparable<Comment> {

    private int id;
    private String content;
    private String author;
    private LocalDateTime timestamp;

    public Comment(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
        timestamp = LocalDateTime.now();
    }

    @Override
    public int compareTo(Comment o) {
        return o.getTimestamp().compareTo(this.getTimestamp());
    }
}

