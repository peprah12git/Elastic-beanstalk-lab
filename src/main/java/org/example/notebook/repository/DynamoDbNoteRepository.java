package org.example.notebook.repository;

import org.example.notebook.model.Note;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DynamoDbNoteRepository implements NoteRepository {

    private final DynamoDbClient dynamoDb;
    private final String tableName;
    private final AtomicLong idGenerator = new AtomicLong(System.currentTimeMillis());

    public DynamoDbNoteRepository(
            @Value("${aws.dynamodb.region}") String region,
            @Value("${aws.dynamodb.tableName}") String tableName) {
        this.tableName = tableName;
        this.dynamoDb = DynamoDbClient.builder()
                .region(Region.of(region))
                .credentialsProvider(InstanceProfileCredentialsProvider.create())
                .build();
    }

    @Override
    public Note save(Note note) {
        Long id = note.getId() != null ? note.getId() : idGenerator.incrementAndGet();
        LocalDateTime createdAt = note.getCreatedAt() != null ? note.getCreatedAt() : LocalDateTime.now();

        dynamoDb.putItem(PutItemRequest.builder()
                .tableName(tableName)
                .item(Map.of(
                        "id", AttributeValue.builder().s(String.valueOf(id)).build(),
                        "title", AttributeValue.builder().s(note.getTitle()).build(),
                        "content", AttributeValue.builder().s(note.getContent()).build(),
                        "createdAt", AttributeValue.builder().s(createdAt.toString()).build()
                ))
                .build());

        return new Note(id, note.getTitle(), note.getContent(), createdAt);
    }

    @Override
    public List<Note> findAll() {
        ScanResponse response = dynamoDb.scan(ScanRequest.builder()
                .tableName(tableName)
                .build());

        List<Note> notes = new ArrayList<>();
        for (Map<String, AttributeValue> item : response.items()) {
            notes.add(new Note(
                    Long.parseLong(item.get("id").s()),
                    item.get("title").s(),
                    item.get("content").s(),
                    LocalDateTime.parse(item.get("createdAt").s())
            ));
        }
        return notes;
    }
}