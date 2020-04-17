package com.example.cursor;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PageFragment extends Fragment {

    ArrayList<Lesson> lessons;
    TableLayout tableLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page, container, false);
        tableLayout = v.findViewById(R.id.table);

        tableLayout.setColumnStretchable(1, true);
        tableLayout.setColumnShrinkable(1, true);
        tableLayout.setColumnStretchable(2, true);
        tableLayout.setColumnShrinkable(2, true);
        tableLayout.setColumnStretchable(3, true);
        tableLayout.setColumnShrinkable(3, true);
        tableLayout.setWeightSum(1);
        tableLayout.setPadding(20,0,20,0);
        DrawTable();
        return v;
    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void DrawTable(){
        for (int i = -1; i < lessons.size(); i++) {
            TableRow tbrow = new TableRow(getContext());
            tbrow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            tbrow.setBaselineAligned(false);

            TextView tv2 = new TextView(getContext());
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            TextView tv1 = new TextView(getContext());
            tv1.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            TextView tv0 = new TextView(getContext());
            tv0.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT));

            TextView tv3 = new TextView(getContext());
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            tv0.setGravity(Gravity.CENTER_VERTICAL);
            tv2.setGravity(Gravity.CENTER);
            tv1.setGravity(Gravity.CENTER);
            tv3.setGravity(Gravity.CENTER);
            tv2.setTextColor(Color.BLACK);
            tv1.setTextColor(Color.BLACK);
            tv0.setTextColor(Color.BLACK);
            tv3.setTextColor(Color.BLACK);
            tv2.setPadding(10,30,10,30);
            tv0.setPadding(15,0,15,0);
            tv3.setPadding(10,10,10,10);
            tv2.setTextSize(19);
            tv3.setTextSize(19);
            tv0.setTextSize(15);
            tv1.setTextSize(19);
            if(i == -1){
                tv0.setText("№");
                tv2.setText("кабинет");
                tv3.setText("класс");
                tv1.setText("время");
            }
            else {
                tv1.setText(lessons.get(i).start + " - " + lessons.get(i).end);
                tv2.setText(lessons.get(i).name);
                tv3.setText(lessons.get(i).classroom);
                tv0.setText(String.valueOf(i + 1));
            }
            tv2.setMaxLines(1);
            if (i - lessons.size() + 1 == 0) {
                tv0.setBackground(getResources().getDrawable(R.drawable.cell_phone2));
                tv1.setBackground(getResources().getDrawable(R.drawable.cell_phone2));
                tv2.setBackground(getResources().getDrawable(R.drawable.cell_phone2));
            } else {
                tv0.setBackground(getResources().getDrawable(R.drawable.cell_phone));
                tv1.setBackground(getResources().getDrawable(R.drawable.cell_phone));
                tv2.setBackground(getResources().getDrawable(R.drawable.cell_phone));
                tv3.setBackground(getResources().getDrawable(R.drawable.cell_phone3));
            }
            tbrow.addView(tv0);
            tbrow.addView(tv1);
            tbrow.addView(tv2);
            tbrow.addView(tv3);
            tableLayout.addView(tbrow);
        }
    }
}