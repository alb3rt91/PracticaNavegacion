package com.example.practicanavegacion;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practicanavegacion.databinding.ActivityMainBinding;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NavController navController3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        setSupportActionBar(binding.toolbar);

        // Configuración de AppBar para el controlador principal (NavHostFragment principal)
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.options1Fragment, R.id.options2Fragment,
                R.id.drawer1Fragment, R.id.drawer2Fragment
        )
                .setOpenableLayout(binding.drawerLayout)
                .build();

        // Configuración del NavController para el primer NavHostFragment (drawer navigation)
        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        // Configuración del NavController para el segundo NavHostFragment (bottom navigation)
        NavController navController2 = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment2)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView, navController2);

        // Configuración del NavController para el tercer NavHostFragment (options menu)
        navController3 = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment3)).getNavController();
        NavigationUI.setupWithNavController(binding.toolbar, navController3, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Asegúrate de que navega usando navController3
        return NavigationUI.onNavDestinationSelected(item, navController3)
                || super.onOptionsItemSelected(item);
    }
}