package com.projectakhir;

import java.io.IOException;
import java.sql.SQLException;

public class JobSeeker extends Akun {
    private int id_jobseeker;
    private String nama;
    private String email;
    private String no_tlp;
    private String tgl_lahir;
    private String jenis_kelamin;
    private String alamat;
    private String kota;
    private String keahlian;

    public JobSeeker(int id, String username, String password, String role, int id_jobseeker, String nama, String email,
            String no_telp, String tgl_lahir, String jenis_kelamin, String alamat, String kota, String keahlian) {
        super(id, username, password, role);
        this.id_jobseeker = id_jobseeker;
        this.nama = nama;
        this.email = email;
        this.no_tlp = no_telp;
        this.tgl_lahir = tgl_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.kota = kota;
        this.keahlian = keahlian;
    }

    public JobSeeker() {

    }

    public int getIdJobseeker() {
        return id_jobseeker;
    }

    public void setId(int id_jobseeker) {
        this.id_jobseeker = id_jobseeker;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotelp() {
        return no_tlp;
    }

    public void setNotlp(String no_tlp) {
        this.no_tlp = no_tlp;
    }

    public String getTgllahir() {
        return tgl_lahir;
    }

    public void setTgllahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getJenisKelamin() {
        return jenis_kelamin;
    }

    public void setJenisKelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }

    public void profile(int id_akun) throws IOException, SQLException {
        Main.cls();
        JobSeeker js = Main.jobseekcontr.getJobSeekerById(id_akun);
        System.out.println("===================================");
        System.out.println("         PROFILE JOBSEEKER");
        System.out.println("===================================");
        System.out.println("Full Name     : " + js.getNama());
        System.out.println("Email         : " + js.getEmail());
        System.out.println("Phone         : " + js.getNotelp());
        System.out.println("Date of birth : " + js.getTgllahir());
        System.out.println("Gender        : " + js.getJenisKelamin());
        System.out.println("Address       : " + js.getAlamat());
        System.out.println("City          : " + js.getKota());
        System.out.println("Skills        : " + js.getKeahlian());
        System.out.println("-----------------------------------");
        System.out.println("Username      : " + js.getUsername());
        System.out.println("Password      : " + js.getPassword());
        System.out.println("===================================");
        System.out.println("1. UPDATE PROFILE");
        System.out.println("0. BACK");
        System.out.println("===================================");
        System.out.print("Input Menu : ");
        String input = Main.read.readLine();
        switch (input) {
            case "1":
                updateProfile(js.getId(), js.getIdJobseeker());
                Main.menuJobseeker(js.getId());
                break;
            case "0":
                Main.menuJobseeker(js.getId());
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }

    public void updateProfile(int id_akun, int id_jobseeker) throws IOException, SQLException {
        Main.cls();
        System.out.println("===================================");
        System.out.println("      UPDATE PROFILE JOBSEEKER");
        System.out.println("===================================");
        System.out.print("Full Name     : ");
        String nama = Main.read.readLine();
        System.out.print("Email         : ");
        String email = Main.read.readLine();
        System.out.print("Phone         : ");
        String no_tlp = Main.read.readLine();
        System.out.print("Date of birth : ");
        String tgl_lahir = Main.read.readLine();
        System.out.print("Gender        : ");
        String jenis_kelamin = Main.read.readLine();
        System.out.print("Address       : ");
        String alamat = Main.read.readLine();
        System.out.print("City          : ");
        String kota = Main.read.readLine();
        System.out.print("Skills      : ");
        String keahlian = Main.read.readLine();
        System.out.println("===================================");

        Akun akunJobSeeker = Main.akuncontr.getAkunById(id_akun);

        JobSeeker updateJobSeeker = new JobSeeker(akunJobSeeker.getId(), akunJobSeeker.getUsername(),
                akunJobSeeker.getPassword(), akunJobSeeker.getRole(), id_jobseeker, nama, email, no_tlp, tgl_lahir,
                jenis_kelamin, alamat, kota, keahlian);

        try {
            Main.jobseekcontr.updateJobSeeker(updateJobSeeker, id_jobseeker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showJob(int id_jobseeker) throws IOException, SQLException {
        Main.cls();;
        System.out.println("===================================");
        System.out.println("                JOB                ");
        System.out.println("===================================");

        for (Job job : Main.jobcontr.getAllJob()){
            System.out.println("ID Job     : " + job.getIdJob());
            System.out.println("Name       : " + job.getJobName());
            System.out.println("Decription : " + job.getDescription());
            System.out.println("Salary     : " + job.getSalary());
            System.out.println("Category   : " + job.getCategory());
            System.out.println("-----------------------------------");
        }

        System.out.println("===================================");
        System.out.println("1. APPLY JOB");
        System.out.println("0. BACK");
        System.out.println("===================================");

        System.out.print("Input Menu : ");
        String input = Main.read.readLine();
        switch (input) {
            case "1":
                applyJob(id_jobseeker);
                break;
            case "0":
                break;
            default:
                System.out.println("Option Invalid!");
                break;
        }

    }

    public void applyJob(int id_jobseeker) throws IOException, SQLException {
        Main.cls();
        System.out.println("===================================");
        System.out.println("                JOB                ");
        System.out.println("===================================");

        for (Job job : Main.jobcontr.getAllJob()){
            System.out.println("ID Job     : " + job.getIdJob());
            System.out.println("Name       : " + job.getJobName());
            System.out.println("Decription : " + job.getDescription());
            System.out.println("Salary     : " + job.getSalary());
            System.out.println("Category   : " + job.getCategory());
            System.out.println("-----------------------------------");
        }

        System.out.println("===================================");
        System.out.print("Input ID Job    : ");
        int found = 0;
        int id_job = Integer.parseInt(Main.read.readLine());
        for (Job job : Main.jobcontr.getAllJob()){
            if (job.getIdJob() == id_job){
                found = 1;
                Lamaran newLamaran = new Lamaran(0, id_job, id_jobseeker, "Waiting");

                Main.lamarancontr.addLamaran(newLamaran);
            }
        }
        if (found != 1){
            System.out.println("ID Job is invalid!");
        }
        
        System.out.println("===================================");

    }

    public void showLamaran(int id_jobseeker) throws IOException, SQLException {
        Main.cls();
        System.out.println("===================================");
        System.out.println("             APPLIED JOB           ");
        System.out.println("===================================");
        
        for (Lamaran lamaran : Main.lamarancontr.getAllLamaran()){
            for (Job job : Main.jobcontr.getAllJob()) {
                if (lamaran.getIdJob() ==  job.getIdJob()) {
                    System.out.println("ID Applied Job    : " + lamaran.getIdJob());
                    System.out.println("Job Name          : " + job.getJobName() );
                    System.out.println("Status            : " + lamaran.getStatus());
                    System.out.println("-----------------------------------");
                }
            }   
        }
        System.out.println("0. BACK");
        System.out.println("===================================");

        System.out.print("Input Menu : ");
        String input = Main.read.readLine();
        switch (input) {
            case "0":
                break;
            default:
                System.out.println("Option Invalid!");
                break;
        }
    }
}
