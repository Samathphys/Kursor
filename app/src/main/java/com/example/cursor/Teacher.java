package com.example.cursor;

import java.util.ArrayList;
import java.util.Date;

class Teacher {
    int id;
    String name;
    String surname;
    String patronomic;
    String school;
    String subject;
    Teacher(){
        id = -1;
        name = "";
        surname = "";
        patronomic = "";
        school = "";
        subject = "";
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
