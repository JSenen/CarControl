package com.juansenen.carcontrol.adapter;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.juansenen.carcontrol.AddFuelActivity;
import com.juansenen.carcontrol.AddReviewActivity;
import com.juansenen.carcontrol.DetailFuelActivity;
import com.juansenen.carcontrol.DetailReviewActivity;
import com.juansenen.carcontrol.LastParkActivity;
import com.juansenen.carcontrol.MapParkActivity;
import com.juansenen.carcontrol.R;
import com.juansenen.carcontrol.UpdateCarActivity;
import com.juansenen.carcontrol.db.AppDatabase;
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
        holder.txtyear.setText(carsList.get(position).getYear());
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
        public Button detailfuel;
        public Button detailreviews;
        public Button goParking;
        public ImageButton imgaddfuel;
        public ImageButton imgaddreview;
        public ImageButton imgaddparking;
        public ImageButton imgdeletecar;
        public ImageButton imgupdatecar;

        public View parentview;

        public CarsHolder(View view) {
            super(view);
            parentview = view;

            txtregister = view.findViewById(R.id.txtview_register);
            txttrademrk = view.findViewById(R.id.txtview_trdmark);
            txtmodel = view.findViewById(R.id.txtview_model);
            txtyear = view.findViewById(R.id.txtview_year);
            txtkm = view.findViewById(R.id.txtview_km);

            imgaddfuel = view.findViewById(R.id.imgbut_addfuel);
            imgaddfuel.setOnClickListener(view1 -> addFuelCar(getAdapterPosition()));

            imgaddreview = view.findViewById(R.id.imgbut_addreview);
            imgaddreview.setOnClickListener(view1 -> addReviewCar(getAdapterPosition()));

            imgaddparking = view.findViewById(R.id.imgbut_addpark);
            imgaddparking.setOnClickListener(view1 -> addCarPark(getAdapterPosition()));

            detailfuel = view.findViewById(R.id.butlistfuel);
            detailfuel.setOnClickListener(view1 -> seeDetailFuel(getAdapterPosition()));

            detailreviews = view.findViewById(R.id.butlistreviews);
            detailreviews.setOnClickListener(view1 -> seeDetailReviews(getAdapterPosition()));

            goParking = view.findViewById(R.id.butlastpark);
            goParking.setOnClickListener(view1 -> goLastParking(getAdapterPosition()));

            imgdeletecar = view.findViewById(R.id.imgbut_delcar);
            imgdeletecar.setOnClickListener(view1 -> deleteCar(getAdapterPosition()));

            imgupdatecar = view.findViewById(R.id.imgbut_updatecar);
            imgupdatecar.setOnClickListener(view1 -> updateCar(getAdapterPosition()));




        }

        private void addFuelCar(int position) {
            Cars car = carsList.get(position);

            Intent intent = new Intent(contex, AddFuelActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);
        }
        private void addReviewCar(int position) {
            Cars cars = carsList.get(position);

            Intent intent = new Intent(contex, AddReviewActivity.class);
            intent.putExtra("register", cars.getRegister());
            contex.startActivity(intent);
        }

        private void seeDetailFuel(int position) {
            Cars car = carsList.get(position);

            Intent intent = new Intent(contex, DetailFuelActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);
        }

        private void seeDetailReviews(int position) {
            Cars car = carsList.get(position);

            Intent intent = new Intent(contex, DetailReviewActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);
        }

        private void addCarPark(int position) {
            Cars car = carsList.get(position);

            Intent intent = new Intent(contex, MapParkActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);
        }

        public void goLastParking(int position) {
            Cars car = carsList.get(position);

            Intent intent = new Intent(contex, LastParkActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);
        }

        public void deleteCar(int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(contex);
            builder.setMessage(R.string.delete_car_question)
                    .setTitle(R.string.delete_car)
                    .setPositiveButton(R.string.do_it, (dialog, id) -> {
                        final AppDatabase db = Room.databaseBuilder(contex, AppDatabase.class, DATABASE_NAME)
                                .allowMainThreadQueries().build();
                        Cars car = carsList.get(position);
                        db.carsDAO().delete(car);

                        carsList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton((R.string.cancel), (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        public void updateCar (int position) {
            Cars car = carsList.get(position);

            Intent intent = new Intent(contex, UpdateCarActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);

        }
    }
}