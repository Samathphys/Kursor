package com.example.cursor;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

class Teacher {
    int id;
    String name;
    String surname;
    String patronomic;
    String school;
    String subject;
    String url;
    Bitmap bitmap;
    ArrayList<Day> week = new ArrayList<>();
    Teacher(){
        id = -1;
        name = "";
        surname = "";
        patronomic = "";
        school = "";
        subject = "";
        url = "";
    }
    String getfullname(){
        return name + " " + patronomic + " " + surname;
    }
}


class Class{
    int id;
    String name;
    Class(){
        id = -1;
        name = "";
    }
}
class Lesson{
    int id;
    String name;
    int numberinday;
    String start, end;
    String classroom;
    Lesson(){
        id = -1;
        name = "";
        numberinday = -1;
    }
}
class User{

    int id;
    String name;
    String surname;
    String patronomic;
    Class userclass;
    String school;
    Teacher form_master;

    User(){
        name = "";
        surname = "";
        patronomic = "";
        userclass = new Class();
        school = "";
        form_master = new Teacher();
    }
    String getfullname(){
        return name + " " + patronomic + " " + surname;
    }

}

class Day{
    ArrayList<Lesson> lessons;

    Day(){
        lessons = new ArrayList<>();
    }
}
