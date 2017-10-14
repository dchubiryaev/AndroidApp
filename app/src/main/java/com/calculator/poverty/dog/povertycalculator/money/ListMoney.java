package com.calculator.poverty.dog.povertycalculator.money;

import java.util.Date;

/**
 * Created by DoG on 14.10.2017.
 */

public class ListMoney {
    int _id;
    String category;
    String thing;
    String date;
    String money;
    String spent;

    public ListMoney(){
    }

    public ListMoney(int id, String category, String thing, String date, String money, String spent) {
        this._id = id;
        this.category = category;
        this.thing = thing;
        this.date = date;
        this.money = money;
        this.spent = spent;
    }

    public ListMoney(String category, String thing, String date, String money, String spent) {
        this.category = category;
        this.thing = thing;
        this.date = date;
        this.money = money;
        this.spent = spent;
    }

    public int getID(){
        return _id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getCategory () {return category;}

    public void setCategory(String cat) {this.category = cat;}

    public String getThing(){
        return thing;
    }

    public void setThing (String thing) {
        this.thing = thing;
    }

    public String getDate(){
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getMoney() {return money; }

    public void setMoney(String money){ this.money = money; }

    public String getSpent() {return spent; }

    public void setSpent (String spent) {
        this.spent = spent;
    }


}
