package com.example.user.californiazoo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "name";

    private List<Animals> myAnimals = new ArrayList<Animals>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        populateAnimalList();
        populateListView();
        onClickList();
    }

    private void populateAnimalList()
    {
        myAnimals.add(new Animals("Elephant", R.drawable.elephant, "Asian elephants are huge gray animals inhabiting Asian tropical forests." +
                "Asian elephants grow up to 21 feet long, stand up to 10 feet tall.A dexterous trunk and large, " +
                "rasping molars allow Asian elephants to gather and process a wide variety of vegetation."));
        myAnimals.add(new Animals("Kangaroo", R.drawable.kangaroo, "The Kangaroo has a naked muzzle with a black and white mark on the sides and " +
                "a broad white stripe running from the corner of the mouth to base of the ear." +
                "The hind feet are extremely powerful enabling the Kangaroo to travel at speeds of 65kph."));
        myAnimals.add(new Animals("Rhino", R.drawable.rhino, "Rhinos are solitary and territorial animals; " +
                " Black rhinos eat and are most active between dusk and dawn. During the day when the " +
                "temperatures are high, rhinos wallow in mud to protect their skin from the sun and then find a shady place to rest.\n"));
        myAnimals.add(new Animals("Bear", R.drawable.bear, "All bears have short, thick limbs, heavily built body, and a large head. Look carefully and you’ll " +
                "notice that most bears walk pigeon-toed. Adult brown bears " +
                "are not quite as comfortable in trees, " +
                "cubs are encouraged to climb for safety."));
        myAnimals.add(new Animals("Tiger", R.drawable.tiger, "A powerful hunter with sharp teeth, strong jaws, the tiger is the largest member of the cat family." +
                "It is also the largest land-living mammal whose diet consists of meat." +
                "It is difficult to distinguish a tiger from a lion,but the tiger is the only cat with striped fur."));
    }

    private void populateListView()
    {
        ArrayAdapter<Animals> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.animalsListView);
        list.setAdapter(adapter);
    }


    private class MyListAdapter extends ArrayAdapter<Animals>
    {
        public MyListAdapter()
        {
            super(MainActivity.this, R.layout.animal_view, myAnimals);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View itemView = convertView;
            if (itemView == null)
            {
                itemView = getLayoutInflater().inflate(R.layout.animal_view, null, true);
            }
            Animals animal = myAnimals.get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.animal_icon);
            imageView.setImageResource(animal.getIconId());

            TextView textView = (TextView) itemView.findViewById(R.id.aniaml_name);
            textView.setText(animal.getName());

            return itemView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.info)
        {
            Intent zooInfo = new Intent(MainActivity.this, ZooDetails.class);
            startActivity(zooInfo);
            return true;
        }

        if (id == R.id.uninstall)
        {
            Intent removeApp = new Intent(Intent.ACTION_DELETE);
            removeApp.setData(Uri.parse("package:com.example.user.californiazoo"));
            startActivity(removeApp);
        }
        return super.onOptionsItemSelected(item);
    }

    private void onClickList()
    {
        final ListView list = (ListView) findViewById(R.id.animalsListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id)
            {
                final Animals animal = (Animals) parent.getItemAtPosition(position);
                if (position == list.getCount() - 1)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("WARNING : This animal is very scary. Are you sure you want to view this?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(MainActivity.this, AnimalDetails.class);
                                    intent.putExtra("name", animal);
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int id)
                                {
                                }
                            }).show();
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, AnimalDetails.class);
                    intent.putExtra("name", animal);
                    startActivity(intent);
                }

            }

        });
    }

}
