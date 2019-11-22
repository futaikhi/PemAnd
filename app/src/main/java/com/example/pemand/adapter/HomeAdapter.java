package com.example.pemand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pemand.R;
import com.example.pemand.model.Data;

import java.util.ArrayList;

public class HomeAdapter extends BaseAdapter {
    ArrayList<Data> data;
    Context context;
    private static LayoutInflater inflater = null;

    public HomeAdapter(AppCompatActivity appCompatActivity, ArrayList<Data> data){
        this.context = appCompatActivity;
        this.data = data;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Holder{
        TextView os_text;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        final View rowView;

        rowView = inflater.inflate(R.layout.adapter_home, null);
        holder.os_text = (TextView) rowView.findViewById(R.id.dataHome);

        holder.os_text.setText(data.get(position).getNama());

        return rowView;
    }
}
