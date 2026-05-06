package org.example.notebook.repository;

import org.example.notebook.model.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryNoteRepository implements NoteRepository {

    private final List<Note> notes = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public synchronized Note save(Note note) {
        Long id = note.getId() != null ? note.getId() : idGenerator.incrementAndGet();
        Note stored = new Note(id, note.getTitle(), note.getContent(), note.getCreatedAt());
        notes.add(stored);
        return stored;
    }

    @Override
    public synchronized List<Note> findAll() {
        return new ArrayList<>(notes);
    }
}

