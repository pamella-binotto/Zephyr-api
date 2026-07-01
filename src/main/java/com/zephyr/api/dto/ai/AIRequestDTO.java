package com.zephyr.api.dto.ai;

import java.util.List;

public class AIRequestDTO {

    private String model;
    private List<MessageDTO> messages;

    public AIRequestDTO(String model, List<MessageDTO> messages) {
        this.model = model;
        this.messages = messages;
    }

    public AIRequestDTO() {}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}
