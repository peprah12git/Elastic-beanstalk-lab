package org.example.notebook.repository;

import org.example.notebook.model.Note;

import java.util.List;

public interface NoteRepository {

    Note save(Note note);

    List<Note> findAll();
}

