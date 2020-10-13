package com.example.projectx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HoteldetailAdapter extends RecyclerView.Adapter<HoteldetailAdapter.viewholder> {
    Context context;
    List<Hoteldetail> hoteldetails;

    public HoteldetailAdapter(Context context, List<Hoteldetail> hoteldetails) {
        this.context = context;
        this.hoteldetails = hoteldetails;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customhoteldetail,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
    holder.hotelname.setText(hoteldetails.get(position).getHotelName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        CardView cardView;
        TextView hotelname;
        public viewholder(@NonNull View itemView){
            super(itemView);
         cardView=itemView.findViewById(R.id.hotelcardview);
         hotelname=itemView.findViewById(R.id.hotelname);
         imageView=itemView.findViewById(R.id.hotelimage);

        }
    }

}
