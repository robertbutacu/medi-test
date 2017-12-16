package com.medi.test.meditest.dtos.test.single.match.dto;

import javafx.util.Pair;

import java.util.List;

public class ComplexTestQuestionDto {
    private List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> matches;

    public ComplexTestQuestionDto(List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> matches){
        this.matches = matches;
    }

}
