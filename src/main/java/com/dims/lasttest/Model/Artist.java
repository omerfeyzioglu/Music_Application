package com.dims.lasttest.Model;

import java.time.LocalDate;
public class Artist {
    private int id;

    private String name;


    private LocalDate birth_date;

    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth_date=" + birth_date +
                ", country='" + country + '\'' +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Artist() {
    }

    public Artist(int id, String name, LocalDate birth_date, String country) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.country = country;
    }


}