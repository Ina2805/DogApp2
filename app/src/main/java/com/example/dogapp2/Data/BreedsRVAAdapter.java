package com.example.dogapp2.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp2.Model.DatabaseDogs;
import com.example.dogapp2.Model.DogModel;
import com.example.dogapp2.R;

import java.util.ArrayList;

public class BreedsRVAAdapter extends RecyclerView.Adapter<BreedsRVAAdapter.ViewHolder> {

        private BreedsRVInterface breedsRVInterface;
        Context context;
        ArrayList<DogModel> dogsModel;
        ImageButton imageButton;
        private DatabaseDogs dogBreedsDatabase;

        public BreedsRVAAdapter(Context context, ArrayList<DogModel> dogsModel, BreedsRVInterface breedsRVInterface) {
            this.context = context;
            this.dogsModel = dogsModel;
            this.breedsRVInterface = breedsRVInterface;

        }
        @NonNull
        @Override
        public BreedsRVAAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            dogBreedsDatabase = new DatabaseDogs(context);
            SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            boolean firstStart = prefs.getBoolean("firstStart", true);
            if (firstStart) {
                createTableOnFirstStart();
            }
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.fav_item, parent, false);
            return new BreedsRVAAdapter.ViewHolder(view);
        }



        @Override
        public void onBindViewHolder(@NonNull BreedsRVAAdapter.ViewHolder holder, int position) {
            final DogModel dogItem = dogsModel.get(position);

            readCursorData(dogItem, holder);
            holder.name.setText(dogsModel.get(position).getDogName());
            holder.image.setImageResource(dogsModel.get(position).getImage());
        }


        @Override
        public int getItemCount() {
            return dogsModel.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView name;
            ImageView image;
            ImageButton imageButton;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.textView);
                image = itemView.findViewById(R.id.imageView);
                imageButton = itemView.findViewById(R.id.imageButton);

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        DogModel dogsModel1 = dogsModel.get(position);


                        if (dogsModel1.getFavStatus().equals("0"))
                        {
                            dogsModel1.setFavStatus("1");
                            dogBreedsDatabase.insertIntoDatabase(dogsModel1.getTitle(), dogsModel1.getImageResourse(),dogsModel1.getKey_id(), dogsModel1.getFavStatus(), dogsModel1.getDescription());
                            imageButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                        } else {
                            dogsModel1.setFavStatus("0");
                            dogBreedsDatabase.remove_fav(dogsModel1.getKey_id());
                            imageButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24);

                        }
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (breedsRVInterface != null)
                        {
                            int position = getAdapterPosition();

                            if(position != RecyclerView.NO_POSITION)
                            {
                                breedsRVInterface.onItemClick(position);
                            }
                        }
                    }
                });


            }


        }
        private void readCursorData(DogModel dogItem, ViewHolder viewHolder) {
            Cursor cursor = dogBreedsDatabase.read_all_data(dogItem.getKey_id());
            SQLiteDatabase db = dogBreedsDatabase.getReadableDatabase();
            try {
                while (cursor.moveToNext()) {
                    int m=cursor.getColumnIndex(DatabaseDogs.FAVOURITE_STATUS);
                    String item_fav_status = cursor.getString(m);

                    //check fav status
                    if (item_fav_status != null && item_fav_status.equals("1")) {
                        viewHolder.imageButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                    } else if (item_fav_status != null && item_fav_status.equals("0")) {
                        viewHolder.imageButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24);
                    }
                }
            } finally {
                if (cursor != null && cursor.isClosed())
                    cursor.close();
                db.close();
            }
        }

        private void createTableOnFirstStart() {
            dogBreedsDatabase.insertEmpty();

            SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart", false);
            editor.apply();
        }


}
