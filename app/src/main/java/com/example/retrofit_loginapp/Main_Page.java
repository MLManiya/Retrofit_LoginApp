package com.example.retrofit_loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.retrofit_loginapp.databinding.HomePageBinding;
import com.example.retrofit_loginapp.ui.add.Add_Product_Fragment;
import com.example.retrofit_loginapp.ui.home.HomeFragment;
import com.example.retrofit_loginapp.ui.slideshow.SlideshowFragment;
import com.google.android.material.navigation.NavigationView;



public class Main_Page extends AppCompatActivity {

    HomePageBinding binding;
    TextView headername,headeremail;
    ImageView headerimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=HomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addfragment(new Fragment());

        View headerview = binding.navView.getHeaderView(0);
        headerimage = headerview.findViewById(R.id.imageView);
        headername = headerview.findViewById(R.id.textviewname);
        headeremail = headerview.findViewById(R.id.textviewemail);

        headername.setText(Splash_Screen.sharedPreferences.getString("sellername",null));
        headeremail.setText(Splash_Screen.sharedPreferences.getString("sellername",null));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Main_Page.this,binding.drawerLayout,binding.appBarProductViewWithNavigationDrawer.toolbarview,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    addfragment(new HomeFragment());
                    binding.drawerLayout.closeDrawers();
                }
                if(item.getItemId()==R.id.nav_addproduct){
                    addfragment(new Add_Product_Fragment());
                    binding.drawerLayout.closeDrawers();
                }
                if(item.getItemId()==R.id.nav_slideshow){
                    addfragment(new SlideshowFragment());
                    binding.drawerLayout.closeDrawers();
                }
//                if(item.getItemId()==R.id.signout){
//                    editor.putInt("login",0);
//                    editor.commit();
//                    Intent intent = new Intent(Main_Page.this,Signup_Page.class);
//                    startActivity(intent);
//                }
                return true;
            }
        });

//        setSupportActionBar(binding.appBarProductViewWithNavigationDrawer.toolbarview);
//        binding.appBarProductViewWithNavigationDrawer.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });
//        DrawerLayout drawer = binding.drawerLayout;
//        NavigationView navigationView = binding.navView;
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setOpenableLayout(drawer)
//                .build();
//
//        NavController navController = Navigation.findNavController(this, R.id.app_bar_product_view_with_navigation_drawer);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        binding.appBarProductViewWithNavigationDrawer.menudot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(Main_Page.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.menudot,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if(menuItem.getItemId()==R.id.nav_setting)
                        {

                        }
                        if(menuItem.getItemId()==R.id.nav_logout)
                        {
                            Splash_Screen.editor.putInt("login",0);
                            Splash_Screen.editor.commit();
                            Intent intent = new Intent(Main_Page.this, Login_Activity.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }


    private void addfragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.commit();
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.app_bar_product_view_with_navigation_drawer);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}