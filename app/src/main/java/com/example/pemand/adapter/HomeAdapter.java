package com.example.pemand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pemand.R;
import com.example.pemand.model.Data;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

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
        TextView title;
        TextView subtitle;
        ImageView image;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder = new Holder();
        final View rowView;

        rowView = inflater.inflate(R.layout.adapter_home, null);
        holder.title = (TextView) rowView.findViewById(R.id.title);
        holder.subtitle = (TextView) rowView.findViewById(R.id.subtitle);
        holder.image = (ImageView) rowView.findViewById(R.id.icon);

        holder.title.setText(data.get(position).getNama());
        holder.subtitle.setText(String.valueOf(data.get(position).getNilai()));
        Picasso.get().load(data.get(position).getGambar()).into(holder.image);

        return rowView;
    }
}
