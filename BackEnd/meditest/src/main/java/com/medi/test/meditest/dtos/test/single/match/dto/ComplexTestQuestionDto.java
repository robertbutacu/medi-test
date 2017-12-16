package com.medi.test.meditest.dtos.test.single.match.dto;

import com.medi.test.meditest.dtos.test.ITestQuestion;
import javafx.util.Pair;

import java.util.List;

public class ComplexTestQuestionDto implements ITestQuestion{
    private List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> matches;

    public ComplexTestQuestionDto(List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> matches){
        this.matches = matches;
    }

    public List<Pair<SingleMatchQuestionDto, SingleMatchAnswerDto>> getMatches() {
        return matches;
    }
}
