package com.medi.test.meditest.dtos;

public class AnswerDto {

    public String body;

    public boolean isCorrect;

    public boolean selected;
    public String fillin;
    private int matchAnswerId;
    private int selectedAnswer;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getFillin() {
        return fillin;
    }

    public void setFillin(String fillin) {
        this.fillin = fillin;
    }

    public int getMatchAnswerId() {
        return matchAnswerId;
    }

    public void setMatchAnswerId(int matchAnswerId) {
        this.matchAnswerId = matchAnswerId;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
