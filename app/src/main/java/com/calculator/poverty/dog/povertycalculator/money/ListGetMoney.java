package com.calculator.poverty.dog.povertycalculator.money;

/**
 * Created by DoG on 14.10.2017.
 */

public class ListGetMoney {
    int _id;
    String category;
    String money;
    String date;

    public ListGetMoney(){
    }

    public ListGetMoney (int id, String category, String date, String money) {
        this._id = id;
        this.category = category;
        this.money = money;
        this.date = date;
    }

    public ListGetMoney(String category, String date, String money) {
        this.category = category;
        this.date = date;
        this.money = money;
    }

    public int getID(){
        return _id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getCategory () {return category;}

    public void setCategory(String cat) {this.category = cat;}

    public String getDate(){
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getMoney() {return money; }

    public void setMoney(String money){ this.money = money; }

}
