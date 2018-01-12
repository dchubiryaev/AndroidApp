package com.calculator.poverty.dog.povertycalculator.money;

/**
 * Created by DoG on 14.10.2017.
 */

public class ListMoney {
    int _id;
    String category;
    String comment;
    String date;
    String money;
    String flag;

    public ListMoney(){
    }

    public ListMoney(int id, String category, String comment, String date, String money, String flag) {
        this._id = id;
        this.category = category;
        this.comment = comment;
        this.date = date;
        this.money = money;
        this.flag = flag;
    }

    public ListMoney(String category, String comment, String date, String money, String flag) {
        this.category = category;
        this.comment = comment;
        this.date = date;
        this.money = money;
        this.flag = flag;
    }

    public int getID(){
        return _id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getCategory () {return category;}

    public void setCategory(String cat) {this.category = cat;}

    public String getComment(){
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate(){
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getMoney() {return money; }

    public void setMoney(String money){ this.money = money; }

    public String getFlag() {return flag; }

    public void setFlag(String flag) {
        this.flag = flag;
    }


}
