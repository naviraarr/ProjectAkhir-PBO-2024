package com.projectakhir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AkunController {
    private Connection connection;

    public AkunController(Connection connection) {
        this.connection = connection;
    }

    public int getLastIdAkun() throws SQLException {
        String query = "SELECT max(id_akun) from akun";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1); // get the first column of the result set
            }
        }
        return -1; // return null if no result is found
    }

    // tambah data ke database
    public void registerUser(Akun akun) throws SQLException {
        String query = "INSERT INTO akun (username, password, role) VALUES (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, akun.getUsername());
            preparedStatement.setString(2, akun.getPassword());
            preparedStatement.setString(2, akun.getRole());
            preparedStatement.executeUpdate();
        }
    }

    // membaca data pada database berdasarkan id
    public Akun getAkunById(int id) throws SQLException {
        String query = "SELECT * FROM akun WHERE id_akun = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Akun(resultSet.getInt("id_akun"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"));
                }
            }
        }
        return null;
    }

    // mengambil semua data akun dari database
    public List<Akun> getAllAkun() throws SQLException {
        List<Akun> akun = new ArrayList<>();
        String query = "SELECT * FROM akun";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                akun.add(new Akun(resultSet.getInt("id_akun"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")));
            }
        }
        return akun;
    }

    // update data pada database
    public void updateAkun(Akun akun) throws SQLException {
        String query = "UPDATE akun SET uname = ?, pwd = ? WHERE id_akun = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, akun.getUsername());
            preparedStatement.setString(2, akun.getPassword());
            preparedStatement.setString(2, akun.getRole());
            preparedStatement.executeUpdate();
        }
    }

    // menghapus data dari database
    public void deleteAkun(int id) throws SQLException {
        String query = "DELETE FROM akun WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

}
