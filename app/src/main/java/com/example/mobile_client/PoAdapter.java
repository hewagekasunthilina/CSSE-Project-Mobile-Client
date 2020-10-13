package com.example.mobile_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PoAdapter extends ArrayAdapter<Po> {

    Context context;
    List<Po> arrayListPo;

    public PoAdapter(@NonNull Context context, List<Po> arrayListPo) {
        super(context, R.layout.custom_list_po,arrayListPo);

        this.context = context;
        this.arrayListPo = arrayListPo;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_po,null,true);

        TextView OrderID = view.findViewById(R.id.OrderID);
        TextView RefNo = view.findViewById(R.id.RefNo);
        TextView Material = view.findViewById(R.id.Material);
        TextView Description = view.findViewById(R.id.Description);
        TextView Supplier = view.findViewById(R.id.Supplier);
        TextView Price = view.findViewById(R.id.Price);
        TextView Quantity = view.findViewById(R.id.Quantity);
        TextView Site = view.findViewById(R.id.Site);
        TextView DelDate = view.findViewById(R.id.DelDate);

        OrderID.setText(arrayListPo.get(position).getOrderID().toString());
        RefNo.setText(arrayListPo.get(position).getRefNo());
        Material.setText(arrayListPo.get(position).getMaterial());
        Description.setText(arrayListPo.get(position).getDescription());
        Supplier.setText(arrayListPo.get(position).getSupplier());
        Price.setText(arrayListPo.get(position).getPrice().toString());
        Quantity.setText(arrayListPo.get(position).getQuantity().toString());
        Site.setText(arrayListPo.get(position).getSite());
        DelDate.setText(arrayListPo.get(position).getDelDate());

        return view;
    }
}
