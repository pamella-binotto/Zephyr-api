package com.zephyr.api.dto.ai;

import java.util.List;

public class AIResponseDTO {

    private List<ChoiceDTO> choices;

    public AIResponseDTO(List<ChoiceDTO> choices) {
        this.choices = choices;
    }

    public AIResponseDTO() {}

    public List<ChoiceDTO> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceDTO> choices) {
        this.choices = choices;
    }

    public String getContent() {

        if (choices == null || choices.isEmpty()) {
            return "Nenhuma resposta foi retornada pela IA.";
        }
        return choices.get(0)
                .getMessage()
                .getContent();
    }
}
