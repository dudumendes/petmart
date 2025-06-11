package com.mendes.petmart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("generator")
public class ProductGeneratorController {
    private final ChatClient chatClient;

    public ProductGeneratorController(
            @Qualifier("gpt-4o-mini") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping
    public String productGeneration() {
        var prompt = "Generate 5 pet shop products";

        return this.chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
