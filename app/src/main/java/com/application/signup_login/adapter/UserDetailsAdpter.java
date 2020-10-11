package com.application.signup_login.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.signup_login.R;
import com.application.signup_login.model.Item;
import com.application.signup_login.utils.RecyclerViewClickListener;
import com.application.signup_login.model.UserDetails;

import java.util.ArrayList;

public class UserDetailsAdpter extends  RecyclerView.Adapter<UserDetailsAdpter.ViewHolder>{

    private ArrayList<Item> itemArrayList = new ArrayList<>();
    private RecyclerViewClickListener mListener;

    private Context mContext;

    public UserDetailsAdpter(ArrayList<Item> itemArrayList, Context mContext, RecyclerViewClickListener listener) {
        this.itemArrayList = itemArrayList;
        this.mContext = mContext;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.email.setText(itemArrayList.get(position).getEmail());
        holder.fname.setText(itemArrayList.get(position).getFname());
        holder.lname.setText(itemArrayList.get(position).getLname());

    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCount: "+itemArrayList.size());
        return itemArrayList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView email, fname, lname;
        LinearLayout parentLayout;

        private RecyclerViewClickListener mListener;

        public ViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            parentLayout = itemView.findViewById(R.id.parent_linear_layout);

            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}
