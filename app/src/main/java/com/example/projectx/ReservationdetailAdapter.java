package com.example.projectx;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationdetailAdapter extends RecyclerView.Adapter<ReservationdetailAdapter.viewholder> {
    Context context;
    List<Reservationdetail> reservationdetails;

    public ReservationdetailAdapter(Context context, List<Reservationdetail> reservationdetails) {
        this.context = context;
        this.reservationdetails = reservationdetails;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {

        public viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
