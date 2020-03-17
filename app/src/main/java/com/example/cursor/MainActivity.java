package com.example.cursor;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle toggle;
    ArrayList<Teacher> teachers = new ArrayList<>();
    User user = new User();

    ListFragment listFragment;
    UserFragment userFragment = new UserFragment();
    ScheduleFragment scheduleFragment = new ScheduleFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = findViewById(R.id.drawer_layout);
        nvDrawer = findViewById(R.id.nvView);
        toggle = new ActionBarDrawerToggle (this, mDrawer, toolbar, R.string.open, R.string.close);
        mDrawer.setDrawerListener(toggle);
        setupDrawerContent(nvDrawer);
        toggle.syncState();
        Teacher teacher;
        for (int i = 0; i < 30; i++) {
            teacher = new Teacher();
            if(i == 20) {
                teacher.name = "Вера";
                teacher.subject = "Физика";
            }
            teachers.add(teacher);
        }
        listFragment = new ListFragment(teachers);
        userFragment.user = user;
        loadFragment(listFragment);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.profile:
                loadFragment(userFragment);
                break;
            case R.id.teacher_list:
                loadFragment(listFragment);
                break;
            case R.id.schedule:
                loadFragment(scheduleFragment);
                break;
        }
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }
    void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();

    }
}