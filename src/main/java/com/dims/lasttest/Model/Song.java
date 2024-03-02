package com.dims.lasttest.Model;

import java.time.LocalTime;

public class Song {

    private int id;
    private String title;
    private LocalTime duration_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration_time=" + duration_time +
                '}';
    }

    public LocalTime getDuration_time() {
        return duration_time;
    }

    public void setDuration_time(LocalTime duration_time) {
        this.duration_time = duration_time;
    }

    public Song(int id, String title, LocalTime duration_time) {
        this.id = id;
        this.title = title;
        this.duration_time = duration_time;
    }

    public Song() {
    }


}
