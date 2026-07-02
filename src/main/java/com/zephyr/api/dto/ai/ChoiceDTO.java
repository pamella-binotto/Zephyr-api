package com.zephyr.api.dto.ai;

public class ChoiceDTO {

   private MessageDTO message;

    public ChoiceDTO(MessageDTO message) {
        this.message = message;
    }

    public ChoiceDTO() {}

    public MessageDTO getMessage() {
        return message;
    }

    public void setMessage(MessageDTO message) {
        this.message = message;
    }


}
