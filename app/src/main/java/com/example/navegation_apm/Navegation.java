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
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegation);

        navView = findViewById(R.id.navigationView);
        navView.setItemIconTintList(null);
        View header =navView.getHeaderView(0);
        bundle = this.getIntent().getExtras();
        nameuser = header.findViewById(R.id.txtNameUser);
        nameuser.setText( bundle.getString("Datos"));
        try{
            ImageView imagenuser = header.findViewById(R.id.image_user);
            Glide.with(header)
                    .load(bundle.getString("Imagen"))
                    .error(R.drawable.ic_account_circle_24)
                    .into(imagenuser);

        }catch(Exception e){
            Log.d("Error-: ",e.toString());
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

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
        String rol = bundle.getString("role");
        Log.d("rol: ",rol.toString());
        List<MenuItem> menus = new ArrayList<MenuItem>();
        MenuItem profile = menu.add("Perfil de usuario");


        MenuItem tmp;
        if (rol.equals("A")) {
            SubMenu navigation_root = menu.addSubMenu("Administración");
            tmp = navigation_root.add("Gestionar Usuarios").setIcon(R.drawable.ic_baseline_subject_24);
            menus.add(tmp);
        }

        SubMenu navigation_others = menu.addSubMenu("Otros");
        tmp = navigation_others.add("Configuración");
        tmp.setIcon(R.drawable.ic_baseline_settings_24);
        menus.add(tmp);
    }


}