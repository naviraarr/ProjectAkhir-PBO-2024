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
        System.out.println("Nama Lengkap  : " + js.getNama());
        System.out.println("Email         : " + js.getEmail());
        System.out.println("No. Telepon   : " + js.getNotelp());
        System.out.println("Tanggal Lahir : " + js.getTgllahir());
        System.out.println("Jenis Kelamin : " + js.getJenisKelamin());
        System.out.println("Alamat        : " + js.getAlamat());
        System.out.println("Kota          : " + js.getKota());
        System.out.println("Keahlian      : " + js.getKeahlian());
        System.out.println("-----------------------------------");
        System.out.println("Username      : " + js.getUsername());
        System.out.println("Password      : " + js.getPassword());
        System.out.println("===================================");
        System.out.println("1. UPDATE PROFILE");
        System.out.println("0. KEMBALI");
        System.out.println("===================================");
        System.out.println("Input Menu : ");
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
        System.out.print("Nama Lengkap  : ");
        String nama = Main.read.readLine();
        System.out.print("Email         : ");
        String email = Main.read.readLine();
        System.out.print("No. Telepon   : ");
        String no_tlp = Main.read.readLine();
        System.out.print("Tanggal Lahir : ");
        String tgl_lahir = Main.read.readLine();
        System.out.print("Jenis Kelamin : ");
        String jenis_kelamin = Main.read.readLine();
        System.out.print("Alamat        : ");
        String alamat = Main.read.readLine();
        System.out.print("Kota          : ");
        String kota = Main.read.readLine();
        System.out.print("Keahlian      : ");
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
        System.out.println("===================================");
        System.out.println("                JOB                ");
        System.out.println("===================================");

        for (Job job : Main.jobcontr.getAllJob(id_jobseeker)){
            System.out.print("ID Job     : " + job.getIdJob());
            System.out.print("Name       : " + job.getJobName());
            System.out.print("Decription : " + job.getDescription());
            System.out.print("Salary     : " + job.getSalary());
            System.out.print("Category   : " + job.getCategory());
            System.out.println("-----------------------------------");
        }

        System.out.println("===================================");
        System.out.println("1. APPLY JOB");
        System.out.println("0. BACK");
        System.out.println("===================================");

        System.out.println("Input Menu : ");
        String input = Main.read.readLine();
        switch (input) {
            case "1":
                applyJob(id_jobseeker);
                Main.menuJobseeker(id_jobseeker);
                break;
            case "0":
                Main.menuJobseeker(id_jobseeker);
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }

    }

    public void applyJob(int id_jobseeker) {
        System.out.println("===================================");
        System.out.println("Input ID Job    : ");
        Main.
        System.out.println("===================================");
    }
}
