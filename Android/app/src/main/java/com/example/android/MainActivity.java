package com.example.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.transition.Slide;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.menu_home :
                        Toast.makeText(getApplicationContext(),"Chats Panel is Open",Toast.LENGTH_LONG).show();
                        setContentView(R.layout.layout_chatlist);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_call :
                        Toast.makeText(getApplicationContext(),"Calls Panel is Open",Toast.LENGTH_LONG).show();
                        setContentView(R.layout.activity_main);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_setting :
                        Toast.makeText(getApplicationContext(),"Disease Prediction Panel is Open",Toast.LENGTH_LONG).show();
                        setContentView(R.layout.activity_main);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_period_tracker:
                        Toast.makeText(getApplicationContext(),"Period Tracker is Open",Toast.LENGTH_LONG).show();
                        setContentView(R.layout.activity_main);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_logout :
                        Toast.makeText(getApplicationContext(),"Signout is Open",Toast.LENGTH_LONG).show();
                        setContentView(R.layout.activity_main);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

                return true;
            }
        });

    }
}
