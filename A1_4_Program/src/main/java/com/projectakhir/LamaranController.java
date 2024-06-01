package com.projectakhir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LamaranController {
    private Connection connection;

    public LamaranController(Connection connection) {
        this.connection = connection;
    }
    
    public void addLamaran(Lamaran lamaran) throws SQLException{
        String query = "INSERT INTO lamaran (id_job, id_jobseeker, status) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatementRecruiter = connection.prepareStatement(query)) {
            preparedStatementRecruiter.setInt(1, lamaran.getIdJob());
            preparedStatementRecruiter.setInt(2, lamaran.getIdJobseeker());
            preparedStatementRecruiter.setString(3, lamaran.getStatus());
            preparedStatementRecruiter.executeUpdate();
        }  
    }

    public List<Lamaran> getAllLamaran() throws SQLException {
        List<Lamaran> lamaran = new ArrayList<>();
        String query = "SELECT * FROM lamaran";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        lamaran.add(new Lamaran(
                        resultSet.getInt("id_lamaran"),
                        resultSet.getInt("id_job"),
                        resultSet.getInt("id_jobseeker"),
                        resultSet.getString("status")
                        )
                    );
                    }
        }
        return lamaran;
    }

    // public List<Lamaran> getAllLamaran(int id_recruiter) throws SQLException {
    //     List<Lamaran> lamaran = new ArrayList<>();
    //     String query = "SELECT * FROM lamaran WHERE id_job = ?";
    //     try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    //         preparedStatement.setInt(1, id_recruiter);
    //         ResultSet resultSet = preparedStatement.executeQuery();
    //             if (resultSet.next()) {
    //                 while (resultSet.next()) {
    //                     lamaran.add(new Lamaran(
    //                     resultSet.getInt("id_lamaran"),
    //                     resultSet.getInt("id_job"),
    //                     resultSet.getInt("id_jobseeker"),
    //                     resultSet.getString("status")
    //                     )
    //                     );
    //                 }
    //             }
    //     }
    //     return lamaran;
    // }

    public void updateLamaran(Lamaran lamaran, int id_lamaran) throws SQLException {
        String query = "UPDATE lamaran SET status = ? WHERE id_lamaran = ?";
        try (PreparedStatement preparedStatementJobSeeker = connection.prepareStatement(query)) {
            preparedStatementJobSeeker.setString(1, lamaran.getStatus());
            preparedStatementJobSeeker.setInt(2, id_lamaran);
            preparedStatementJobSeeker.executeUpdate();
        }
    }

}
