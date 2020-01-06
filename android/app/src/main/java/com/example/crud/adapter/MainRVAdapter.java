package com.example.crud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.repo.GithubRepo;

import java.util.ArrayList;

public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private ArrayList<GithubRepo> data = new ArrayList<>();
    private Context context;

    public MainRVAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public MainRVAdapter(Context context, ArrayList<GithubRepo> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public MainRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(this.layoutInflater.inflate(R.layout.item_activity_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainRVAdapter.ViewHolder holder, int position) {
        GithubRepo item = data.get(position);

        holder.id.setText(String.valueOf(item.getId()));
        holder.name.setText(String.valueOf(item.getName()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ArrayList<GithubRepo> getData() {
        return data;
    }

    public void setData(ArrayList<GithubRepo> data) {
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView id;
        private final TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
        }
    }
}
