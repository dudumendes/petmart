package com.mendes.petmart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("analysis")
public class SentimentAnalysisController {
    private final ChatClient chatClient;

    public SentimentAnalysisController(
            ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String analysis() {
        String review = "This log is dirty";

        var prompt = String.format(
                "Analyze the sentiment of the following review: %s. Respond with 'Positive', 'Negative' or 'Neutral'.", review);

        return this.chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
