package com.example.bean;


public class Student {
    private Integer id;
    private String name;
    private Integer son;
    private String password;

    public Student() {
    }

    public Student(Integer id, String name, Integer son, String password) {
        this.id = id;
        this.name = name;
        this.son = son;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSon() {
        return son;
    }

    public void setSon(Integer son) {
        this.son = son;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", son=" + son +
                ", password='" + password + '\'' +
                '}';
    }
}
