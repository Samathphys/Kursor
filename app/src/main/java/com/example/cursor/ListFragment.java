package com.example.cursor;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    View v;
    EditText editText;
    TAdapter adapter;
    ArrayList<Teacher> teachers;
    ArrayList<String> strings;
    ListView listView;
    String[] items;
    ArrayList<String> listItems;

    ListFragment(ArrayList<Teacher> teachers){
        strings = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++) {
            strings.add(teachers.get(i).getfullname());
        }
        this.teachers = teachers;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_list, container, false);

        editText = v.findViewById(R.id.txtsearch);
        listView = v.findViewById(R.id.listview);

        adapter = new TAdapter((Activity) v.getContext(), strings, strings);
        listView.setAdapter(adapter);
        initList();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
            TeacherFragment fragment = new TeacherFragment(teachers.get(position));
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
        });

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    initList();
                } else {
                    searchItem(s.toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return v;
    }
    public void searchItem(String textToSearch){
        for(String item:strings){
            String textToSearchlowercase = textToSearch.toLowerCase();
            if(!item.toLowerCase().contains(textToSearchlowercase)){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void initList(){
        listItems = new ArrayList<>(strings);
        adapter = new TAdapter((Activity) v.getContext(), listItems, listItems);
        listView.setAdapter(adapter);
    }
}
