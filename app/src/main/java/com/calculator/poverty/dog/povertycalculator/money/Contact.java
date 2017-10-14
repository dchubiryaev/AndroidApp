package com.calculator.poverty.dog.povertycalculator.money;

/**
 * Created by DoG on 14.10.2017.
 */

public class Contact {
    int _id;
    String _name;
    static String _phone_number;

    public Contact(){
    }

    public Contact(int id, String name, String _phone_number){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }

    public Contact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public static String getPhoneNumber(){
        return _phone_number;
    }

    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}
