package com.juansenen.carcontrol.adapter;

import static com.juansenen.carcontrol.db.Constans.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

//Adapter del RecyclerView de la MainActivity

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsHolder> {

    private List<Cars> carsList;
    private Context contex;

    public CarsAdapter(Context contex, List<Cars> carsList) {
        this.contex = contex;
        this.carsList = carsList;


    }
    //Creamos ViewHolder e inicializamos campos del RecyclerView
    @Override
    public CarsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcview_main_items, parent, false);
        return new CarsHolder(view);
    }
    //Establecemos los datos sobre un Item del Recycler
    @Override
    public void onBindViewHolder(CarsHolder holder, int position) {
        holder.txtregister.setText(carsList.get(position).getRegister());
        holder.txttrademrk.setText(carsList.get(position).getTrademark());
        holder.txtmodel.setText(carsList.get(position).getModel());
        holder.txtyear.setText(carsList.get(position).getYear());
        holder.txtkm.setText(String.valueOf(carsList.get(position).getKm()));

        //Si ruta imagen no esta cargada, se carga imagen por defecto
        if ((carsList.get(position).getImgPath()) == null){
            holder.imgencar.setImageResource(R.drawable.img_base);
        }else{
            holder.imgencar.setImageURI(Uri.parse(carsList.get(position).getImgPath()));
        }
    }
    //Obtenemos el tamaño del listado
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
        public ImageView imgencar;
        public ImageButton imgaddfuel;
        public ImageButton imgaddreview;
        public ImageButton imgaddparking;
        public ImageButton imgdeletecar;
        public ImageButton imgupdatecar;

        public View parentview;

        public CarsHolder(View view) {
            super(view);
            parentview = view;

            //Recuperamos los elementos del layout
            txtregister = view.findViewById(R.id.txtview_register);
            txttrademrk = view.findViewById(R.id.txtview_trdmark);
            txtmodel = view.findViewById(R.id.txtview_model);
            txtyear = view.findViewById(R.id.txtview_year);
            txtkm = view.findViewById(R.id.txtview_km);
            imgencar = view.findViewById(R.id.recview_imgcar);

            //Boton con imagen de añadir repostaje. Establecemos click listener y cogemos posicion
            //en el recycler para saber que coche es
            imgaddfuel = view.findViewById(R.id.imgbut_addfuel);
            imgaddfuel.setOnClickListener(view1 -> addFuelCar(getAdapterPosition()));

            //Boton con imagen de añadir revision. Establecemos click listener y cogemos posicion
            //en el recycler para saber que coche es
            imgaddreview = view.findViewById(R.id.imgbut_addreview);
            imgaddreview.setOnClickListener(view1 -> addReviewCar(getAdapterPosition()));

            //Boton con imagen de añadir aparcamiento. Establecemos click listener y cogemos posicion
            //en el recycler para saber que coche es
            imgaddparking = view.findViewById(R.id.imgbut_addpark);
            imgaddparking.setOnClickListener(view1 -> addCarPark(getAdapterPosition()));

            //Boton listado repostaje. Establecemos clisk listener y cogemos posicion
            //en el recycler para saber que coche es
            detailfuel = view.findViewById(R.id.butlistfuel);
            detailfuel.setOnClickListener(view1 -> seeDetailFuel(getAdapterPosition()));

            //Boton listado revisiones. Establecemos clisk listener y cogemos posicion
            //en el recycler para saber que coche es
            detailreviews = view.findViewById(R.id.butlistreviews);
            detailreviews.setOnClickListener(view1 -> seeDetailReviews(getAdapterPosition()));

            //Boton ir al utltimo parking. Establecemos clisk listener y cogemos posicion
            //en el recycler para saber que coche es
            goParking = view.findViewById(R.id.butlastpark);
            goParking.setOnClickListener(view1 -> goLastParking(getAdapterPosition()));

            //Boton con imagen para eliminar vehiculo. Establecemos clisk listener y cogemos posicion
            //en el recycler para saber que coche es
            imgdeletecar = view.findViewById(R.id.imgbut_delcar);
            imgdeletecar.setOnClickListener(view1 -> deleteCar(getAdapterPosition()));

            //Boton con imagen para actualizar vehiculo. Establecemos clisk listener y cogemos posicion
            //en el recycler para saber que coche es
            imgupdatecar = view.findViewById(R.id.imgbut_updatecar);
            imgupdatecar.setOnClickListener(view1 -> updateCar(getAdapterPosition()));

        }
        //Metodo boton añadir repostaje
        private void addFuelCar(int position) {
            Cars car = carsList.get(position);
            //Enviamos a la Activity añadir repostaje y le pasamos la matricula
            Intent intent = new Intent(contex, AddFuelActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);
        }
        //Metodo boton añadir revision
        private void addReviewCar(int position) {
            Cars cars = carsList.get(position);
            //Enviamos a la Activity añadir revision y le pasamos la matricula
            Intent intent = new Intent(contex, AddReviewActivity.class);
            intent.putExtra("register", cars.getRegister());
            contex.startActivity(intent);
        }
        //Metodo para ver Activity listado repostajes
        private void seeDetailFuel(int position) {
            Cars car = carsList.get(position);
            //Enviamos a la Activity listado repostaje y le pasamos la matricula
            Intent intent = new Intent(contex, DetailFuelActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);
        }
        //Metodo para ver Activity listado revisiones
        private void seeDetailReviews(int position) {
            Cars car = carsList.get(position);
            //Enviamos a la Activity listado revisiones y le pasamos la matricula
            Intent intent = new Intent(contex, DetailReviewActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);
        }
        //Metodo para añadir nuevo aparcamiento
        private void addCarPark(int position) {
            Cars car = carsList.get(position);
            //Enviamos a la Activity añadir aparcamiento y le pasamos la matricula
            Intent intent = new Intent(contex, MapParkActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);
        }
        //Metodo para Activity ver el ultimo aparcamiento registrado
        public void goLastParking(int position) {
            Cars car = carsList.get(position);
            //Enviamos a la Activity ultimo aparcamiento y le pasamos la matricula
            Intent intent = new Intent(contex, LastParkActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);
        }
        //Metodo para borrar vehiculo
        public void deleteCar(int position) {

            //Creamos dialogo de alerta con opciones
            AlertDialog.Builder builder = new AlertDialog.Builder(contex);
            builder.setMessage(R.string.delete_car_question)
                    .setTitle(R.string.delete_car)
                    .setPositiveButton(R.string.do_it, (dialog, id) -> {
                        //Al pulsar en OK eliminamos vehiculo de la base de datos
                        final AppDatabase db = Room.databaseBuilder(contex, AppDatabase.class, DATABASE_NAME)
                                .allowMainThreadQueries().build();
                        Cars car = carsList.get(position);
                        db.carsDAO().delete(car);

                        carsList.remove(position);
                        //Notificamos el cambio
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton((R.string.cancel), (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        //Metodo envio a Activity actualizar datos de vehiculo
        public void updateCar (int position) {
            Cars car = carsList.get(position);
            //Enviamos a la Activity actualizar y le pasamos la matricula
            Intent intent = new Intent(contex, UpdateCarActivity.class);
            intent.putExtra("register", car.getRegister());
            contex.startActivity(intent);

        }
    }
}