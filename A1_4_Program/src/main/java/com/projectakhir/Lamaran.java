package com.projectakhir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lamaran {
    private int id_lamaran;
    private int id_job;
    private int id_jobseeker;
    private String status;

    public Lamaran(int id_lamaran, int id_job, int id_jobseeker, String status){
        this.id_lamaran = id_lamaran;
        this.id_job = id_job;
        this.id_jobseeker = id_jobseeker;
        this.status = status;
    }

    public Lamaran(){

    }

    public int getILamaran() {
        return id_lamaran;
    }

    public void setIdLamaran(int id_lamaran) {
        this.id_lamaran = id_lamaran;
    }

    public int getIdJob() {
        return id_job;
    }

    public void setIdJob(int id_job) {
        this.id_job = id_job;
    }

    public int getIdJobseeker() {
        return id_jobseeker;
    }

    public void setIdJobseeker(int id_jobseeker) {
        this.id_jobseeker = id_jobseeker;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
