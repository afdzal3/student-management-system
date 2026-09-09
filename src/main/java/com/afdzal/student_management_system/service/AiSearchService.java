package com.afdzal.student_management_system.service;

import com.afdzal.student_management_system.model.Student;
import com.afdzal.student_management_system.repo.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AiSearchService {

    private final ChatClient chatClient;
    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    public AiSearchService(ChatClient.Builder chatClientBuilder, StudentRepository studentRepository) {
        this.chatClient = chatClientBuilder
                .defaultOptions(GoogleGenAiChatOptions.builder()
                        .model("gemini-3.6-flash")
                        .build())
                .build();
        this.studentRepository = studentRepository;
        
        // Initialize ObjectMapper and register the Java 8 Time module for LocalDateTime
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public List<Student> searchStudentsWithAi(String naturalLanguageQuery) {
        List<Student> allStudents = studentRepository.findAll();

        try {
            // Convert student objects into a valid JSON string (including dates)
            String studentsJson = objectMapper.writeValueAsString(allStudents);

            String prompt = "You are a database search assistant. Here is the full list of students in JSON format: " + studentsJson + 
                            "\nBased on this user query: '" + naturalLanguageQuery + 
                            "', analyze the name, address, and other fields. Return ONLY a comma-separated list of matching student IDs (e.g., '1, 3'). If none match, return 'NONE'. Do not include any extra text.";

            String aiResponse = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            if (aiResponse == null || aiResponse.contains("NONE")) {
                return List.of();
            }

            // Safe ID matching check
            return allStudents.stream()
                    .filter(s -> {
                        String idStr = String.valueOf(s.getId());
                        return java.util.regex.Pattern.compile("\\b" + idStr + "\\b").matcher(aiResponse).find();
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.err.println("⚠️ AI Service Error: " + e.getMessage());
            System.out.println("🔄 Falling back to keyword search...");
            
            String cleanQuery = naturalLanguageQuery
                    .replaceAll("(?i)\\b(find|show|me|all|students|from|in|where|named|which|is|are|the)\\b", "")
                    .trim();
            
            if (cleanQuery.isEmpty()) {
                return studentRepository.findAll();
            }
            
            String firstKeyword = cleanQuery.split(" ")[0];
            return studentRepository.findByNameContainingIgnoreCase(firstKeyword);
        }
    }
}