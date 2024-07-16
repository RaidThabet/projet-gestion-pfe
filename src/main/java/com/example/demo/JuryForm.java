package com.example.demo;

public class JuryForm {
    private String idJury;
    private String nomPresident;
    private String nomRapporteur;
    private String nomExaminateur;
    private String nomsEncadreurs;
    
    public JuryForm(String idJury, String nomPresident, String nomRapporteur, String nomExaminateur, String nomsEncadreurs) {
        this.idJury = idJury;
        this.nomPresident = nomPresident;
        this.nomRapporteur = nomRapporteur;
        this.nomExaminateur = nomExaminateur;
        this.nomsEncadreurs = nomsEncadreurs;
    }

    public String getIdJury() {
        return idJury;
    }

    public void setIdJury(String idJury) {
        this.idJury = idJury;
    }

    public String getNomPresident() {
        return nomPresident;
    }

    public void setNomPresident(String nomPresident) {
        this.nomPresident = nomPresident;
    }

    public String getNomRapporteur() {
        return nomRapporteur;
    }

    public void setNomRapporteur(String nomRapporteur) {
        this.nomRapporteur = nomRapporteur;
    }

    public String getNomExaminateur() {
        return nomExaminateur;
    }

    public void setNomExaminateur(String nomExaminateur) {
        this.nomExaminateur = nomExaminateur;
    }

    public String getNomsEncadreurs() {
        return nomsEncadreurs;
    }

    public void setNomsEncadreurs(String nomsEncadreurs) {
        this.nomsEncadreurs = nomsEncadreurs;
    }
}
