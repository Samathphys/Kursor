package com.example.cursor;

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
        tableLayout.setShrinkAllColumns(true);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setWeightSum(1);
        tableLayout.setPadding(20,0,20,0);
        DrawTable();
        return v;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void DrawTable(){
        for (int i = 0; i < lessons.size(); i++) {
            TableRow tbrow = new TableRow(getContext());
            tbrow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            tbrow.setBaselineAligned(false);

            TextView tv2 = new TextView(getContext());
            tv2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            TextView tv11 = new TextView(getContext());
            tv11.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            TextView tv12 = new TextView(getContext());
            tv12.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));


            TextView tv3 = new TextView(getContext());
            tv3.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(tv11);
            linearLayout.addView(tv12);

            tv2.setGravity(Gravity.CENTER);
            tv11.setGravity(Gravity.CENTER);
            tv12.setGravity(Gravity.CENTER);
            tv3.setGravity(Gravity.CENTER);
            tv2.setPadding(10,10,10,10);
            tv3.setPadding(10,10,10,10);
            linearLayout.setPadding(10,10,10,10);
            Date d = new Date();
            d.setTime(lessons.get(i).start);
            SimpleDateFormat form = new SimpleDateFormat("HH:mm");
            tv11.setText(form.format(d));
            d.setTime(lessons.get(i).end);
            tv12.setText(form.format(d));
            tv2.setText("201");
            tv3.setText("там где надо");
            tv2.setMaxLines(1);
            if (i - lessons.size() + 1 == 0) {
                linearLayout.setBackground(getResources().getDrawable(R.drawable.cell_phone2));
                tv2.setBackground(getResources().getDrawable(R.drawable.cell_phone2));
            } else {
                linearLayout.setBackground(getResources().getDrawable(R.drawable.cell_phone));
                tv2.setBackground(getResources().getDrawable(R.drawable.cell_phone));
                tv3.setBackground(getResources().getDrawable(R.drawable.cell_phone3));
            }
            tbrow.addView(linearLayout);
            tbrow.addView(tv2);
            tbrow.addView(tv3);
            tableLayout.addView(tbrow);
        }
    }
}