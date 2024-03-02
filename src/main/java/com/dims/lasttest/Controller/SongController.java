package com.dims.lasttest.Controller;



import com.dims.lasttest.Model.Song;
import com.dims.lasttest.Repository.SongRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class SongController {

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt3;

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) btn5.getScene().getWindow();
        stage.close();
    }

    @FXML
    void add(ActionEvent event) {
        SongRepository songRepository = new SongRepository();
        Song song1 = new Song();
        song1.setId(Integer.parseInt(txt1.getText()));
        song1.setTitle(txt2.getText());
        String durationText = txt3.getText();

        try {
            LocalTime durationTime = LocalTime.parse(durationText);
            song1.setDuration_time(durationTime);


            songRepository.saveSong(song1);

            System.out.println("Save successful");
        } catch (DateTimeParseException | SQLException e) {
            System.err.println("Invalid time format. Please enter a valid time format.");
        }
    }

    @FXML
    void delete(ActionEvent event) throws SQLException {
        SongRepository songRepository = new SongRepository();
        int songIdToDelete = Integer.parseInt(txt1.getText());
        songRepository.deleteSong(songIdToDelete);
    }

    @FXML
    void fetch(ActionEvent event) throws Exception {
        SongRepository songRepository = new SongRepository();
        Song song1 = songRepository.getSongById(Integer.parseInt(txt1.getText()));
        if ((song1.getId()==0))
              {Alert alert = new Alert(Alert.AlertType.INFORMATION, "There is no record has this id!" , ButtonType.CLOSE);
            alert.showAndWait();
            txt1.setText("");
            txt1.requestFocus();
          }
        else{txt2.setText(song1.getTitle());
            txt3.setText(String.valueOf(song1.getDuration_time()));


}

    }

    @FXML
    void update(ActionEvent event) throws SQLException {
        SongRepository songRepository = new SongRepository();
        int songIdToUpdate = Integer.parseInt(txt1.getText());
        Song anySong = songRepository.getSongById(songIdToUpdate);
        if (anySong != null){
            anySong.setId(Integer.parseInt(txt1.getText()));

            anySong.setTitle(txt2.getText());
            String durationText = txt3.getText();


                LocalTime durationTime = LocalTime.parse(durationText);
                anySong.setDuration_time(durationTime);

            songRepository.updateSong(anySong);
        }else{
            System.out.println("No such song.");
        }

    }

    @FXML
    void clean(ActionEvent event){
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");

    }
}
