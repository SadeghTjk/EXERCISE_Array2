package com.example.shadow.exercise_array2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.shadow.exercise_array2.fragments.Home;
import com.example.shadow.exercise_array2.fragments.Profile_frag;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationViewEx bnve;
    //ImageView iv2;
    FloatingActionButton fab;
    android.support.v7.widget.Toolbar toolbar;
    getNavbarColor getNavbarColor;
    FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rv = findViewById(R.id.rv);
        bnve = findViewById(R.id.bnve);
        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        container = findViewById(R.id.container);
        //iv2 = findViewById(R.id.imageView2);
        getNavbarColor = new getNavbarColor();

        //bnve.enableItemShiftingMode(false);
        bnve.enableShiftingMode(false);
        //bnve.enableAnimation(false);
        bnve.setTextVisibility(false);
        bnve.setIconSize(30);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Floating Action", Toast.LENGTH_SHORT).show();
            }
        });

        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0;
                switch (item.getItemId()) {
                    case R.id.homenav:
                        position = 0;
                        setFragment(new Home());
                       // bnve.setBackgroundColor(getResources().getColor(R.color.home_nav));
                        break;
                    case R.id.searchnav:
                        position = 1;
                        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, 22);
                        break;
                    case R.id.favornav:
                        position = 2;
                       /// bnve.setBackgroundColor(getResources().getColor(R.color.favorite_nav));
//                        Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(),
//                                R.drawable.cute);
//                        setToolbarColor(icon);
                        break;
                    case R.id.profilenav:
                        position = 3;
                       // bnve.setBackgroundColor(getResources().getColor(R.color.profile_nav));
                        setFragment(new Profile_frag());
                        break;
                    case R.id.emptynav:
                        return false;
                }

                return true;
            }
        });
    }

    // show fragments methode
    public void setFragment(Fragment fragment){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container,fragment).commit();
    }

    //image picker result
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                //iv2.setImageBitmap(selectedImage);

                int color = getNavbarColor.findNavbarColor(selectedImage,getApplicationContext());
                bnve.setBackgroundColor(color);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(MainActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
}
