package com.example.demo.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private Integer sort1;
    private Integer sort2;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "sort1")
    public Integer getSort1() {
        return sort1;
    }

    public void setSort1(Integer sort1) {
        this.sort1 = sort1;
    }

    @Basic
    @Column(name = "sort2")
    public Integer getSort2() {
        return sort2;
    }

    public void setSort2(Integer sort2) {
        this.sort2 = sort2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (age != person.age) return false;
        if (sort1 != person.sort1) return false;
        if (sort2 != person.sort2) return false;
        if (!Objects.equals(name, person.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + sort1;
        result = 31 * result + sort2;
        return result;
    }
}
