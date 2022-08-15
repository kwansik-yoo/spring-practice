package com.example.springpractice.base.domain.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
    private String id;
    private String text;
    private String writerId;
    @Nullable
    private String parentCommentId;
    //
    transient private Writer writer;
    transient private List<Comment> children;

    public Comment(String id, String text, String writerId, @Nullable String parentCommentId) {
        this.id = id;
        this.text = text;
        this.writerId = writerId;
        this.parentCommentId = parentCommentId;
    }

    public Comment(String text, String writerId, @Nullable String parentCommentId) {
        this(UUID.randomUUID().toString(), text, writerId, parentCommentId);
    }

    public Comment(String text, String writerId) {
        this(text, writerId, null);
    }
}
