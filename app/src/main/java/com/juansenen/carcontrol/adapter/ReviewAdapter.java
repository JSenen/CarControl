package com.juansenen.carcontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juansenen.carcontrol.R;
import com.juansenen.carcontrol.domain.Reviews;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    private List<Reviews> reviewsList;
    private Context context;

    public ReviewAdapter (Context context, List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
        this.context = context;
    }


    @Override
    public ReviewAdapter.ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcview_review_item, parent, false);
        return new ReviewHolder(view);
    }
    @Override
    public void onBindViewHolder(ReviewAdapter.ReviewHolder holder, int position) {
        holder.txtdate.setText(reviewsList.get(position).getFecha());
        holder.txtkm.setText(String.valueOf(reviewsList.get(position).getKmreview()));
        holder.txtprice.setText(String.valueOf(reviewsList.get(position).getPrice()));
        holder.chkOil.setChecked(reviewsList.get(position).isOil());
        holder.chkLiquid.setChecked(reviewsList.get(position).isBrakeLiquid());
        holder.chkWipers.setChecked(reviewsList.get(position).isWipers());
        holder.chkFreeze.setChecked(reviewsList.get(position).isFreeze());
        holder.chkWheels.setChecked(reviewsList.get(position).isWheels());
        holder.chkBrakes.setChecked(reviewsList.get(position).isBrakes());



    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder {

        public TextView txtdate;
        public TextView txtkm;
        public TextView txtprice;
        public CheckBox chkOil;
        public CheckBox chkBrakes;
        public CheckBox chkLiquid;
        public CheckBox chkWheels;
        public CheckBox chkFreeze;
        public CheckBox chkWipers;
        public View parentview;

        public ReviewHolder (View view){
            super(view);
            parentview = view;

            txtdate = view.findViewById(R.id.txt_review_item_date);
            txtkm = view.findViewById(R.id.txt_review_item_km);
            txtprice = view.findViewById(R.id.txt_review_item_price);
            chkOil = view.findViewById(R.id.chk_review_item_oil);
            chkBrakes = view.findViewById(R.id.chk_review_item_brakes);
            chkLiquid = view.findViewById(R.id.chk_review_item_liquidbrake);
            chkWheels = view.findViewById(R.id.chk_review_item_ruedas);
            chkFreeze = view.findViewById(R.id.chk_review_item_freeze);
            chkWipers = view.findViewById(R.id.chk_review_item_whaser);

        }
    }

}
