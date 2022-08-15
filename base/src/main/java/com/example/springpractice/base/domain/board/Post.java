package com.example.springpractice.base.domain.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    private String id;
    private String title;
    private String content;
    private String writerId;
    //
    transient private Writer writer;
    transient private List<Comment> comments;

    public Post(String id, String title, String content, String writerId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }

    public Post(String title, String content, String writerId) {
        this(UUID.randomUUID().toString(), title, content, writerId);
    }
}
