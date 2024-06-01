package com.projectakhir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobController {
    private Connection connection;

    public JobController(Connection connection) {
        this.connection = connection;
    }
    
    public void addJob(Job job) throws SQLException{
        String query = "INSERT INTO job (id_recruiter, job_name, description, salary, category) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatementRecruiter = connection.prepareStatement(query)) {
            preparedStatementRecruiter.setInt(1, job.getIdRecruiter());
            preparedStatementRecruiter.setString(2, job.getJobName());
            preparedStatementRecruiter.setString(3, job.getDescription());
            preparedStatementRecruiter.setString(4, job.getSalary());
            preparedStatementRecruiter.setString(5, job.getCategory());
            preparedStatementRecruiter.executeUpdate();
        }  
    }

    public List<Job> getAllJob() throws SQLException {
        List<Job> job = new ArrayList<>();
        String query = "SELECT * FROM job";
        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        job.add(new Job(
                            resultSet.getInt("id_job"),
                            resultSet.getInt("id_recruiter"),
                            resultSet.getString("job_name"),
                            resultSet.getString("description"),
                            resultSet.getString("salary"),
                            resultSet.getString("category")
                            )
                    );
                    }
        }
        return job;
    }

    public List<Job> getAllJob(int id_recruiter) throws SQLException {
        List<Job> job = new ArrayList<>();
        String query = "SELECT * FROM job WHERE id_recruiter = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id_recruiter);
            ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    while (resultSet.next()) {
                        job.add(new Job(
                        resultSet.getInt("id_job"),
                        resultSet.getInt("id_recruiter"),
                        resultSet.getString("job_name"),
                        resultSet.getString("description"),
                        resultSet.getString("salary"),
                        resultSet.getString("category")
                        )
                        );
                    }
                }
        }
        return job;
    }

    public Job getJobById(int id_job) throws SQLException {
        String query = "SELECT * FROM job WHERE id_job = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id_job);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Job(resultSet.getInt("id_job"),
                            resultSet.getInt("id_recruiter"),
                            resultSet.getString("job_name"),
                            resultSet.getString("description"),
                            resultSet.getString("salary"),
                            resultSet.getString("category"));
                        }
            }
        }
        return null;
    }

    public void updateJob(Job job, int id_job) throws SQLException {
        String query = "UPDATE job SET job_name = ?, description = ?, salary = ?, category = ? WHERE id_job = ?";
        try (PreparedStatement preparedStatementJob = connection.prepareStatement(query)) {
            preparedStatementJob.setString(1, job.getJobName());
            preparedStatementJob.setString(2, job.getDescription());
            preparedStatementJob.setString(3, job.getSalary());
            preparedStatementJob.setString(4, job.getCategory());
            preparedStatementJob.setInt(9, id_job);
            preparedStatementJob.executeUpdate();
        }
    }
}
