package com.example.shadow.exercise_array2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.shadow.exercise_array2.fragments.Favorite;
import com.example.shadow.exercise_array2.fragments.Home;
import com.example.shadow.exercise_array2.fragments.Profile_frag;
import com.example.shadow.exercise_array2.fragments.Search;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    //Variables
    BottomNavigationViewEx bnve;
    FloatingActionButton fab;
    android.support.v7.widget.Toolbar toolbar;
    getNavColor getNavColor;
    CoordinatorLayout container;
    AppBarLayout appbar;
    int homef = 0,favoritef = 0;
    final Fragment homefragment =new Home(),
            profilefragment = new Profile_frag(),
            favoritefragment = new Favorite(),
            searchfragment = new Search();
    Fragment active = homefragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //References
        bnve = findViewById(R.id.bnve);
        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        container = findViewById(R.id.container);
        getNavColor = new getNavColor();
        appbar = findViewById(R.id.abl);

        //Bottom Navigation View Settings
//        bnve.enableItemShiftingMode(false);
//        bnve.enableShiftingMode(false);
        //bnve.enableAnimation(false);
        //bnve.setTextVisibility(false);
        bnve.setIconSize(30);
        bnve.setBackgroundColor(getColor(R.color.default_nav));
        toolbar.setBackgroundColor(getColor(R.color.default_nav));
        //Set Default Fragment to Home
        getFragmentManager().beginTransaction().replace(R.id.container,homefragment).commit();
        getFragmentManager().beginTransaction().add(R.id.container,profilefragment).hide(profilefragment).commit();
        getFragmentManager().beginTransaction().add(R.id.container,searchfragment).hide(searchfragment).commit();
        getFragmentManager().beginTransaction().add(R.id.container,favoritefragment).hide(favoritefragment).commit();


        //Floating Action Button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Floating Action", Toast.LENGTH_SHORT).show();
            }
        });
        //Bottom Navigation onClick
        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0;

                switch (item.getItemId()){
                    case R.id.homenav:
                        position = 0;
                        //bnve.getSmallLabelAt(position).setText("");
                        Toast.makeText(MainActivity.this, ""+bnve.getLargeLabelAt(position).getText().toString(), Toast.LENGTH_SHORT).show();

                        setFragment(homefragment);
                        active = homefragment;
                        break;
                    case R.id.searchnav:
                        position = 1;
                        homef = 0;
                        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, 22);
                        break;
                    case R.id.favornav:
                        position = 2;
//                        Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.cute);
                        break;
                    case R.id.profilenav:
                        position = 3;
                        setFragment(profilefragment);
                        active = profilefragment;
                        break;
                    case R.id.emptynav:
                        return false;
                }
                return true;
            }
        });

        bnve.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                Log.d("navbar double clicked","double tapped");
            }
        });
    }

    public void setFragment(Fragment fragment){
        getFragmentManager().beginTransaction().hide(active).show(fragment).addToBackStack(null).commit();
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

                int color = getNavColor.findNavbarColor(selectedImage,getApplicationContext());
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
