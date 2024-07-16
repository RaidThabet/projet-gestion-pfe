package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StudentTableController implements Initializable {

    @FXML
    private TableView<EtudiantForm> studentTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateStudentTable();
    }

    private void populateStudentTable() {
        if (studentTable != null) {
            System.out.println("inside populateStudentTable");
            EtudiantDAO etudiantDAO = new EtudiantDAO();
            try {
                List<EtudiantForm> etudiants = etudiantDAO.getAllEtudiants();
                ObservableList<EtudiantForm> data = FXCollections.observableArrayList(etudiants);
                studentTable.setItems(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("studentTable is null");
        }
    }
}