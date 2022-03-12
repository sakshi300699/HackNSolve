package com.example.android;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationHelperForDoctor {

    public static void enableNavigation(final Context context , BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.doctorChats:
                        Intent intent1 = new Intent(context, ChatListActivity.class);
                        context.startActivity(intent1);
                        break;

                    case R.id.calls:
                        Intent intent4 = new Intent(context, CallsActivity.class);
                        context.startActivity(intent4);
                        break;

                }

                return false;
            }
        });
    }


}
