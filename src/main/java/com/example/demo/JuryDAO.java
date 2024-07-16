package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JuryDAO {

        public void insertJury(JuryForm juryForm) throws SQLException {
            try {
                String sql = "INSERT INTO jury (idJury, nomPresident, nomRapporteur, nomExaminateur, nomsEncadreurs) VALUES (?, ?, ?, ?, ?)";
                Class.forName("oracle.jdbc.OracleDriver");
                Connection connection = DriverManager
                        .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "raid");
                connection.setAutoCommit(false);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, juryForm.getIdJury());
                preparedStatement.setString(2, juryForm.getNomPresident());
                preparedStatement.setString(3, juryForm.getNomRapporteur());
                preparedStatement.setString(4, juryForm.getNomExaminateur());
                preparedStatement.setString(5, juryForm.getNomsEncadreurs());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
                connection.commit();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                printSQLException(e);
            }
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

    public List<JuryForm> getAllJuries() throws SQLException {
        List<JuryForm> juries = new ArrayList<>();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager
                    .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "raid");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM jury");
            while (resultSet.next()) {
                String idJury = resultSet.getString("idJury");
                String nomPresident = resultSet.getString("nomPresident");
                String nomRapporteur = resultSet.getString("nomRapporteur");
                String nomExaminateur = resultSet.getString("nomExaminateur");
                String nomsEncadreurs = resultSet.getString("nomsEncadreurs");
                juries.add(new JuryForm(idJury, nomPresident, nomRapporteur, nomExaminateur, nomsEncadreurs));
            }
            connection.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return juries;
    }

    public JuryForm getJuryById(String idJury) throws SQLException {
        String sql = "SELECT * FROM jury WHERE idJury = ?";
        JuryForm jury = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection connection = DriverManager
                    .getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "raid");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idJury);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                jury = new JuryForm(
                        rs.getString("idJury"),
                        rs.getString("nomPresident"),
                        rs.getString("nomRapporteur"),
                        rs.getString("nomExaminateur"),
                        rs.getString("nomsEncadreurs")
                );
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return jury;
    }


}
