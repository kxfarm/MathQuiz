package com.example.mathquiz;

import java.util.Random;

public class Question {

    private int firstNumber;
    private int secondNumber;
    private int answer;
    private int []answerArray;
    private int answerPosition;
    private int maxValue;
    private String questionLine;

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public String getQuestionLine() {
        return questionLine;
    }

    public void setQuestionLine(String questionLine) {
        this.questionLine = questionLine;
    }

    public Question(int maxValue){
        this.maxValue = maxValue;
        Random randomNumberMaker = new Random();

        this.firstNumber = randomNumberMaker.nextInt(maxValue);
        this.secondNumber= randomNumberMaker.nextInt(maxValue);
        this.answer=this.firstNumber+this.secondNumber;
        this.questionLine =  firstNumber + " + " + secondNumber + " = ";

        this.answerPosition=randomNumberMaker.nextInt(4);
        this.answerArray = new int[]{0,1,2,3};
        this.answerArray[0] = answer + 5;
        this.answerArray[1] = answer + 7;
        this.answerArray[2] = answer - 5;
        this.answerArray[3] = answer + 7;
        this.answerArray = shuffleArray(this.answerArray);

        answerArray[answerPosition] = answer;
    }

    private int [] shuffleArray(int [] array){
        int index,temp;
        Random randomNumberGenerator = new Random();

        for(int i = array.length-1; i>0 ; i--){
            index = randomNumberGenerator.nextInt(i+1);
            temp=array[index];
            array[index]=array[i];
            array[i]=temp;
        }
        return array;
    }
}
