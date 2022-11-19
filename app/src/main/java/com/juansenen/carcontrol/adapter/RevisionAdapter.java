package com.juansenen.carcontrol.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.juansenen.carcontrol.AddRevisionActivity;
import com.juansenen.carcontrol.R;
import com.juansenen.carcontrol.domain.Revision;

import java.util.List;

public class RevisionAdapter extends RecyclerView.Adapter<RevisionAdapter.RevisionHolder>{

    public List<Revision> revisionList;
    public Context context;

    public RevisionAdapter (Context context, List<Revision> revisionList){
        this.context = context;
        this.revisionList = revisionList;
    }

    @Override
    public RevisionHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcview_revisiones_item, parent, false);
        return new RevisionHolder(view);
    }

    @Override
    public void onBindViewHolder(RevisionHolder holder, int position) {
        holder.txtkmrev.setText(revisionList.get(position).getKmrevision());
        holder.mrkdone.setChecked(revisionList.get(position).isDone());
        holder.mrkoil.setChecked(revisionList.get(position).isAceite());

        //TODO Fechas??
        //TODO Terminar

    }

    @Override
    public int getItemCount() {
        return  revisionList.size();
    }

    public class RevisionHolder extends RecyclerView.ViewHolder {
        public TextView txtkmrev;
        public TextView txtfecha;
        public CheckBox mrkdone;
        public CheckBox mrkoil;
        public CheckBox mrkfilter;
        public CheckBox mrkwheels;
        public CheckBox mrkbrakedisc;
        public CheckBox mrkantifreeze;
        public CheckBox mrkwindowclen;

        public View parentview;

        public RevisionHolder(View view) {
            super(view);
            parentview = view;

            txtkmrev = view.findViewById(R.id.rcv_rev_item_km);
            txtfecha = view.findViewById(R.id.rcv_rev_item_date);
            mrkdone = view.findViewById(R.id.rcv_rev_item_done);
            mrkoil = view.findViewById(R.id.rcv_rev_item_oil);
            mrkfilter = view.findViewById(R.id.rc_rev_item_filter);
            mrkwheels = view.findViewById(R.id.rc_rev_item_wheels);
            mrkbrakedisc = view.findViewById(R.id.rc_rev_item_discbrake);
            mrkantifreeze = view.findViewById(R.id.rc_rev_item_antifreeze);
            mrkwindowclen = view.findViewById(R.id.rc_rev_item_washerfluid);

        }
    }


}