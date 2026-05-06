# Notebook (Spring Boot MVC)

A small notebook web application built with Java 17, Spring Boot, Maven, and Thymeleaf.

## Overview

This project demonstrates a clean MVC structure with a form-driven UI where users can create notes and view all saved notes on the same page.

Notes are stored in memory for now, and the code is organized so the repository can be replaced later with DynamoDB or a relational database implementation.

## Features

- Home page at `/`
- Create a note using a server-rendered HTML form
- View all notes on the same page
- Input validation for blank title and content
- Validation errors shown in the UI with user input preserved
- Notes sorted by newest first
- Health endpoint at `/health`

## Tech Stack

- Java 17
- Spring Boot (MVC)
- Thymeleaf
- Maven

## Project Structure

```text
src/
  main/
    java/org/example/notebook/
      NotebookApplication.java
      model/
        Note.java
      repository/
        NoteRepository.java
        InMemoryNoteRepository.java
      service/
        NoteService.java
      web/
        NoteController.java
        NoteForm.java
        HealthController.java
    resources/
      templates/
        index.html
      static/css/
        style.css
      application.properties
  test/
    java/org/example/notebook/
      NotebookApplicationTests.java
pom.xml
README.md
.gitignore
```

## Run Locally

### Prerequisites

- Java 17 installed
- Maven Wrapper is included (`mvnw`, `mvnw.cmd`)

### Start the app

```powershell
./mvnw.cmd spring-boot:run
```

Open:
- App: `http://localhost:8080/`
- Health: `http://localhost:8080/health`

## Build with Maven

```powershell
./mvnw.cmd clean verify
```

