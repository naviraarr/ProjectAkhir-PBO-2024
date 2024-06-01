package com.projectakhir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecruiterController {
    private Connection connection;

    public RecruiterController(Connection connection) {
        this.connection = connection;
    }

    public void addRecruiter(Recruiter recruiter) throws SQLException {
        String queryrc = "INSERT INTO recruiter (id_akun, nama, email, no_tlp, alamat, instansi, status) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatementRecruiter = connection.prepareStatement(queryrc)) {
            preparedStatementRecruiter.setInt(1, recruiter.getId());
            preparedStatementRecruiter.setString(2, recruiter.getNama());
            preparedStatementRecruiter.setString(3, recruiter.getEmail());
            preparedStatementRecruiter.setString(4, recruiter.getNoTlp());
            preparedStatementRecruiter.setString(5, recruiter.getAlamat());
            preparedStatementRecruiter.setString(6, recruiter.getInstansi());
            preparedStatementRecruiter.setString(7, recruiter.getStatus());
            preparedStatementRecruiter.executeUpdate();
        }   
    }

    public void addAkunRecruiter(Recruiter recruiter) throws SQLException {
        String queryakun = "INSERT INTO akun (id_akun, username, password, role) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatementAkun = connection.prepareStatement(queryakun)) {
            preparedStatementAkun.setInt(1,  recruiter.getId());
            preparedStatementAkun.setString(2,  recruiter.getUsername());
            preparedStatementAkun.setString(3,  recruiter.getPassword());
            preparedStatementAkun.setString(4,  recruiter.getRole());
            preparedStatementAkun.executeUpdate();
        }   
    }

    public List<Recruiter> getAllRecruiter() throws SQLException {
        List<Recruiter> rc = new ArrayList<>();
        String query = "SELECT * FROM recruiter";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        rc.add(new Recruiter(
                        resultSet.getInt("id_akun"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getInt("id_recruiter"),
                        resultSet.getString("nama_job"),
                        resultSet.getString("email"),
                        resultSet.getString("no_tlp"),
                        resultSet.getString("alamat"),
                        resultSet.getString("intansi"),
                        resultSet.getString("status")
                        )
                    );
                    }
        }
        return rc;
    }

    public Recruiter getRecruiterById(int id_akun) throws SQLException {
        String queryAkun = "SELECT * FROM akun WHERE id_akun = ?";
        String queryRc = "SELECT * FROM recruiter WHERE id_akun = ?";

        try (PreparedStatement preparedStatementAkun = connection.prepareStatement(queryAkun)) {
            preparedStatementAkun.setInt(1, id_akun);
            try (ResultSet resultSetAkun = preparedStatementAkun.executeQuery()) {
                if (resultSetAkun.next()) {
                    try (PreparedStatement preparedStatementRc = connection.prepareStatement(queryRc)) {
                        preparedStatementRc.setInt(1, id_akun);
                        try (ResultSet resultSetRc = preparedStatementRc.executeQuery()) {
                            if (resultSetRc.next()) {
                                return new Recruiter(
                                    resultSetAkun.getInt("id_akun"),
                                    resultSetAkun.getString("username"),
                                    resultSetAkun.getString("password"),
                                    resultSetAkun.getString("role"),
                                    resultSetRc.getInt("id_recruiter"),
                                    resultSetRc.getString("nama"),
                                    resultSetRc.getString("email"),
                                    resultSetRc.getString("no_tlp"),
                                    resultSetRc.getString("alamat"),
                                    resultSetRc.getString("instansi"),
                                    resultSetRc.getString("status")
                                );
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void updateRecruiter(Recruiter recruiter, int id_recruiter) throws SQLException {
        String query = "UPDATE recruiter SET nama = ?, email = ?, no_tlp = ?, alamat = ?, instansi = ? WHERE id_recruiter = ?";
        try (PreparedStatement preparedStatementJobSeeker = connection.prepareStatement(query)) {
            preparedStatementJobSeeker.setString(1, recruiter.getNama());
            preparedStatementJobSeeker.setString(2, recruiter.getEmail());
            preparedStatementJobSeeker.setString(3, recruiter.getNoTlp());
            preparedStatementJobSeeker.setString(4, recruiter.getAlamat());
            preparedStatementJobSeeker.setString(5, recruiter.getInstansi());
            preparedStatementJobSeeker.setInt(6, id_recruiter);
            preparedStatementJobSeeker.executeUpdate();
        }
    }

}
