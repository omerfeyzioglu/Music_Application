package com.dims.lasttest.Repository;

import com.dims.lasttest.Model.Artist;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

public class ArtistRepository {
    private final String URL = "jdbc:mysql://127.0.0.1:3306/music";

    private final String USER = "root";

    private final String PASSWORD = "jgnw3quev";

    private Connection conn = null;

    private Statement stm = null;

    private ResultSet rs = null;

    public Artist getArtistBYID(int aid) throws SQLException{
        conn = DriverManager.getConnection(URL ,USER,PASSWORD);
        String query ="SELECT * FROM artist WHERE id=" + aid +";";
        stm = conn.createStatement();
        rs = stm.executeQuery(query);
        Artist artist1 = new Artist();
        while(rs.next()){
            artist1.setId(rs.getInt(1));
            artist1.setName(rs.getString(2));
            LocalDate birthDate = rs.getDate(3).toLocalDate();
            artist1.setBirth_date(birthDate);
            artist1.setCountry(rs.getString(4));

        }
        conn.close();
        return artist1;
    }


public void saveArtist(Artist artist1) throws SQLException {

    if (isArtistExist(artist1.getId())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Duplicate Artist ID");
        alert.setHeaderText(null);
        alert.setContentText("An artist with the same ID already exists in the database.");
        alert.showAndWait();
        return;
    }

    String query = "INSERT INTO artist (id, name, birth_date, country) VALUES (?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stm = conn.prepareStatement(query)) {
        stm.setInt(1, artist1.getId());
        stm.setString(2, artist1.getName());
        Date birthDate = Date.valueOf(artist1.getBirth_date());
        stm.setDate(3, birthDate);
        stm.setString(4, artist1.getCountry());
        stm.executeUpdate();
    }
}
    private boolean isArtistExist(int artistId) throws SQLException {
        String query = "SELECT id FROM artist WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setInt(1, artistId);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void deleteArtist(int artistId) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stm = conn.createStatement();
        String query = "DELETE FROM artist WHERE id = " + artistId;
        stm.executeUpdate(query);
    }

    public void updateArtist(Artist artist) throws SQLException {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stm = conn.createStatement();

            String query = "UPDATE artist SET name = '" + artist.getName() + "', birth_date = '" + artist.getBirth_date() + "', country = '" + artist.getCountry() + "' WHERE id = " + artist.getId();

            stm.executeUpdate(query);

            System.out.println("Artist informations successfully updated.");
    }
}
