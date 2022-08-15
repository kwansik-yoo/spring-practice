package com.example.springpractice.base.domain.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Writer {
    private String id;
    private String name;

    public Writer(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
