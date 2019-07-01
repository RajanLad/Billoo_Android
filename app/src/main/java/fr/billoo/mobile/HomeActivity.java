package fr.billoo.mobile;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import fr.billoo.mobile.Fragments.AnalyticsFragment;
import fr.billoo.mobile.Fragments.BillsFragment;
import fr.billoo.mobile.Fragments.HomeFragment;
import fr.billoo.mobile.Fragments.SettingsFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        BillsFragment.OnFragmentInteractionListener,
        AnalyticsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener
        {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);


        //Barcode Fragment POP up
        // get the bottom sheet view
        ConstraintLayout llBottomSheet = (ConstraintLayout) findViewById(R.id.barView);

        // init the bottom sheet behavior
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

        // change the state of the bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        // set the peek height
        bottomSheetBehavior.setPeekHeight(88);

        // set hideable or not
        bottomSheetBehavior.setHideable(false);

        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if(newState==BottomSheetBehavior.STATE_EXPANDED) {
                    TextView t1=(TextView) findViewById(R.id.textView3);
                    t1.setGravity(Gravity.CENTER_VERTICAL);
                    t1.setTextSize(30f);
                    t1.setText("Swipe Down ");
                    t1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_green_24dp),null,getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_green_24dp),null);
                }
                else
                {
                    TextView t1=(TextView) findViewById(R.id.textView3);
                    t1.setGravity(Gravity.CENTER_VERTICAL);
                    t1.setTextSize(30f);
                    t1.setText("Swipe Up ");
                    t1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_keyboard_arrow_up_24dp),null,getResources().getDrawable(R.drawable.ic_keyboard_arrow_up_24dp),null);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemBackgroundResource(R.drawable.menu_selector_options);
        navigationView.setNavigationItemSelectedListener(this);

        //Transistion animation to BarCodeActivity

        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
        } else {
            // Swap without transition
        }

        //Load the home fragment before hand
        Fragment fragment = new HomeFragment();
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
            title  = "Home";

        } else if (id == R.id.nav_bills) {
            fragment = new BillsFragment();
            title  = "Bills";

        } else if (id == R.id.nav_analytics) {
            fragment = new AnalyticsFragment();
            title = "Analytics";
        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
            title = "Settings";
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
