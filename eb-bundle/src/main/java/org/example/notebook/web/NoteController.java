package org.example.notebook.web;

import jakarta.validation.Valid;
import org.example.notebook.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String home(Model model) {
        if (!model.containsAttribute("noteForm")) {
            model.addAttribute("noteForm", new NoteForm());
        }
        model.addAttribute("notes", noteService.getAllNotesNewestFirst());
        return "index";
    }

    @PostMapping("/notes")
    public String createNote(@Valid @ModelAttribute("noteForm") NoteForm noteForm,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("notes", noteService.getAllNotesNewestFirst());
            return "index";
        }

        noteService.createNote(noteForm.getTitle(), noteForm.getContent());
        return "redirect:/";
    }
}

