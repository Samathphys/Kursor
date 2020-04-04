package com.example.cursor;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.KeyListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class TeacherFragment extends Fragment implements KeyListener {

    View v;
    TextView name, classt, school, status;
    Teacher teacher;
    final int pageCount = 5;
    int pagenow;
    TextView[]tv = new TextView[pageCount];
    String[] days1 = {"пн", "вт", "ср", "чт", "пт", "сб", "вс"};
    ViewPager pager;
    PagerAdapter pagerAdapter;
    ArrayList<PageFragment> pageFragments;
    LinearLayout layoutmain;

    TeacherFragment(Teacher teacher) {
        this.teacher = teacher;
        pageFragments = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            PageFragment pageFragment = new PageFragment();
            pageFragment.lessons = teacher.week.get(i).lessons;
            pageFragments.add(pageFragment);
        }
        Date datenow = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(datenow);
        int date = c.get(Calendar.DAY_OF_WEEK)-2;
        System.out.println(date);
        pagenow = (date <= -1 || date == 5 ? 0 : date);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_teacher, container, false);
        name = v.findViewById(R.id.name);
        classt = v.findViewById(R.id.classt);
        school = v.findViewById(R.id.school);
        status = v.findViewById(R.id.status);
        layoutmain = v.findViewById(R.id.linear);
        school.setOnClickListener(v -> {
            layoutmain.removeView(name);
            layoutmain.removeView(classt);
            layoutmain.removeView(status);
        });
        setTextSett(name, teacher.getfullname(), (int) 20);
        setTextSett(school, teacher.school, (int) 20);
        setTextSett(status, "Физичка", 20);
        pager =  v.findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(pagenow);
        pager.setVisibility(View.VISIBLE);
        dye(tv, pagenow);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                dye(tv, position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return v;
    }
    void setTextSett(TextView v, String s, int fontsize){
        v.setText(s);
        v.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        v.setPadding(50, 50, 50, 0);
        v.setTextSize(20);
        v.setGravity(Gravity.CENTER);
    }
    void dye(TextView [] tv, int selected){
        for (int i = 0; i < pageCount; i++) {
            if(tv[i] == null) {
                tv[i] = new TextView(getContext());
                tv[i].setId(i);
                tv[i].setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                p.weight = (float) 1 / pageCount;
                tv[i].setHeight((int) dptopx(32));
                tv[i].setTextSize(17);
                tv[i].setLayoutParams(p);
                int finalI1 = i;
                int finalI = i;
                tv[i].setOnClickListener(v -> {
                    pager.setCurrentItem(finalI1);
                    dye(tv, finalI);
                });
                LinearLayout linear1 = v.findViewById(R.id.linear1);
                linear1.setPadding(0,50,0,0);
                linear1.setWeightSum(1);
                linear1.addView(tv[i]);
            }

            tv[i].setTextColor(Color.BLACK);
            if(i == selected)
                tv[i].setBackgroundColor(Color.LTGRAY);
            else
                tv[i].setBackgroundColor(0x00000000);


            String s = days1[i];
            Spannable spans = new SpannableString(s);
            tv[i].setText(spans);
        }
    }

    @Override
    public int getInputType() {
        return 0;
    }

    @Override
    public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_BACK) {
            System.out.println("------------------------------------------------");
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void clearMetaKeyState(View view, Editable editable, int i) {

    }


    class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return pageFragments.get(position);
        }

        @Override
        public int getCount() {
            return pageCount;
        }
    }

    float dptopx(float dip){
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics()
        );
        return px;
    }
}
