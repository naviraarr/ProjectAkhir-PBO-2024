package com.projectakhir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
