package org.example.notebook.service;

import org.example.notebook.model.Note;
import org.example.notebook.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(String title, String content) {
        Note note = new Note(null, title.trim(), content.trim(), LocalDateTime.now());
        return noteRepository.save(note);
    }

    public List<Note> getAllNotesNewestFirst() {
        return noteRepository.findAll().stream()
                .sorted(Comparator
                        .comparing(Note::getCreatedAt)
                        .thenComparing(Note::getId)
                        .reversed())
                .toList();
    }
}

