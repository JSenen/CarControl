package com.juansenen.carcontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.juansenen.carcontrol.R;
import com.juansenen.carcontrol.domain.Fuel;

import java.util.List;

public class FuelAdapter extends RecyclerView.Adapter<FuelAdapter.FuelHolder> {

    private List<Fuel> fuelList;
    private Context context;

    public FuelAdapter(Context context, List<Fuel> fuelList){
        this.context = context;
        this.fuelList = fuelList;
    }
    @Override
    public FuelAdapter.FuelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcview_fuel_item, parent, false);
        return new FuelHolder(view);
    }
    @Override
    public void onBindViewHolder(FuelAdapter.FuelHolder holder, int position) {
        holder.txtidfuel.setText(fuelList.get(position).getIdFuelCar());
        holder.txtpricefuel.setText(String.valueOf(fuelList.get(position).getPrice()));
        holder.txtlitrefuel.setText(String.valueOf(fuelList.get(position).getLitres()));
    }
    @Override
    public int getItemCount() {
        return fuelList.size();
    }

    public class FuelHolder extends RecyclerView.ViewHolder{
        public TextView txtidfuel;
        public TextView txtpricefuel;
        public TextView txtlitrefuel;

        public View parentview;

        public FuelHolder(View view){
            super(view);
            parentview = view;

            txtidfuel = view.findViewById(R.id.rcw_itemfuel_id);
            txtpricefuel = view.findViewById(R.id.rcw_itemfuel_price);
            txtlitrefuel = view.findViewById(R.id.rcw_itemfuel_litre);

        }
    }
}
