package com.example.englishpractice;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class TutorialActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TabLayout tabLayout;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ShareActionProvider shareActionProvider;
    private ViewPager mViewPager;
    private static final String CHANNEL_ID = "chronometer",CHANNEL_NAME = "Chronometer Notification";
    private static final int NOTIFICATION_ID = 1; //Notification ID'si
    private NotificationManager manager;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);

        //Custom toolbar Definition
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        //Navigation Drawer implementation
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        navigationView = findViewById(R.id.nvView);
        navigationView.setNavigationItemSelectedListener(this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //ViewPager implementation
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //Tab implementation
        tabLayout = findViewById(R.id.tablayout);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_quiz:
                intent = new Intent(this, QuizActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_score:
                intent = new Intent(this, ScoreActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_translation:
                intent = new Intent(this, TranslationActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Custom toolbar implementatıon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Join Us");
        return true;
    }

    //Share score with implicit intent
    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            case R.id.music:
                //Start service
                if (MyStartedService.StartValue == 0) {
                    Intent intent = new Intent(TutorialActivity.this, MyStartedService.class);
                    startService(intent);

                    //Create Notification
                    manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    createNotificationChannel();
                    sendNotification(getResources().getString(R.string.warning),getResources().getString(R.string.times));

                } else {
                    Intent intent = new Intent(TutorialActivity.this, MyStartedService.class);
                    stopService(intent);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Notification Implementation
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        channel.enableVibration(true);
        manager.createNotificationChannel(channel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendNotification(String title,String text) {
        @SuppressLint("WrongConstant") Notification notification = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.music)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH) //used to show the incoming notification as a pop-up
                .build();
        manager.notify(NOTIFICATION_ID, notification);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new WordsFragment();
                case 1:
                    return new GrammerFragment();
                case 2:
                    return new DailyConversationFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}


