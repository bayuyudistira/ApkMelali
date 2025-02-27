package com.example.apkmelali_test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apkmelali_test.R;
import com.example.apkmelali_test.model.Bus;
import com.example.apkmelali_test.model.PemesananTiket;

import java.util.ArrayList;
import java.util.List;

public class TiketBus_CustomAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    public static List<Bus> busList;
    private TextView totalTiketTextView;
    public ArrayList<PemesananTiket> pemesananTikets = new ArrayList<PemesananTiket>();

    public TiketBus_CustomAdapter(Context context, int layout, List<Bus> busList, TextView totalTiketTextView, ArrayList<PemesananTiket> pemesananTiket) {
        this.context = context;
        this.layout = layout;
        TiketBus_CustomAdapter.busList = busList;
        this.totalTiketTextView = totalTiketTextView;
        this.pemesananTikets = pemesananTiket;
    }

    @Override
    public int getCount() {
        return busList.size();
    }

    @Override
    public Object getItem(int position) {
        return busList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.imageIcon = convertView.findViewById(R.id.imageIcon);
            holder.textTitle = convertView.findViewById(R.id.textTitle);
            holder.textSubtitle = convertView.findViewById(R.id.textSubtitle);
            holder.priceText = convertView.findViewById(R.id.priceText);
            holder.quantityText = convertView.findViewById(R.id.quantityText);
            holder.addButton = convertView.findViewById(R.id.addButton);
            holder.subtractButton = convertView.findViewById(R.id.subtractButton);
            holder.quantityView = convertView.findViewById(R.id.quantityView); // TextView for quantity
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bus bus = busList.get(position);
        holder.imageIcon.setImageResource(bus.getImage());
        holder.textTitle.setText(bus.getTitle());
        holder.textSubtitle.setText(bus.getSubtitle());
        holder.priceText.setText("Rp. " + bus.getPrice());
        holder.quantityText.setText(String.valueOf(bus.getQuantity()));
        holder.quantityView.setText("Qty: " + bus.getQuantity()); // Set quantity TextView

        holder.addButton.setOnClickListener(v -> {
//            int quantity = bus.getQuantity();
//            bus.setQuantity(quantity + 1);
//            holder.quantityText.setText(String.valueOf(bus.getQuantity()));
//            holder.quantityView.setText("Qty: " + bus.getQuantity()); // Update quantity TextView
            pemesananTikets.get(position).setQuantity(pemesananTikets.get(position).getQuantity()+1);
            pemesananTikets.get(position).setBus(busList.get(position));
            holder.quantityText.setText(String.valueOf(pemesananTikets.get(position).getQuantity()));
            holder.quantityView.setText("Qty: " + (pemesananTikets.get(position).getQuantity()));
            calculateTotalPrice();
        });

        holder.subtractButton.setOnClickListener(v -> {
            int quantity = bus.getQuantity();
//            if (quantity > 0) {
////                bus.setQuantity(quantity - 1);
////                holder.quantityText.setText(String.valueOf(bus.getQuantity()));
////                holder.quantityView.setText("Qty: " + bus.getQuantity()); // Update quantity TextView
//            }
            if(pemesananTikets.get(position).getQuantity() > 0){
                pemesananTikets.get(position).setQuantity(pemesananTikets.get(position).getQuantity()-1);
                pemesananTikets.get(position).setBus(busList.get(position));
                holder.quantityText.setText(String.valueOf(pemesananTikets.get(position).getQuantity()));
                holder.quantityView.setText("Qty: " + (pemesananTikets.get(position).getQuantity()));
                calculateTotalPrice();
            }
        });

        return convertView;
    }

    public List<Bus> getBusList() {
        return busList;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
//        for (Bus bus : busList) {
//            bus.setSubTotalPrice(bus.getQuantity(), bus.getPrice());
//            totalPrice += bus.getSubTotalPrice();
//        }
        for(PemesananTiket tiket : pemesananTikets){
            totalPrice += tiket.getQuantity() * tiket.getBus().getPrice();
        }
        totalTiketTextView.setText("Total Tiket : Rp. " + totalPrice);
        return totalPrice;
    }

    static class ViewHolder {
        TextView quantityView;
        ImageView imageIcon;
        TextView textTitle;
        TextView textSubtitle;
        TextView priceText;
        TextView quantityText;
        ImageButton addButton;
        ImageButton subtractButton;
    }
}