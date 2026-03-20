package com.omidmohebbise.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public ChatController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    @GetMapping("/life-principles")
    public String ask(@RequestParam int age, @RequestParam String question) {
        String ageGroup = ageGroupFor(age);

        QuestionAnswerAdvisor advisor = QuestionAnswerAdvisor.builder(vectorStore)
                .searchRequest(
                        SearchRequest.builder()
                                .topK(3)
                                .filterExpression("ageGroup == '" + ageGroup + "' && category == 'life-principles'")
                                .build()
                )
                .build();

        String userPrompt = """
                The person asking is %d years old.
                Answer the question using the life principles for this age group only.

                Question: %s
                """.formatted(age, question);

        return chatClient.prompt()
                .user(userPrompt)
                .advisors(advisor)
                .call()
                .content();
    }

    private String ageGroupFor(int age) {
        if (age >= 2 && age < 5) return "2-5";
        if (age >= 5 && age < 10) return "5-10";
        if (age >= 10 && age < 16) return "10-16";
        if (age >= 16 && age < 21) return "16-21";
        if (age >= 21 && age < 25) return "21-25";
        if (age >= 25 && age < 30) return "25-30";
        if (age >= 30 && age < 35) return "30-35";
        if (age >= 35 && age < 45) return "35-45";
        if (age >= 45 && age < 55) return "45-55";
        if (age >= 55 && age < 65) return "55-65";
        if (age >= 65 && age < 75) return "65-75";
        return "76+";
    }
}
