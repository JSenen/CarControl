package com.juansenen.carcontrol.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.juansenen.carcontrol.AddRevisionActivity;
import com.juansenen.carcontrol.DetailCarActivity;
import com.juansenen.carcontrol.R;
import com.juansenen.carcontrol.domain.Cars;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsHolder> {

    private List<Cars> carsList;
    private Context contex;

    public CarsAdapter(Context contex, List<Cars> carsList) {
        this.contex = contex;
        this.carsList = carsList;

    }

    @Override
    public CarsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcview_main_items, parent, false);
        return new CarsHolder(view);
    }
    @Override
    public void onBindViewHolder(CarsHolder holder, int position) {
        holder.txtregister.setText(carsList.get(position).getRegister());
        holder.txttrademrk.setText(carsList.get(position).getTrademark());
        holder.txtmodel.setText(carsList.get(position).getModel());
        holder.txtyear.setText(String.valueOf(carsList.get(position).getYear()));
        holder.txtkm.setText(String.valueOf(carsList.get(position).getKm()));


    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

    public class CarsHolder extends RecyclerView.ViewHolder {
        public TextView txtregister;
        public TextView txttrademrk;
        public TextView txtmodel;
        public TextView txtyear;
        public TextView txtkm;
        public Button seeDetailsBut;
        public Button addRevBut;
        public View parentview;

        public CarsHolder(View view) {
            super(view);
            parentview = view;

            txtregister = view.findViewById(R.id.txtview_register);
            txttrademrk = view.findViewById(R.id.txtview_trdmark);
            txtmodel = view.findViewById(R.id.txtview_model);
            txtyear = view.findViewById(R.id.txtview_year);
            txtkm = view.findViewById(R.id.txtview_km);

            seeDetailsBut = view.findViewById(R.id.but_details);
            seeDetailsBut.setOnClickListener(view1 -> seeDetailsCar(getAdapterPosition()));

            addRevBut = view.findViewById(R.id.but_addrev);
            addRevBut.setOnClickListener(view1 -> addRevCar(getAdapterPosition()));

        }
    }
    private void seeDetailsCar(int position) {
        Cars car = carsList.get(position);

        Intent intent = new Intent(contex, DetailCarActivity.class);
        intent.putExtra("register",car.getRegister());
        contex.startActivity(intent);
    }
    private void addRevCar(int position) {
        Cars car = carsList.get(position);

        Intent intent = new Intent(contex, AddRevisionActivity.class);
        intent.putExtra("register",car.getRegister());
        contex.startActivity(intent);
    }
}