package com.example.demo;

public class SoutenanceForm {

    private String idSoutenance;
    private String dateSoutenance;
    private String heureSoutenance;
    private String salleSoutenance;
    private String idJurySoutenance;
    private String nomsEtudiants;
    private String note;
    private String validation;

    public SoutenanceForm(String idSoutenance, String dateSoutenance, String heureSoutenance,
                          String salleSoutenance, String idJurySoutenance, String nomsEtudiants,
                          String note, String validation) {
        this.idSoutenance = idSoutenance;
        this.dateSoutenance = dateSoutenance;
        this.heureSoutenance = heureSoutenance;
        this.salleSoutenance = salleSoutenance;
        this.idJurySoutenance = idJurySoutenance;
        this.nomsEtudiants = nomsEtudiants;
        this.note = note;
        this.validation = validation;
    }

    public String getIdSoutenance() {
        return idSoutenance;
    }

    public void setIdSoutenance(String idSoutenance) {
        this.idSoutenance = idSoutenance;
    }

    public String getDateSoutenance() {
        return dateSoutenance;
    }

    public void setDateSoutenance(String dateSoutenance) {
        this.dateSoutenance = dateSoutenance;
    }

    public String getHeureSoutenance() {
        return heureSoutenance;
    }

    public void setHeureSoutenance(String heureSoutenance) {
        this.heureSoutenance = heureSoutenance;
    }

    public String getSalleSoutenance() {
        return salleSoutenance;
    }

    public void setSalleSoutenance(String salleSoutenance) {
        this.salleSoutenance = salleSoutenance;
    }

    public String getIdJurySoutenance() {
        return idJurySoutenance;
    }

    public void setidJurySoutenance(String idJurySoutenance) {
        this.idJurySoutenance = idJurySoutenance;
    }

    public String getNomsEtudiants() {
        return nomsEtudiants;
    }

    public void setNomsEtudiants(String nomsEtudiants) {
        this.nomsEtudiants = nomsEtudiants;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }
}
