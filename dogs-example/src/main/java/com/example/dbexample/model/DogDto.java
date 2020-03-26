package com.example.dbexample.model;

public class DogDto {
    private long id;
    private String name;
    private int age;

    public DogDto(long id, String name, int age) throws Exception {
        super();
        setAge(age);
        setId(id);
        setName(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() == 0) {
            throw new Exception("name should not be empty");
        }
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) throws Exception {
        if (age <= 0) throw new Exception("dog age should be greater than or equal zero");
        this.age = age;
    }
}
