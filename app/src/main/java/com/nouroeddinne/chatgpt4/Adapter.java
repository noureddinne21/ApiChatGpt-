package com.nouroeddinne.chatgpt4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<ModelMsg> list = new ArrayList<ModelMsg>();;
    Context context;
    int fromInt = 1;

    public Adapter(List<ModelMsg> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        if (list.get(position).getFrom().equals("you")){
            return fromInt;
        }else {
            return 0;
        }

    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viwe;

        if (viewType == fromInt){
            viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_you, parent, false);
        }else{
            viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gpt, parent, false);
        }

        return new ViewHolder(viwe);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        ModelMsg m = list.get(position);
        holder.text.setText(m.getMessage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView);
        }
    }

























}
