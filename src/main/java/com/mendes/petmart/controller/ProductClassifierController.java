package com.mendes.petmart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.DefaultChatOptionsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("classifier")
public class ProductClassifierController {
    private final ChatClient chatClient;

    public ProductClassifierController(
            ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String classify(String prompt) {
//        var system = "You are product categorizer and should answer only the category's name of the product";

        var system = """
                You are product categorizer and should answer only the category's name of the product
                
                Choose your answer from the list :
                
                1. Pet Food
                2. Pet Toys
                3. Pet Grooming Products
                4. Pet Accessories
                5. Pet Health & Wellness
                6. Others
                
                ##### Use example:
                
                prompt: color ball
                answer: Pet Toys
                """;

        return this.chatClient.prompt()
                .system(system)
                .user(prompt)
                .options(ChatOptions.builder()
                        .temperature(0.5)
                        .build())
                .call()
                .content();
    }
}
