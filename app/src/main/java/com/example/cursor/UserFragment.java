package com.example.cursor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class UserFragment extends Fragment {

    View v;
    LinearLayout linearuser;
    User user;

    public UserFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v  = inflater.inflate(R.layout.fragment_user, container, false);
        linearuser = v.findViewById(R.id.linearuser);
        createInterf();
        return v;
    }

    void createInterf(){
        TextView name = new TextView(v.getContext());
        TextView school = new TextView(v.getContext());
        TextView uclass = new TextView(v.getContext());
        TextView form_master_name = new TextView(v.getContext());
        setTextSett(uclass, user.userclass.name, 20);
        setTextSett(name, user.getfullname(), 20);
        setTextSett(school, user.school, 20);
        setTextSett(form_master_name, user.form_master.getfullname(), 20);
        linearuser.addView(name);
        linearuser.addView(uclass);
        linearuser.addView(school);
    }

    void setTextSett(TextView v, String s, int fontsize){
        v.setText(s);
        v.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        v.setPadding(50, 50, 50, 0);
        v.setTextSize(fontsize);
        v.setGravity(Gravity.CENTER);
    }



}