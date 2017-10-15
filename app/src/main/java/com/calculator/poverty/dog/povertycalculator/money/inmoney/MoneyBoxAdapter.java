package com.calculator.poverty.dog.povertycalculator.money.inmoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.calculator.poverty.dog.povertycalculator.R;
import com.calculator.poverty.dog.povertycalculator.money.ListMoney;

import java.util.ArrayList;

/**
 * Created by DoG on 15.10.2017.
 */

public class MoneyBoxAdapter extends BaseAdapter {

    ArrayList<ListMoney> data = new ArrayList<ListMoney>();
    Context context;

    public MoneyBoxAdapter(Context context, ArrayList<ListMoney> arr) {
        if (arr != null) {
            data = arr;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int num) {
        // TODO Auto-generated method stub
        return data.get(num);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int i, View someView, ViewGroup arg2) {
        //Получение объекта inflater из контекста
        LayoutInflater inflater = LayoutInflater.from(context);
        //Если someView (View из ListView) вдруг оказался равен
        //null тогда мы загружаем его с помошью inflater
        if (someView == null) {
            someView = inflater.inflate(R.layout.listview, arg2, false);
        }
        //Обявляем наши текствьюшки и связываем их с разметкой
        TextView category = (TextView) someView.findViewById(R.id.category);
        TextView money = (TextView) someView.findViewById(R.id.money);

        //Устанавливаем в каждую текствьюшку соответствующий текст
        // сначала заголовок
        category.setText(data.get(i).getThing());
        // потом подзаголовок
        money.setText(data.get(i).getMoney());
        return someView;
    }
}
