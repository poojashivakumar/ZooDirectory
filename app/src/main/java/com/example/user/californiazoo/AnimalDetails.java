package com.example.user.californiazoo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimalDetails extends AppCompatActivity
{
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Animals animals = (Animals)getIntent().getSerializableExtra("name");
        TextView text = (TextView)findViewById(R.id.text);
        text.setText(animals.getName().toString());
        ImageView fullImage = (ImageView)findViewById(R.id.fullImage);
        fullImage.setImageResource(animals.getIconId());
        TextView description = (TextView)findViewById(R.id.desc);
        description.setText(animals.getDescription().toString());
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

        if (id == R.id.info) {
            Intent zooInfo = new Intent(AnimalDetails.this, ZooDetails.class);
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
}


