package org.example.notebook.model;

import java.time.LocalDateTime;

public class Note {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public Note(Long id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

