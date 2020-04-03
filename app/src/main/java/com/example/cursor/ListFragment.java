package com.example.cursor;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.cursor.MainActivity.download_arr;
import static com.example.cursor.MainActivity.download_obj;

public class ListFragment extends Fragment {

    View v;
    EditText editText;
    TAdapter adapter;
    ArrayList<Teacher> teachers;
    ArrayList<Teacher> teachers1;
    ArrayList<String> strings;
    ArrayList<String> strings1;
    ListView listView;
    ArrayList<String> listItems;
    ArrayList<String> listItems1;

    ListFragment(ArrayList<Teacher> teachers){
        strings = new ArrayList<>();
        strings1 = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++) {
            strings.add(teachers.get(i).name);
            strings1.add(teachers.get(i).subject);
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

        adapter = new TAdapter((Activity) v.getContext(), strings, strings1);
        listView.setAdapter(adapter);
        initList();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            try {
                new Thread(() -> {
                    JSONObject jsonObject = download_obj("http://cursor.spb.ru/schedule/" + teachers1.get(position).id);
                    System.out.println(jsonObject.toString());
                    TeacherFragment fragment = new TeacherFragment(teachers.get(position));
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
                }).start();
            }catch (Exception e){}
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
        listItems = new ArrayList<>(strings);
        listItems1 = new ArrayList<>(strings1);
        teachers1 = new ArrayList<>(teachers);
        for(String item:strings){
            String textToSearchlowercase = textToSearch.toLowerCase();
            if(!item.toLowerCase().contains(textToSearchlowercase) && listItems.indexOf(item) != -1){
                listItems1.remove(listItems.indexOf(item));
                teachers1.remove(listItems.indexOf(item));
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
        adapter = new TAdapter((Activity) v.getContext(), listItems, listItems1);
        listView.setAdapter(adapter);
    }



    public void initList(){
        listItems = new ArrayList<>(strings);
        listItems1 = new ArrayList<>(strings1);
        teachers1 = new ArrayList<>(teachers);
        adapter = new TAdapter((Activity) v.getContext(), listItems, listItems1);
        listView.setAdapter(adapter);
    }


}
