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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
        final JSONArray[] jsonArrays = {null};
        new Thread(() -> {
            try {
                jsonArrays[0] = download_arr("http://cursor.spb.ru/all_teachers");
                try {

                    System.out.println(jsonArrays[0].toString());
                    for (int i = 0; i < jsonArrays[0].length(); i++) {
                        Teacher teacher = new Teacher();
                        JSONArray jsonArray1 = jsonArrays[0].getJSONArray(i);
                        teacher.id = Integer.parseInt(jsonArray1.getString(0));
                        teacher.name = jsonArray1.getString(1);
                        if(jsonArray1.length() > 2)
                            teacher.subject = jsonArray1.getString(2);
                        teachers.add(teacher);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listFragment = new ListFragment(teachers);
                userFragment.user = user;
                loadFragment(listFragment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    static  JSONArray download_arr(String url){
        try {
            String str = connect(url);
            JSONArray jsonArray = new JSONArray(str);
            return jsonArray;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new JSONArray();
    }

    static  JSONObject download_obj(String url){
        try {
            String str = connect(url);
            JSONObject jsonObject = new JSONObject(str);
            return jsonObject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new JSONObject();
    }

    static  String connect(String url){
        String str = "";
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            str = response.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
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