package com.example.demo;

import java.sql.*;
import java.util.*;


public class SoutenanceDAO {

    public void insertSoutenance(SoutenanceForm soutenance) throws SQLException {
        try {
            String query = "INSERT INTO SOUTENANCE (IDSOUTENANCE, DATESOUTENANCE, HEURESOUTENANCE, SALLESOUTENANCE, IDJURYSOUTENANCE, NOMSETUDIANTS) VALUES (?, ?, ?, ?, ?, ?)";
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "raid");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, soutenance.getIdSoutenance());
            preparedStatement.setString(2, soutenance.getDateSoutenance());
            preparedStatement.setString(3, soutenance.getHeureSoutenance());
            preparedStatement.setString(4, soutenance.getSalleSoutenance());
            preparedStatement.setString(5, soutenance.getIdJurySoutenance());
            preparedStatement.setString(6, soutenance.getNomsEtudiants());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public List<SoutenanceForm> getAllSoutenance() throws SQLException{
        List<SoutenanceForm> soutenances = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "raid");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SOUTENANCE");
            while (resultSet.next()) {
                String idSoutenance = resultSet.getString("IDSOUTENANCE");
                String dateSoutenance = resultSet.getString("DATESOUTENANCE");
                String heureSoutenance = resultSet.getString("HEURESOUTENANCE");
                String salleSoutenance = resultSet.getString("SALLESOUTENANCE");
                String idJurySoutenance = resultSet.getString("IDJURYSOUTENANCE");
                String nomsEtudiants = resultSet.getString("NOMSETUDIANTS");
                soutenances.add(new SoutenanceForm(idSoutenance, dateSoutenance, heureSoutenance, salleSoutenance, idJurySoutenance, nomsEtudiants));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return soutenances;

    }

    public SoutenanceForm getSoutenanceById(String idSoutenance) throws SQLException {
        SoutenanceForm soutenance = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "raid");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM SOUTENANCE WHERE IDSOUTENANCE = ?");
            preparedStatement.setString(1, idSoutenance);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String idSoutenance1 = resultSet.getString("IDSOUTENANCE");
                String dateSoutenance = resultSet.getString("DATESOUTENANCE");
                String heureSoutenance = resultSet.getString("HEURESOUTENANCE");
                String salleSoutenance = resultSet.getString("SALLESOUTENANCE");
                String idJurySoutenance = resultSet.getString("IDJURYSOUTENANCE");
                String nomsEtudiants = resultSet.getString("NOMSETUDIANTS");
                soutenance = new SoutenanceForm(idSoutenance1, dateSoutenance, heureSoutenance, salleSoutenance, idJurySoutenance, nomsEtudiants);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return soutenance;
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
