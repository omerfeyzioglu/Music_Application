package com.dims.lasttest.Controller;

import com.dims.lasttest.Model.Artist;
import com.dims.lasttest.MusicApplication;
import com.dims.lasttest.Repository.ArtistRepository;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ArtistController {

    @FXML
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    @FXML
    private TextField txt1, txt2, txt3, txt4;


    @FXML
    void add(ActionEvent event) throws Exception {
        ArtistRepository artistRepository = new ArtistRepository();
        Artist artist1 = new Artist();
        artist1.setId(Integer.parseInt(txt1.getText()));
        artist1.setName(txt2.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(txt3.getText(), formatter);
        artist1.setBirth_date(birthDate);

        artist1.setCountry(txt4.getText());
        artistRepository.saveArtist(artist1);
    }


    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) btn6.getScene().getWindow();
        stage.close();
    }

    @FXML

    void delete(ActionEvent event) throws Exception {
            ArtistRepository artistRepository = new ArtistRepository();

            int artistIdToDelete = Integer.parseInt(txt1.getText());

            artistRepository.deleteArtist(artistIdToDelete);

        }



    @FXML
    void clean(ActionEvent event) {
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
    }

    @FXML
    void fetch(ActionEvent event) throws Exception {
        ArtistRepository artistRepository = new ArtistRepository();
        Artist artist1 = artistRepository.getArtistBYID(Integer.parseInt(txt1.getText()));
        if ((artist1.getId()== 0)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "There is no record have this id!" , ButtonType.CLOSE);
            alert.showAndWait();
            txt1.setText("");
            txt1.requestFocus();
        } else {
            txt2.setText(artist1.getName());
            txt3.setText(String.valueOf(artist1.getBirth_date()));
            txt4.setText(artist1.getCountry());
        }
    }

    @FXML
    void showSong(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("song.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600  , 400);
        stage.setTitle("Song");
        stage.setScene(scene);
        stage.show();
    }

    @FXML

        void update(ActionEvent event) throws Exception {
            ArtistRepository artistRepository = new ArtistRepository();

            int artistIdToUpdate = Integer.parseInt(txt1.getText());
            Artist existingArtist = artistRepository.getArtistBYID(artistIdToUpdate);
            if (existingArtist != null) {

                existingArtist.setId(Integer.parseInt(txt1.getText()));

                existingArtist.setName(txt2.getText());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthDate = LocalDate.parse(txt3.getText(), formatter);
                existingArtist.setBirth_date(birthDate);

                existingArtist.setCountry(txt4.getText());

                artistRepository.updateArtist(existingArtist);
            } else {
                System.out.println("No such artist.");
            }
        }





}

