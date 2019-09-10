package com.example.demo.pojo;

import java.math.BigInteger;
import java.util.Objects;

public class StudentCount{

    private Integer age;
    private BigInteger count;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCount)) return false;
        StudentCount that = (StudentCount) o;
        return getAge().equals(that.getAge()) &&
                getCount().equals(that.getCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getCount());
    }
}