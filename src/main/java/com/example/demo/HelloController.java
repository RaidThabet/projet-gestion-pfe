package com.example.demo;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(HelloController.class.getName());


    public Button ajouterJuryButton;
    public Button ajouterSoutenanceButon;
    public Button modifierEtudiantButton;
    public Button validerSoutenanceButon;

    @FXML
    private Label buttonAjouterEtudiant;

    // Informations Etudiant
    @FXML
    private TextField nomEtudiant;
    @FXML
    private TextField cinEtudiant;
    @FXML
    private DatePicker dateNaissanceEtudiant;
    @FXML
    private TextField lieuNaissanceEtudiant;
    @FXML
    private TextField adresseEtudiant;
    @FXML
    private TextField emailEtudiant;
    @FXML
    private ComboBox<String> diplomeEtudiant;
    @FXML
    private ComboBox<String> specialiteEtudiant;
    @FXML
    private TableView<EtudiantForm> studentTable;

    @FXML
    private TextField matEtudiant;
    @FXML
    private TextField nvNomEtudiant;
    @FXML
    private DatePicker nvDateNaissanceEtudiant;
    @FXML
    private TextField nvCinEtudiant;
    @FXML
    private TextField nvLieuNaissanceEtudiant;
    @FXML
    private TextField nvAdresseEtudiant;
    @FXML
    private TextField nvEmailEtudiant;
    @FXML
    private ComboBox<String> nvDiplomeEtudiant;
    @FXML
    private ComboBox<String> nvSpecialiteEtudiant;

    //Information Jury
    @FXML
    private TextField idJury;
    @FXML
    private TextField nomPresident;
    @FXML
    private TextField nomRapporteur;
    @FXML
    private TextField nomExaminateur;
    @FXML
    private TextField nomsEncadreurs;
    @FXML
    private TableView<JuryForm> juryTable;

    //Informations Soutenance
    @FXML
    private DatePicker dateSoutenance;
    @FXML
    private TextField heureSoutenance;
    @FXML
    private TextField salleSoutenance;
    @FXML
    private TextField idJurySoutenance;
    @FXML
    private TextField nomsEtudiants;
    @FXML
    private DatePicker dateValidationSoutenance;
    @FXML
    private TextField heureValidationSoutenance;
    @FXML
    private TextField salleValidationSoutenance;
    @FXML
    private TextField idJuryValidationSoutenance;
    @FXML
    private TextField noteValidationSoutenance;
    @FXML
    private TextField appreciationValidationSoutenance;
    @FXML
    private TableView<SoutenanceForm> soutenanceTable;



    @FXML
    private Node ajouterEtudiantPage, modifierEtudiantPage,  ajouterJuryPage, rechercheJuryPage
            ,ajouterSoutenancePage, validerSoutenancePage, rechercherSoutenancePage;

    @FXML
    private Node rechercheEtudiantPage;

    @FXML
    private StackPane pagesStackPane;




    @FXML
    private Button ajouterEtudiantButton;



    public void initialize(URL url, ResourceBundle rb) {

        hideAllPages();
        setupAjouterButtonEtudiantBinding();
        setupAjouterButtonJuryBinding();
        setupAjouterButtonSoutenanceBinding();
        setupModifyButtonEtudiantBinding();
        setupModifyButtonSoutenanceBinding();

    }

    @FXML

    private void setupAjouterButtonEtudiantBinding() {
        if (ajouterEtudiantButton == null || nomEtudiant == null || cinEtudiant == null || dateNaissanceEtudiant == null || lieuNaissanceEtudiant == null || adresseEtudiant == null || emailEtudiant == null || diplomeEtudiant == null || specialiteEtudiant == null) {
            return;
        }
        BooleanBinding areFieldsEmpty = Bindings.createBooleanBinding(() -> nomEtudiant.textProperty().get().trim().isEmpty(), nomEtudiant.textProperty())
                .or(Bindings.createBooleanBinding(() -> cinEtudiant.textProperty().get().trim().isEmpty(), cinEtudiant.textProperty()))
                .or(Bindings.createBooleanBinding(() -> !cinEtudiant.textProperty().get().matches("\\d{8}"), cinEtudiant.textProperty()))
                .or(dateNaissanceEtudiant.valueProperty().isNull())
                .or(Bindings.createBooleanBinding(() -> lieuNaissanceEtudiant.textProperty().get().trim().isEmpty(), lieuNaissanceEtudiant.textProperty()))
                .or(Bindings.createBooleanBinding(() -> adresseEtudiant.textProperty().get().trim().isEmpty(), adresseEtudiant.textProperty()))
                .or(Bindings.createBooleanBinding(() -> emailEtudiant.textProperty().get().trim().isEmpty(), emailEtudiant.textProperty()))
                .or(diplomeEtudiant.valueProperty().isNull())
                .or(specialiteEtudiant.valueProperty().isNull());

        ajouterEtudiantButton.disableProperty().bind(areFieldsEmpty);
    }
    @FXML
    private void setupAjouterButtonJuryBinding() {
        if (ajouterJuryButton == null || idJury == null || nomPresident == null || nomRapporteur == null || nomExaminateur == null || nomsEncadreurs == null) {
            return;
        }
        BooleanBinding areFieldsEmpty = Bindings.createBooleanBinding(() -> idJury.textProperty().get().trim().isEmpty(), idJury.textProperty())
                .or(Bindings.createBooleanBinding(() -> nomPresident.textProperty().get().trim().isEmpty(), nomPresident.textProperty()))
                .or(Bindings.createBooleanBinding(() -> nomRapporteur.textProperty().get().trim().isEmpty(), nomRapporteur.textProperty()))
                .or(Bindings.createBooleanBinding(() -> nomExaminateur.textProperty().get().trim().isEmpty(), nomExaminateur.textProperty()))
                .or(Bindings.createBooleanBinding(() -> nomsEncadreurs.textProperty().get().trim().isEmpty(), nomsEncadreurs.textProperty()));

        ajouterJuryButton.disableProperty().bind(areFieldsEmpty);
    }

    @FXML
    private void setupAjouterButtonSoutenanceBinding() {
        if (ajouterSoutenanceButon == null || dateSoutenance == null || heureSoutenance == null || salleSoutenance == null || idJurySoutenance == null || nomsEtudiants == null) {
            return;
        }
        BooleanBinding areFieldsEmpty = (dateSoutenance.valueProperty().isNull())
                .or(Bindings.createBooleanBinding(() -> heureSoutenance.textProperty().get().trim().isEmpty(), heureSoutenance.textProperty()))
                .or(Bindings.createBooleanBinding(() -> salleSoutenance.textProperty().get().trim().isEmpty(), salleSoutenance.textProperty()))
                .or(Bindings.createBooleanBinding(() -> idJurySoutenance.textProperty().get().trim().isEmpty(), idJurySoutenance.textProperty()))
                .or(Bindings.createBooleanBinding(() -> nomsEtudiants.textProperty().get().trim().isEmpty(), nomsEtudiants.textProperty()));

        ajouterSoutenanceButon.disableProperty().bind(areFieldsEmpty);
    }

    @FXML
    private void setupModifyButtonEtudiantBinding() {
        if (modifierEtudiantButton == null || matEtudiant == null ) {
            return;
        }

        BooleanBinding matriculeNonValide = Bindings.createBooleanBinding(() -> matEtudiant.textProperty().get().trim().isEmpty(), matEtudiant.textProperty())
                .or(Bindings.createBooleanBinding(() -> !matEtudiant.textProperty().get().matches("\\d{8}"), matEtudiant.textProperty()));

        modifierEtudiantButton.disableProperty().bind(matriculeNonValide);
    }

    @FXML
    private void setupModifyButtonSoutenanceBinding() {
        if (validerSoutenanceButon == null || salleValidationSoutenance == null || dateValidationSoutenance == null || heureValidationSoutenance == null || idJuryValidationSoutenance == null || noteValidationSoutenance == null || appreciationValidationSoutenance == null) {
            return;
        }

        BooleanBinding areFieldsEmpty = (dateValidationSoutenance.valueProperty().isNull())
                .or(Bindings.createBooleanBinding(() -> heureValidationSoutenance.textProperty().get().trim().isEmpty(), heureValidationSoutenance.textProperty()))
                .or(Bindings.createBooleanBinding(() -> salleValidationSoutenance.textProperty().get().trim().isEmpty(), salleValidationSoutenance.textProperty()))
                .or(Bindings.createBooleanBinding(() -> idJuryValidationSoutenance.textProperty().get().trim().isEmpty(), idJuryValidationSoutenance.textProperty()))
                .or(Bindings.createBooleanBinding(() -> noteValidationSoutenance.textProperty().get().trim().isEmpty(), noteValidationSoutenance.textProperty()))
                .or(Bindings.createBooleanBinding(() -> appreciationValidationSoutenance.textProperty().get().trim().isEmpty(), appreciationValidationSoutenance.textProperty()));

        validerSoutenanceButon.disableProperty().bind(areFieldsEmpty);

    }

    @FXML
    private void hideAllPages() {
        if (pagesStackPane == null) {
            return;
        }
        for(Node page : pagesStackPane.getChildren()) {
            if (page != null)
                page.setVisible(false);
        }
    }

    @FXML
    private void adjustColumnWidthsStudent() {
        if (studentTable == null) {
            return;
        }

        int numColumns = studentTable.getColumns().size();
        for (TableColumn<?, ?> column : studentTable.getColumns()) {
            column.prefWidthProperty().bind(studentTable.widthProperty().divide(numColumns));
        }
    }
    @FXML

    private void adjustColumnWidthsJury() {
        if (juryTable == null) {
            return;
        }

        int numColumns = juryTable.getColumns().size();
        for (TableColumn<?, ?> column : juryTable.getColumns()) {
            column.prefWidthProperty().bind(juryTable.widthProperty().divide(numColumns));
        }
    }
    @FXML

    private void adjustColumnWidthsSoutenance() {
        if (soutenanceTable == null) {
            return;
        }

        int numColumns = soutenanceTable.getColumns().size();
        for (TableColumn<?, ?> column : soutenanceTable.getColumns()) {
            column.prefWidthProperty().bind(soutenanceTable.widthProperty().divide(numColumns));
        }
    }

    @FXML
    private void loadAjouterEtudiant() {
        hideAllPages();
        ajouterEtudiantPage.setVisible(true);
    }

    @FXML
    private void loadModifierEtudiant() {
        hideAllPages();
        modifierEtudiantPage.setVisible(true);
    }

    @FXML
    private void loadRechercheEtudiant() {
        hideAllPages();
        adjustColumnWidthsStudent();
        rechercheEtudiantPage.setVisible(true);
        populateStudentTable();
    }

    @FXML
    private void loadAjouterJury() {
        hideAllPages();
        ajouterJuryPage.setVisible(true);
    }

    @FXML
    private void loadRechercheJury() {
        hideAllPages();
        adjustColumnWidthsJury();
        rechercheJuryPage.setVisible(true);
        populateJuryTable();
    }

    @FXML
    private void loadAjouterSoutenance() {
        hideAllPages();
        ajouterSoutenancePage.setVisible(true);
    }

    @FXML
    private void loadValiderSoutenance() {
        hideAllPages();
        validerSoutenancePage.setVisible(true);
    }

    @FXML
    private void loadRechercherSoutenance() {
        hideAllPages();
        adjustColumnWidthsSoutenance();
        rechercherSoutenancePage.setVisible(true);
        populateSoutenanceTable();
    }

    @FXML
    public void handleSubmitButtonActionEtudiant() {
        EtudiantForm etudiant = new EtudiantForm(nomEtudiant.getText(), cinEtudiant.getText(), dateNaissanceEtudiant.getValue().toString(), lieuNaissanceEtudiant.getText(), adresseEtudiant.getText(), emailEtudiant.getText(), diplomeEtudiant.getValue(), specialiteEtudiant.getValue());
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        try {
            if (etudiantDAO.getEtudiantByMatricule(etudiant.getCin()) == null){
                etudiantDAO.insertEtudiant(etudiant);
                showSuccessDialog("Etudiant ajouté avec succès");
            }
            else{
                showErrorDialog("Etudiant déjà existe");
            }
        } catch (SQLException e) {
            showErrorDialog("Erreur");
            LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
        }
    }

    @FXML
    public void handleModifyButtonActionEtudiant() {
        EtudiantForm newEtudiant = createNewEtudiant();
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        try {
            EtudiantForm oldEtudiant = etudiantDAO.getEtudiantByMatricule(matEtudiant.getText());
            if (oldEtudiant == null) {
                showErrorDialog("Etudiant n'existe pas");
            } else {
                etudiantDAO.updateEtudiant(matEtudiant.getText(), newEtudiant, oldEtudiant);
                showSuccessDialog("Etudiant modifié avec succès");
            }
        } catch (SQLException e) {
            showErrorDialog("Erreur");
            LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
        }
    }

    @FXML
    public void handleSubmitButtonActionJury() {
        JuryForm jury = new JuryForm(idJury.getText(), nomPresident.getText(), nomRapporteur.getText(), nomExaminateur.getText(), nomsEncadreurs.getText());
        JuryDAO juryDAO = new JuryDAO();
        try {
            if (juryDAO.getJuryById(jury.getIdJury()) != null){
                showErrorDialog("Jury existe déjà");
            }
            else{
                juryDAO.insertJury(jury);
                showSuccessDialog("Jury ajouté avec succès");
            }
        } catch (SQLException e) {
            showErrorDialog("Erreur");
            LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
        }
    }

    // @FXML
    // public void handleSubmitButtonActionSoutenance() {
    //     String idSout = dateSoutenance.getValue().toString().replace("-", "") + heureSoutenance.getText().replace(":", "") + idJurySoutenance.getText();
    //     SoutenanceForm soutenance = new SoutenanceForm(idSout, dateSoutenance.getValue().toString(), heureSoutenance.getText(), salleSoutenance.getText(), idJurySoutenance.getText(), nomsEtudiants.getText());
    //     SoutenanceDAO soutenanceDAO = new SoutenanceDAO();
    //     try {
    //         soutenanceDAO.insertSoutenance(soutenance);
    //     } catch (SQLException e) {
    //         LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
    //     }
    // }

    @FXML
    public void handleSubmitButtonActionSoutenance() {
        String idSout = salleSoutenance.getText() + dateSoutenance.getValue().toString().replace("-", "") + heureSoutenance.getText().replace(":", "") + idJurySoutenance.getText();
        SoutenanceForm soutenance = new SoutenanceForm(idSout, dateSoutenance.getValue().toString(), heureSoutenance.getText(), salleSoutenance.getText(), idJurySoutenance.getText(), nomsEtudiants.getText(), "NA", "NA");
        SoutenanceDAO soutenanceDAO = new SoutenanceDAO();
        JuryDAO juryDAO = new JuryDAO();
        try {
            //test if isSoutenance already exists
            if (soutenanceDAO.getSoutenanceById(idSout) != null){
                showErrorDialog("Soutenance existe déjà");
            }

            //test if idJury doesn't exist

            else if (juryDAO.getJuryById(idJurySoutenance.getText()) == null) {
                showErrorDialog("Jury n'existe pas");
            }

            else{
                soutenanceDAO.insertSoutenance(soutenance);
                showSuccessDialog("Soutenance ajoutée avec succès");
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            showErrorDialog("Erreur dans la requête");
        }
    }

    @FXML
    public void handleModifyButtonSoutenance() {
        String idSout = salleValidationSoutenance.getText() + dateValidationSoutenance.getValue().toString().replace("-", "") + heureValidationSoutenance.getText().replace(":", "") + idJuryValidationSoutenance.getText();
        // SoutenanceForm soutenance = new SoutenanceForm(idSout, dateValidationSoutenance.getText(), heureValidationSoutenance.getText(), salleValidationSoutenance.getText(), idJuryValidationSoutenance.getText(), nomsEtudiants.getText(), noteValidationSoutenance.getText(), appreciationValidationSoutenance.getText());
        SoutenanceDAO soutenanceDAO = new SoutenanceDAO();
        JuryDAO juryDAO = new JuryDAO();
        try {
            if (soutenanceDAO.getSoutenanceById(idSout) == null){
                showErrorDialog("Soutenance n'existe pas");
            }
            else{
                soutenanceDAO.modifyNoteAppreciationSoutenance(idSout, noteValidationSoutenance.getText(), appreciationValidationSoutenance.getText());
                showSuccessDialog("Soutenance modifiée avec succès");
            }
        } catch (SQLException e) {
            showErrorDialog("Erreur dans la requête");
        }
    }

    @FXML
    public void populateStudentTable() {
        if (studentTable != null) {
            EtudiantDAO etudiantDAO = new EtudiantDAO();
            try {
                List<EtudiantForm> etudiants = etudiantDAO.getAllEtudiants();
                ObservableList<EtudiantForm> data = FXCollections.observableArrayList(etudiants);
                studentTable.setItems(data);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
            }
        }
    }

    @FXML
    public void populateJuryTable() {
        if (juryTable != null) {
            JuryDAO juryDAO = new JuryDAO();
            try {
                List<JuryForm> juries = juryDAO.getAllJuries();
                ObservableList<JuryForm> data = FXCollections.observableArrayList(juries);
                juryTable.setItems(data);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
            }
        }
    }

    @FXML
    public void populateSoutenanceTable() {
        if (soutenanceTable != null) {
            SoutenanceDAO soutenanceDAO = new SoutenanceDAO();
            try {
                List<SoutenanceForm> soutenances = soutenanceDAO.getAllSoutenance();
                ObservableList<SoutenanceForm> data = FXCollections.observableArrayList(soutenances);
                soutenanceTable.setItems(data);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
            }
        }
    }

    private EtudiantForm createNewEtudiant() {
        String nvDiplome = nvDiplomeEtudiant.getValue() != null ? nvDiplomeEtudiant.getValue() : null;
        String nvSpecialite = nvSpecialiteEtudiant.getValue() != null ? nvSpecialiteEtudiant.getValue() : null;
        String nvDateNaissance = nvDateNaissanceEtudiant.getValue() != null ? nvDateNaissanceEtudiant.getValue().toString() : null;

        return new EtudiantForm(nvNomEtudiant.getText(), nvCinEtudiant.getText(), nvDateNaissance, nvLieuNaissanceEtudiant.getText(), nvAdresseEtudiant.getText(), nvEmailEtudiant.getText(), nvDiplome, nvSpecialite);
    }

    // private SoutenanceForm createNewSoutenance() {
    //     return new SoutenanceForm(dateSoutenance.getValue().toString(), heureSoutenance.getText(),
    //                             salleSoutenance.getText(), idJurySoutenance.getText(),
    //                              nomsEtudiants.getText(), "NA", "NA");
    // }

    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText(errorMessage);
                alert.showAndWait();
    }

    private void showSuccessDialog(String content) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText(content);
                alert.showAndWait();
    }

}