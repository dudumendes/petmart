package com.mendes.petmart.model;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.ModelType;

public class Tokenizer {
    public static int countTokens(String system, String user) {
        var registry = Encodings.newDefaultEncodingRegistry();
        var enc = registry.getEncodingForModel(ModelType.GPT_4O_MINI);
        return enc.countTokens(system + user);
    }
}
