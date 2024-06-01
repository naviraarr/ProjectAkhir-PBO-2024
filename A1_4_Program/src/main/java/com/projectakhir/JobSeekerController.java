package com.projectakhir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobSeekerController {
    private Connection connection;

    public JobSeekerController(Connection connection) {
        this.connection = connection;
    }

    public void addJobSeeker(JobSeeker jobSeeker) throws SQLException {
        String queryjs = "INSERT INTO jobseeker (id_akun, nama, email, no_tlp, tgl_lahir, jenis_kelamin, alamat, kota, keahlian) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatementJobSeeker = connection.prepareStatement(queryjs)) {
            preparedStatementJobSeeker.setInt(1, jobSeeker.getId());
            preparedStatementJobSeeker.setString(2, jobSeeker.getNama());
            preparedStatementJobSeeker.setString(3, jobSeeker.getEmail());
            preparedStatementJobSeeker.setString(4, jobSeeker.getNotelp());
            preparedStatementJobSeeker.setString(5, jobSeeker.getTgllahir());
            preparedStatementJobSeeker.setString(6, jobSeeker.getJenisKelamin());
            preparedStatementJobSeeker.setString(7, jobSeeker.getAlamat());
            preparedStatementJobSeeker.setString(8, jobSeeker.getKota());
            preparedStatementJobSeeker.setString(9, jobSeeker.getKeahlian());
            preparedStatementJobSeeker.executeUpdate();
        }   
    }

    public void addAkunJobSeeker(JobSeeker jobSeeker) throws SQLException {
        String queryakun = "INSERT INTO akun (id_akun, username, password, role) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatementAkun = connection.prepareStatement(queryakun)) {
            preparedStatementAkun.setInt(1, jobSeeker.getId());
            preparedStatementAkun.setString(2, jobSeeker.getUsername());
            preparedStatementAkun.setString(3, jobSeeker.getPassword());
            preparedStatementAkun.setString(4, jobSeeker.getRole());
            preparedStatementAkun.executeUpdate();
        }   
    }

    public List<JobSeeker> getAllJobSeeker() throws SQLException {
        List<JobSeeker> js = new ArrayList<>();
        String query = "SELECT * FROM jobseeker";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        js.add(new JobSeeker(
                        resultSet.getInt("id_akun"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getInt("id_jobseeker"),
                        resultSet.getString("nama"),
                        resultSet.getString("email"),
                        resultSet.getString("no_tlp"),
                        resultSet.getString("tgl_lahir"),
                        resultSet.getString("jenis_kelamin"),
                        resultSet.getString("alamat"),
                        resultSet.getString("kota"),
                        resultSet.getString("keahlian")
                        )
                    );
                    }
        }
        return js;
    }

    // membaca data pada database berdasarkan id
    public JobSeeker getJobSeekerById(int id_akun) throws SQLException {
        String queryAkun = "SELECT * FROM akun WHERE id_akun = ?";
        String queryJs = "SELECT * FROM jobseeker WHERE id_akun = ?";

        try (PreparedStatement preparedStatementAkun = connection.prepareStatement(queryAkun)) {
            preparedStatementAkun.setInt(1, id_akun);
            try (ResultSet resultSetAkun = preparedStatementAkun.executeQuery()) {
                if (resultSetAkun.next()) {
                    try (PreparedStatement preparedStatementJs = connection.prepareStatement(queryJs)) {
                        preparedStatementJs.setInt(1, id_akun);
                        try (ResultSet resultSetJs = preparedStatementJs.executeQuery()) {
                            if (resultSetJs.next()) {
                                return new JobSeeker(
                                    resultSetAkun.getInt("id_akun"),
                                    resultSetAkun.getString("username"),
                                    resultSetAkun.getString("password"),
                                    resultSetAkun.getString("role"),
                                    resultSetJs.getInt("id_jobseeker"),
                                    resultSetJs.getString("nama"),
                                    resultSetJs.getString("email"),
                                    resultSetJs.getString("no_tlp"),
                                    resultSetJs.getString("tgl_lahir"),
                                    resultSetJs.getString("jenis_kelamin"),
                                    resultSetJs.getString("alamat"),
                                    resultSetJs.getString("kota"),
                                    resultSetJs.getString("keahlian")
                                );
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    // update data pada database
    public void updateJobSeeker(JobSeeker jobSeeker, int id_jobseeker) throws SQLException {
        String query = "UPDATE jobseeker SET nama = ?, email = ?, no_tlp = ?, tgl_lahir = ?, jenis_kelamin = ?, alamat = ?, kota = ?, keahlian = ? WHERE id_jobseeker = ?";
        try (PreparedStatement preparedStatementJobSeeker = connection.prepareStatement(query)) {
            preparedStatementJobSeeker.setString(1, jobSeeker.getNama());
            preparedStatementJobSeeker.setString(2, jobSeeker.getEmail());
            preparedStatementJobSeeker.setString(3, jobSeeker.getNotelp());
            preparedStatementJobSeeker.setString(4, jobSeeker.getTgllahir());
            preparedStatementJobSeeker.setString(5, jobSeeker.getJenisKelamin());
            preparedStatementJobSeeker.setString(6, jobSeeker.getAlamat());
            preparedStatementJobSeeker.setString(7, jobSeeker.getKota());
            preparedStatementJobSeeker.setString(8, jobSeeker.getKeahlian());
            preparedStatementJobSeeker.setInt(9, id_jobseeker);
            preparedStatementJobSeeker.executeUpdate();
        }
    }
}
