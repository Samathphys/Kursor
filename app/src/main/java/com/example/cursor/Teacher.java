package com.example.cursor;

import java.util.Date;

class Teacher {
    int id;
    String name;
    String surname;
    String patronomic;
    Class form_masters_class;
    String school;
    String subject;
    Teacher(){
        id = -1;
        name = "��������";
        surname = "���������";
        patronomic = "���������";
        school = "�������� �0";
        subject = "�������";
        form_masters_class = new Class();
    }
    String getfullname(){
        return name + " " + patronomic + " " + surname;
    }
}

class Subject{
    int id;
    String name;
    Subject(){
        id = -1;
        name = "null";
    }
}

class Class{
    int id;
    String name;
    Class(){
        id = -1;
        name = "11-A";
    }
}
class Lesson{
    int id;
    String name;
    int numberinday;
    long start, end;
    Lesson(){
        id = -1;
        name = "�������";
        numberinday = -1;
        start = (new Date()).getTime();
        end = start + 1000000;
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
        name = "�������";
        surname = "����������";
        patronomic = "�����������";
        userclass = new Class();
        school = "�������� �0";
        form_master = new Teacher();
    }
    String getfullname(){
        return name + " " + patronomic + " " + surname;
    }

}