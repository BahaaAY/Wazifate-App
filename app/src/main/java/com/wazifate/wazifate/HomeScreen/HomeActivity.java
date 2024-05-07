package com.wazifate.wazifate.HomeScreen;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.wazifate.wazifate.HomeScreen.ui.home.HomeFragment;
import com.wazifate.wazifate.HomeScreen.ui.quiz_home.QuizHomeFragment;
import com.wazifate.wazifate.R;
import com.wazifate.wazifate.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        setSupportActionBar(binding.appBarHome.toolbar);
        String fragmentToShow = getIntent().getStringExtra("fragmentToShow");


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_quizhome, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                NavController navController = Navigation.findNavController(HomeActivity.this, R.id.nav_host_fragment_content_home);
                switch (id) {
                    case R.id.nav_home:
                        // Check if we're not already in the home fragment
                        if (navController.getCurrentDestination().getId() != R.id.nav_home) {
                            navController.popBackStack();  // Pop the current fragment (Quizzes)
                            navController.navigate(R.id.nav_home);
                        }
                        break;
                    case R.id.nav_quizhome:
                        if (navController.getCurrentDestination().getId() != R.id.nav_quizhome) {
                            navController.popBackStack();  // Pop the current fragment (home)
                            navController.navigate(R.id.nav_quizhome);
                        }
                        break;
                    // Handle other menu items similarly
                }
                drawer.closeDrawer(navigationView);
                return true;
            }
        });
        if (fragmentToShow != null) {
            if(fragmentToShow.equals("quiz")) {
                navController.navigate(R.id.action_quizhome);

            }
        }
        String user = getSharedPreferences("wazifate",MODE_PRIVATE).getString("username", "user");
        System.out.println("username: " + user);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}