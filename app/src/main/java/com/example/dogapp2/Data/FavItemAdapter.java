package com.example.dogapp2.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp2.Model.DatabaseDogs;
import com.example.dogapp2.R;

import java.util.List;

public class FavItemAdapter extends RecyclerView.Adapter<FavItemAdapter.ViewHolder>{

    List<FavItem> favItemList;
    Context context;
    DatabaseDogs dogBreedsDatabase;

    public FavItemAdapter(List<FavItem> favItemList, Context context, DatabaseDogs dogBreedsDatabase) {
        this.favItemList = favItemList;
        this.context = context;
        this.dogBreedsDatabase = dogBreedsDatabase;
    }

    @NonNull
    @Override
    public FavItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        dogBreedsDatabase = new DatabaseDogs(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavItemAdapter.ViewHolder holder, int position) {
        holder.favTextView.setText(favItemList.get(position).getItem_title());
        holder.favImageView.setImageResource(favItemList.get(position).getItem_image());
    }

    @Override
    public int getItemCount() {
        return favItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView favImageView;
        TextView favTextView;
        ImageButton favImageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favImageView = itemView.findViewById(R.id.imageView);
            favTextView = itemView.findViewById(R.id.textView);
            favImageButton = itemView.findViewById(R.id.imageButton);

            favImageButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int position = getAdapterPosition();
                    final FavItem favItem = favItemList.get(position);
                    dogBreedsDatabase.remove_fav(favItem.getKey_id());
                    removeItem(position);
                }
            });
        }
        }

    private void removeItem(int position) {
        favItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, favItemList.size());
    }

}
