package com.example.navegation_apm;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Navegation extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private TextView nameuser;
    private TextView name;
    private Bundle bundle;
    private View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegation);

        navView = findViewById(R.id.navigationView);
        navView.setItemIconTintList(null);
        header = navView.getHeaderView(0);
        bundle = this.getIntent().getExtras();
        nameuser = header.findViewById(R.id.txtNameUser);
        nameuser.setText(bundle.getString("Datos"));

        try {
            ImageView imagenuser = header.findViewById(R.id.image_user);
            Glide.with(header)
                    .load(bundle.getString("Imagen"))
                    .error(R.drawable.ic_account_circle_24)
                    .into(imagenuser);

        } catch (Exception e) {
            Log.d("Error-: ", e.toString());
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        bundle = this.getIntent().getExtras();
        String rol = bundle.getString("Rol");

        Menu menu = navView.getMenu();
        Menudinamico(menu);

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    private void Menudinamico(Menu menu) {
        bundle = this.getIntent().getExtras();
        String rol = bundle.getString("Rol");
        name = header.findViewById(R.id.txtName);
        try {

            if (rol.equals("A")) {
                name.setText("Administrador");
                menu.findItem(R.id.menu_seccion_1).setVisible(false);
                menu.findItem(R.id.menu_seccion_2).setVisible(false);
                menu.findItem(R.id.menu_seccion_3).setVisible(false);
                menu.findItem(R.id.menu_seccion_4).setVisible(true);
                menu.findItem(R.id.menu_seccion_5).setVisible(true);
                menu.findItem(R.id.menu_opcion_1).setVisible(false);
                menu.findItem(R.id.menu_opcion_2).setVisible(false);
                menu.findItem(R.id.menu_opcion_3).setVisible(true);
            } else {
                name.setText("Usuario");
                menu.findItem(R.id.menu_seccion_1).setVisible(true);
                menu.findItem(R.id.menu_seccion_2).setVisible(true);
                menu.findItem(R.id.menu_seccion_3).setVisible(true);
                menu.findItem(R.id.menu_seccion_4).setVisible(false);
                menu.findItem(R.id.menu_seccion_5).setVisible(false);
                menu.findItem(R.id.menu_opcion_1).setVisible(true);
                menu.findItem(R.id.menu_opcion_2).setVisible(true);
                menu.findItem(R.id.menu_opcion_3).setVisible(false);
            }

        } catch (Exception e) {
            Log.d("Error menu: ", e.toString());
            e.printStackTrace();
        }

    }

}