package com.calculator.poverty.dog.povertycalculator.money;

/**
 * Created by DoG on 12.01.2018.
 */

public class CheckingCorrect {
    public CheckingCorrect(){
    }

    //Need checkCorrectData on zero. Zero there is true!!!
    public boolean checkNumber (String num){
        int number;
        try{
            number = Integer.parseInt(num);
        } catch (Exception ex) {
            return false;
        }

        if (number % 1 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkComment (String com) {
        if (com.length()!=0){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkItem(ListMoney listMoney) {
        try{
            String thing = listMoney.getComment();
        } catch (Exception ex) {
            return false;
        } return true;
    }
}
