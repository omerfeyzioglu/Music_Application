package com.dims.lasttest.Repository;
import com.dims.lasttest.Model.Song;
import javafx.scene.control.Alert;

import java.sql.*;

import java.time.LocalTime;

public class SongRepository {
    private final String URL = "jdbc:mysql://127.0.0.1:3306/music";

    private final String USER = "root";

    private final String PASSWORD = "123";

    private Connection conn = null;

    private Statement stm = null;

    private ResultSet rs = null;

    public Song getSongById(int sid) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT * FROM song WHERE id=" + sid + ";";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        Song song1 = new Song();
        while (rs.next()) {
            song1.setId(rs.getInt(1));
            song1.setTitle(rs.getString(2));
            song1.setDuration_time(rs.getTime(3).toLocalTime());
        }
        conn.close();
        return song1;
    }


    public void saveSong(Song song1) throws SQLException {

        if(isSongExist(song1.getId())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Already exist!");
            alert.setContentText("The song you try to create already exist!");
            alert.showAndWait();

            return;


        }
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stm = conn.createStatement();
        String query = "INSERT INTO song VALUES (" + song1.getId() + ",'" + song1.getTitle() + "','" + song1.getDuration_time() + "');";
        stm.executeUpdate(query);


    }
    private boolean isSongExist(int songID) throws SQLException {
        String query = "SELECT id FROM song WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stm = conn.prepareStatement(query)){
                    stm.setInt(1,songID);
                    try(ResultSet rs = stm.executeQuery()){
                        return rs.next();
                    }
        }

    }

    public void updateSong (Song song) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stm = conn.createStatement();

                String query = "UPDATE song SET title = '" + song.getTitle() + "', duration = '" + song.getDuration_time() +"'WHERE id = " +song.getId();
        stm.executeUpdate(query);
        System.out.println("Song informations successfully updated.");
    }
    public void deleteSong (int songId) throws SQLException {
       conn = DriverManager.getConnection(URL, USER, PASSWORD);
       stm = conn.createStatement();
       String query = "DELETE FROM song WHERE id= " + songId;
       stm.executeUpdate(query);



    }
}


