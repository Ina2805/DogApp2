package com.example.dogapp2.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.dogapp2.Data.BreedsRVAAdapter;
import com.example.dogapp2.Data.BreedsRVInterface;
import com.example.dogapp2.Model.DogModel;
import com.example.dogapp2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BreedsRVInterface {

    BottomNavigationView bottomNavigationView;
    ArrayList<DogModel> dogsModelArrayList = new ArrayList<>();

    String australianShepherd = "The Australian Shepherd, a lean, tough ranch dog, is one of those 'only in America'\u009D stories: a European breed perfected in California by way of Australia. Fixtures on the rodeo circuit, they are closely associated with the cowboy life. The Australian Shepherd, the cowboy's herding dog of choice, is a medium-sized worker with a keen, penetrating gaze in the eye. Aussie coats offer different looks, including merle (a mottled pattern with contrasting shades of blue or red). In all ways, they're the picture of rugged and agile movers of stock. Aussies exhibit an irresistible impulse to herd, anything: birds, dogs, kids. This strong work drive can make Aussies too much dog for a sedentary pet owner. Aussies are remarkably intelligent, quite capable of hoodwinking an unsuspecting novice owner. In short, this isn't the pet for everyone. But if you're looking for a brainy, tireless, and trainable partner for work or sport, your search might end here.";

    int[] dogImages = {R.drawable.australian_shepherd2, R.drawable.chiuauau2, R.drawable.german2,
            R.drawable.golden2, R.drawable.labrador2, R.drawable.newfoundland2, R.drawable.poodle2,
            R.drawable.rottweiler2, R.drawable.shih_tzu2};
    String[] dogDescription = {australianShepherd};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        setUpDogsModel();

        BreedsRVAAdapter adapter = new BreedsRVAAdapter(this, dogsModelArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.breeds);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.favourites:
                        startActivity(new Intent(getApplicationContext(), FavActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.breeds:
                        return true;

                    case R.id.training:
                        startActivity(new Intent(getApplicationContext(), TrainingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }

        });

    }
    public ArrayList<DogModel> getDogList(){

        return dogsModelArrayList;
    }
    private void setUpDogsModel()
    {
        String[] dogNames = getResources().getStringArray(R.array.array_dog_breeds_all_names);


        for (int i = 0; i<dogNames.length; i++)
        {
            dogsModelArrayList.add(new DogModel(dogNames[i], dogImages[i], australianShepherd,(""+i)));
        }
    }



    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, FavActivity.class);

        intent.putExtra("DOG_DESCRIPTION", dogsModelArrayList.get(position).getDescription());
        intent.putExtra("IMAGE", dogsModelArrayList.get(position).getImage());


        startActivity(intent);
    }
}