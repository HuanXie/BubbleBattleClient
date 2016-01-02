package com.example.huan.bubblebattle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Log.d("Stupid", "inside personal activity");
        Intent intent = getIntent();

        addListenersForTableImages();
    }

    private void addListenersForTableImages() {
        final PopupMenu.OnMenuItemClickListener menuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                boolean handled = false;
                Context context = getApplicationContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;
                Toast toast;
                switch (item.getItemId()) {
                    case R.id.table_join_game:
                        handled = true;
                        text = "join game! ";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                        GoToGamingActivity();
                        break;
                    case R.id.table_watch_game:
                        text = "join game! ";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                        handled = true;
                        break;
                    case R.id.table_show_profile:
                        text = "show profile! ";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                        GotoProfileActivity();
                        break;
                    default:
                        break;
                }
                return handled;
            }
        };

        final View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(PersonalActivity.this, v);
                popup.setOnMenuItemClickListener(menuItemClickListener);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.table_popup, popup.getMenu());
                popup.show();
            }
        };

        ImageButton button = (ImageButton) findViewById(R.id.table1);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table2);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table3);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table4);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table5);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table6);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table7);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table8);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table9);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table10);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table11);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table12);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table13);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table14);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table15);
        button.setOnClickListener(onClickListener);
        button = (ImageButton) findViewById(R.id.table16);
        button.setOnClickListener(onClickListener);
    }

    private void GoToGamingActivity() {
        Context context = getApplicationContext();
        Intent intent = new Intent(context, GamingActivity.class);
        startActivity(intent);
        Log.d(PersonalActivity.class.toString(), "Jumping to gaming activity");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.personal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mypage) {
            GoToMyPageamingActivity();
        } else if (id == R.id.baggage) {

        } else if (id == R.id.market) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void GoToMyPageamingActivity() {
        Context context = getApplicationContext();
        Intent intent = new Intent(context, MyPageActivity.class);
        startActivity(intent);
        Log.d(PersonalActivity.class.toString(), "Jumping to MyPage activity");
    }

    private void GotoProfileActivity() {
        Context context = getApplicationContext();
        Intent intent = new Intent(context, ProfileActivity.class);
        startActivity(intent);
        Log.d(PersonalActivity.class.toString(), "Jumping to Profile activity");
    }
}
