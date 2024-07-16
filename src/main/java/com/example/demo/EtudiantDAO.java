package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EtudiantDAO {
    private static final Logger LOGGER = Logger.getLogger(HelloController.class.getName());


    public void insertEtudiant(EtudiantForm etudiantForm) throws SQLException {
        try {
            String sql = "INSERT INTO etudiant (nomPrenom, cin, dateNaissance, lieuNaissance, adresse, email, diplome, specialite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager
                    .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "raid");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, etudiantForm.getNomPrenom());
            preparedStatement.setString(2, etudiantForm.getCin());
            preparedStatement.setString(3, etudiantForm.getDateNaissance());
            preparedStatement.setString(4, etudiantForm.getLieuNaissance());
            preparedStatement.setString(5, etudiantForm.getAdresse());
            preparedStatement.setString(6, etudiantForm.getEmail());
            preparedStatement.setString(7, etudiantForm.getDiplome());
            preparedStatement.setString(8, etudiantForm.getSpecialite());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void updateEtudiant(String matricule, EtudiantForm newEtudiantForm, EtudiantForm oldEtudiantForm) throws SQLException {
        String sql = "UPDATE etudiant SET nomPrenom = ?, cin = ?, dateNaissance = ?, lieuNaissance = ?, adresse = ?, email = ?, diplome = ?, specialite = ? WHERE cin = ?";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager
                    .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "raid");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newEtudiantForm.getNomPrenom() != null && !newEtudiantForm.getNomPrenom().isEmpty() ? newEtudiantForm.getNomPrenom() : oldEtudiantForm.getNomPrenom());
            preparedStatement.setString(2, newEtudiantForm.getCin() != null && !newEtudiantForm.getCin().isEmpty() ? newEtudiantForm.getCin() : oldEtudiantForm.getCin());
            preparedStatement.setString(3, newEtudiantForm.getDateNaissance() != null && !newEtudiantForm.getDateNaissance().isEmpty() ? newEtudiantForm.getDateNaissance() : oldEtudiantForm.getDateNaissance());
            preparedStatement.setString(4, newEtudiantForm.getLieuNaissance() != null && !newEtudiantForm.getLieuNaissance().isEmpty() ? newEtudiantForm.getLieuNaissance() : oldEtudiantForm.getLieuNaissance());
            preparedStatement.setString(5, newEtudiantForm.getAdresse() != null && !newEtudiantForm.getAdresse().isEmpty() ? newEtudiantForm.getAdresse() : oldEtudiantForm.getAdresse());
            preparedStatement.setString(6, newEtudiantForm.getEmail() != null && !newEtudiantForm.getEmail().isEmpty() ? newEtudiantForm.getEmail() : oldEtudiantForm.getEmail());
            preparedStatement.setString(7, newEtudiantForm.getDiplome() != null && !newEtudiantForm.getDiplome().isEmpty() ? newEtudiantForm.getDiplome() : oldEtudiantForm.getDiplome());
            preparedStatement.setString(8, newEtudiantForm.getSpecialite() != null && !newEtudiantForm.getSpecialite().isEmpty() ? newEtudiantForm.getSpecialite() : oldEtudiantForm.getSpecialite());
            preparedStatement.setString(9, matricule);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public List<EtudiantForm> getAllEtudiants() throws SQLException {
        List<EtudiantForm> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM ETUDIANT";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager
                    .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "raid");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                EtudiantForm etudiant = new EtudiantForm(
                        rs.getString("nomPrenom"),
                        rs.getString("cin"),
                        rs.getString("dateNaissance"),
                        rs.getString("lieuNaissance"),
                        rs.getString("adresse"),
                        rs.getString("email"),
                        rs.getString("diplome"),
                        rs.getString("specialite")
                );
                etudiants.add(etudiant);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
        } catch (SQLException e) {
            printSQLException(e);
        }

        return etudiants;
    }

    public EtudiantForm getEtudiantByMatricule(String matricule) throws SQLException {
        String sql = "SELECT * FROM ETUDIANT WHERE cin = ?";
        EtudiantForm etudiant = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager
                    .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "raid");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, matricule);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                etudiant = new EtudiantForm(
                        rs.getString("nomPrenom"),
                        rs.getString("cin"),
                        rs.getString("dateNaissance"),
                        rs.getString("lieuNaissance"),
                        rs.getString("adresse"),
                        rs.getString("email"),
                        rs.getString("diplome"),
                        rs.getString("specialite")
                );
            }
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "An SQL exception occurred", e);
        } catch (SQLException e) {
            printSQLException(e);
        }
        return etudiant;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}