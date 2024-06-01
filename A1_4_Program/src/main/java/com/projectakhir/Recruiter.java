package com.projectakhir;

import java.io.IOException;
import java.sql.SQLException;

public class Recruiter extends Akun {
    private int id_recruiter;
    private String nama;
    private String email;
    private String no_tlp;
    private String alamat;
    private String instansi;
    private String status;

    public Recruiter(int id, String username, String password, String role, int id_recruiter, String nama, String email,
            String no_tlp, String alamat, String instansi, String status) {
        super(id, username, password, role);
        this.id_recruiter = id_recruiter;
        this.nama = nama;
        this.email = email;
        this.no_tlp = no_tlp;
        this.alamat = alamat;
        this.instansi = instansi;
        this.status = status;
    }

    public Recruiter() {

    }

    public int getIdRecruiter() {
        return id_recruiter;
    }

    public void setIdRecruiter(int id_recruiter) {
        this.id_recruiter = id_recruiter;
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

    public String getNoTlp() {
        return no_tlp;
    }

    public void setNoTlp(String no_tlp) {
        this.no_tlp = no_tlp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void showProfileRecuiter(int id_akun) throws IOException, SQLException {
        Main.cls();
        Recruiter rc = Main.recruitercontr.getRecruiterById(id_akun);
        System.out.println("===================================");
        System.out.println("          PROFILE RECRUITER         ");
        System.out.println("===================================");
        System.out.println("Name          : " + rc.getNama());
        System.out.println("Email         : " + rc.getEmail());
        System.out.println("Phone         : " + rc.getNoTlp());
        System.out.println("Address       : " + rc.getAlamat());
        System.out.println("Instance      : " + rc.getInstansi());
        System.out.println("-----------------------------------");
        System.out.println("Username      : " + rc.getUsername());
        System.out.println("Password      : " + rc.getPassword());
        System.out.println("===================================");
        System.out.println("1. UPDATE PROFILE");
        System.out.println("0. BACK");
        System.out.println("===================================");
        System.out.print("Input Menu : ");
        String input = Main.read.readLine();
        switch (input) {
            case "1":
                updateProfileRecruiter(rc.getId(), rc.getIdRecruiter());
                Main.menuRecruiter(rc.getId());
            case "0":
                Main.menuRecruiter(rc.getId());
                break;
            default:
                System.out.println("Input Invalid!");
                break;
        }
    }

    public void updateProfileRecruiter(int id_akun, int id_recruiter) throws IOException, SQLException {
        Main.cls();
        System.out.println("===================================");
        System.out.println("      UPDATE PROFILE JOBSEEKER");
        System.out.println("===================================");
        System.out.print("Nama          : ");
        String nama = Main.read.readLine();
        System.out.print("Email         : ");
        String email = Main.read.readLine();
        System.out.print("No. Telepon   : ");
        String no_tlp = Main.read.readLine();
        System.out.print("Alamat        : ");
        String alamat = Main.read.readLine();
        System.out.print("Instansi      : ");
        String instansi = Main.read.readLine();
        System.out.println("===================================");

        Akun akunJobSeeker = Main.akuncontr.getAkunById(id_akun);

        Recruiter updateRecruiter = new Recruiter(akunJobSeeker.getId(), akunJobSeeker.getUsername(),
                akunJobSeeker.getPassword(), akunJobSeeker.getRole(), id_recruiter, nama, email, no_tlp, alamat, instansi, "Pending");

        try {
            Main.recruitercontr.updateRecruiter(updateRecruiter, id_recruiter);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addJob(int id_recruiter) throws IOException, SQLException {
        Main.cls();
        
        System.out.println("===================================");
        System.out.println("           ADD NEW JOB             ");
        System.out.println("===================================");
        
        System.out.print("Job Name      : ");
        String job_name = Main.read.readLine();
        System.out.print("Description   : ");
        String desc_job = Main.read.readLine();
        System.out.print("Salary        : ");
        String salary = Main.read.readLine();
        System.out.print("Category      : ");
        String category = Main.read.readLine();

        Job newJob = new Job(0, id_recruiter, job_name, desc_job, salary, category);

        try{
            Main.jobcontr.addJob(newJob);;
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void showJob(int id_recruiter) throws IOException, SQLException {
        Main.cls();
        System.out.println("===================================");
        System.out.println("              SHOW JOB             ");
        System.out.println("===================================");
        
        for (Job job : Main.jobcontr.getAllJob(id_recruiter)){
            System.out.println("ID Job     : " + job.getIdJob());
            System.out.println("Name       : " + job.getJobName());
            System.out.println("Decription : " + job.getDescription());
            System.out.println("Salary     : " + job.getSalary());
            System.out.println("Category   : " + job.getCategory());
            System.out.println("-----------------------------------");
        }

        System.out.println("===================================");
        System.out.println("0. BACK");
        System.out.println("===================================");
        System.out.print("Input Menu : ");
        String input = Main.read.readLine();
        switch (input) {
            case "0":
                break;
            default:
                System.out.println("Input Invalid!");
                break;
        }
    }

    public void showApplicant(int id_recruiter) throws IOException, SQLException {
        Main.cls();
        System.out.println("===================================");
        System.out.println("          SHOW APPLICANT          ");
        System.out.println("===================================");
       
        for (Job job : Main.jobcontr.getAllJob(id_recruiter)){
            for (Lamaran lamaran : Main.lamarancontr.getAllLamaran()){
                if (lamaran.getIdJob() == job.getIdJob() ){
                    for (JobSeeker js : Main.jobseekcontr.getAllJobSeeker()){
                        if (lamaran.getIdJobseeker() == js.getIdJobseeker()){
                            System.out.println("ID Application    : " + lamaran.getIdJob());
                            System.out.println("Job Name          : " + job.getJobName() );
                            System.out.println("Name Applicant    : " + js.getNama());
                            System.out.println("-----------------------------------");
                        }
                    }
                }
            }
        }
        System.out.println("===================================");
        System.out.println("1. VERIFY APPLICATION");
        System.out.println("0. BACK");
        System.out.println("===================================");
        System.out.print("Input Menu : ");
        String input = Main.read.readLine();
        switch (input) {
            case "1":
                verifyApplication(id_recruiter);
                break;
            case "0":
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }

    }

    public static void verifyApplication(int id_recruiter) throws IOException, SQLException {
        System.out.println("===================================");
        System.out.println("          VERIFY APPLICANT         ");
        System.out.println("===================================");

        for (Lamaran lamaran : Main.lamarancontr.getAllLamaran()){
            for (Job job : Main.jobcontr.getAllJob(id_recruiter)){
                if (lamaran.getIdJob() == job.getIdJob() ){
                    for (JobSeeker js : Main.jobseekcontr.getAllJobSeeker()){
                        if (lamaran.getIdJobseeker() == js.getIdJobseeker()){
                            System.out.print("ID Application    : " + lamaran.getIdJob());
                            System.out.print("Job Name          : " + job.getJobName() );
                            System.out.print("Name Applicant    : " + js.getNama());
                            System.out.println("-----------------------------------");
                        }
                    }
                }
            }
        }
        System.out.println("===================================");
        System.out.print("Input ID Application : ");
        int id_application = Integer.parseInt(Main.read.readLine());
        for (Lamaran lamaran : Main.lamarancontr.getAllLamaran()){
            if (id_application == lamaran.getIdLamaran()){
                System.out.print("Input Y to Accept | Input N to Reject : ");
                String input = Main.read.readLine();
                if (input.equals("Y")) {
                    Lamaran updateLamaran = new Lamaran(id_application, lamaran.getIdJob(), lamaran.getIdJobseeker(), "Accept");
                    Main.lamarancontr.updateLamaran(updateLamaran, id_application);
                } else if (input.equals("N")) {
                    Lamaran updateLamaran = new Lamaran(id_application, lamaran.getIdJob(), lamaran.getIdJobseeker(), "Reject");
                    Main.lamarancontr.updateLamaran(updateLamaran, id_application);
                }
            }
        }
        
    }
}
