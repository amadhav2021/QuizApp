package com.madhavamish.quizapp;

public class Score {
    private int money;
    private String date;

    public Score(int money, String date){
        this.money = money;
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public String getDate() {
        return date;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
