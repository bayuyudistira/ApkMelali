package com.example.apkmelali_test.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apkmelali_test.R;
import com.example.apkmelali_test.activity.DetailOrderBusActivity;
import com.example.apkmelali_test.model.Bus;

import java.util.List;

public class DetailOrderBusAdapter extends BaseAdapter {

    private Context context;
    private List<Bus> busList;

    public DetailOrderBusAdapter(Context context, List<Bus> busList) {
        this.context = context;
        this.busList = busList;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.detail_order_bus_list_view, parent, false);
            holder = new ViewHolder();
            holder.titleView = convertView.findViewById(R.id.titleView);
            holder.quantityView = convertView.findViewById(R.id.quantityView);
            holder.subTotalView = convertView.findViewById(R.id.subTotalView);
            holder.editButton = convertView.findViewById(R.id.editButton);
            holder.deleteButton = convertView.findViewById(R.id.deleteButton);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Bus bus = busList.get(position);
        holder.titleView.setText(bus.getTitle());
        holder.quantityView.setText("Jumlah: " + bus.getQuantity());
        holder.subTotalView.setText("Rp. " + bus.getSubTotalPrice());

        holder.editButton.setOnClickListener(v -> {
            // Handle edit button click
            showEditDialog(position, bus);
        });

        holder.deleteButton.setOnClickListener(v -> {
            // Handle delete button click
            busList.remove(position); // Remove item from list
            notifyDataSetChanged(); // Notify adapter
            ((DetailOrderBusActivity) context).deleteItem(position); // Call activity method to update total price
        });

        return convertView;
    }

    private void showEditDialog(final int position, final Bus bus) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_edit_quantity, null);
        final EditText inputQuantity = dialogView.findViewById(R.id.inputQuantity);
        Button saveButton = dialogView.findViewById(R.id.saveButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);

        // Set current quantity in EditText
        inputQuantity.setText(String.valueOf(bus.getQuantity()));

        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        saveButton.setOnClickListener(v -> {
            // Save new quantity
            int newQuantity = Integer.parseInt(inputQuantity.getText().toString());
            bus.setQuantity(newQuantity);
            bus.setSubTotalPrice(newQuantity, bus.getPrice());
            notifyDataSetChanged(); // Update ListView
            ((DetailOrderBusActivity) context).editItem(position, newQuantity); // Call activity method to update total price
            dialog.dismiss();
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    static class ViewHolder {
        TextView titleView;
        TextView quantityView;
        TextView subTotalView;
        Button editButton;
        Button deleteButton;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Bus bus : busList) {
            totalPrice += bus.getSubTotalPrice();
        }
        return totalPrice;
    }
}
