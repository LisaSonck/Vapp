package no.nordicsemi.android.nrftoolbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class GraphActivity extends AppCompatActivity implements FragmentChangeListener, NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_drawer);
        Toolbar toolbar= findViewById(R.id.graph_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.graph_drawer);
        navigationView = findViewById(R.id.graph_nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        TabLayout tablayout = findViewById(R.id.graph_tablayout);
        TabItem tabday = findViewById(R.id.graph_day);
        TabItem tabweek = findViewById(R.id.graph_week);
        TabItem tabmonth = findViewById(R.id.graph_month);
        TabItem tabyear = findViewById(R.id.graph_year);
        ViewPager viewpager = findViewById(R.id.graph_viewpager);

        PagerAdapter pagerAdapter = new Graph_PagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(pagerAdapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //openen van juiste fragment
            case R.id.nav_home:
                Intent home = new Intent(GraphActivity.this, SplashscreenActivity.class);
                startActivity(home);
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
            case R.id.nav_numbers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NumbersFragment()).commit();
                break;
            case R.id.nav_graph:
                Intent i = new Intent(this, GraphActivity.class);
                startActivity(i);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //		new GraphFragment()).commit();
                break;
            case R.id.nav_bluetooth:
                Intent ii = new Intent(this, FeaturesActivity.class);
                startActivity(ii);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true; //item wordt selected na klikken

    }

    @Override
    //willen niet direct terug als navigatievenster open -> enkel terug nr gewone fragment; navigatievenster sluiten
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) { //als drawer langs rechterkan openen (=END) dan moet hier ook .END gebruikt worden, ipv .START
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //anders wel algemene uitvoering functie
            super.onBackPressed();
        }
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
    public FragmentManager getFM(){
        return getSupportFragmentManager();
    }
}
