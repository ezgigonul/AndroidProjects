package com.example.tabsviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
//EZGİ GONUL 15070001022//
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    TabLayout tabLayout;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ShareActionProvider shareActionProvider;

    private ViewPager mViewPager;
    View view;

    Fragment homeFragment;
    Fragment searchFragment;
    Fragment cameraFragment;
    Fragment favoritesFragment;
    Fragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        cameraFragment = new CameraFragment();
        favoritesFragment = new FavoritesFragment();
        profileFragment = new ProfileFragment();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout);
        drawerToggle=new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        navigationView=findViewById(R.id.nvView);
        navigationView.setNavigationItemSelectedListener(this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Join Us :)");
        return true;
    }

    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.nav_home_fragment:
                tabLayout.getTabAt(0).select(); //chance selected tab
                break;
            case R.id.nav_search_fragment:
                tabLayout.getTabAt(1).select();
                break;
            case R.id.nav_camera_fragment:
                tabLayout.getTabAt(2).select();
                break;
        }
        return true;
    }

    private void loadFragment(Fragment secondFragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,secondFragment);
        fragmentTransaction.commit();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return homeFragment;
            } else if (position == 1) {
                return searchFragment;
            } else if (position == 2) {
                return cameraFragment;
            } else if (position == 3) {
                return favoritesFragment;
            } else if (position == 4) {
                return profileFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}

