package com.projectakhir;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Recruiter extends Akun {
    private int id_recruiter;
    private String nama;
    private String email;
    private String no_tlp;
    private String alamat;
    private String instansi;

    public Recruiter(int id, String username, String password, String role, int id_recruiter, String nama, String email,
            String no_tlp, String alamat, String instansi) {
        super(id, username, password, role);
        this.id_recruiter = id_recruiter;
        this.nama = nama;
        this.email = email;
        this.no_tlp = no_tlp;
        this.alamat = alamat;
        this.instansi = instansi;
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

    public void showProfileRecuiter(int id_recruiter) throws IOException, SQLException {
        Main.cls();
        Recruiter rc = Main.recruitercontr.getRecruiterById(id_recruiter);
        System.out.println("===================================");
        System.out.println("          PROFILE RECRUITER         ");
        System.out.println("===================================");
        System.out.println("Nama Lengkap  : " + rc.getNama());
        System.out.println("Email         : " + rc.getEmail());
        System.out.println("No. Telepon   : " + rc.getNoTlp());
        System.out.println("Alamat        : " + rc.getAlamat());
        System.out.println("Instansi      : " + rc.getInstansi());
        System.out.println("-----------------------------------");
        System.out.println("Username      : " + rc.getUsername());
        System.out.println("Password      : " + rc.getPassword());
        System.out.println("===================================");
        System.out.println("1. UPDATE PROFILE");
        System.out.println("0. KEMBALI");
        System.out.println("===================================");
        System.out.println("Input Menu : ");
        System.out.println("Input Menu : ");
        String input = Main.read.readLine();
        switch (input) {
            case "1":
                updateProfileRecruiter(rc.getId(), rc.getIdRecruiter());
                Main.menuRecruiter(rc.getId());
            case "0":
                Main.menuRecruiter(rc.getId());
                break;
            default:
                System.out.println("Pilihan tidak valid.");
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
                akunJobSeeker.getPassword(), akunJobSeeker.getRole(), id_recruiter, nama, email, no_tlp, alamat, instansi);

        try {
            Main.recruitercontr.updateRecruiter(updateRecruiter, id_recruiter);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addJob(int id_recruiter) throws IOException, SQLException {
        Main.cls();
        
        String job_name, desc_job, category, salary;

        System.out.println("===================================");
        System.out.println("           ADD NEW JOB             ");
        System.out.println("===================================");
        
        System.out.print("Nama      : ");
        job_name = Main.read.readLine();
        System.out.print("Deskripsi : ");
        desc_job = Main.read.readLine();
        System.out.print("Gaji      : ");
        salary = Main.read.readLine();
        System.out.print("Kategori  : ");
        category = Main.read.readLine();

        Job newJob = new Job(0, id_recruiter, job_name, desc_job, salary, category);

        try{
            Main.jobcontr.addJob(newJob);;
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // public void showJob(int id__recruiter) throws IOException, SQLException {
    //     Main.cls();
    //     System.out.println("===================================");
    //     System.out.println("              SHOW JOB             ");
    //     System.out.println("===================================");
        
    //     for (Job job : Main.jobcontr.getAllJob(id__recruiter)){
    //         System.out.print("ID Job     : " + job.getIdJob());
    //         System.out.print("Name       : " + job.getJobName());
    //         System.out.print("Decription : " + job.getDescription());
    //         System.out.print("Salary     : " + job.getSalary());
    //         System.out.print("Category   : " + job.getCategory());
    //         System.out.println("-----------------------------------");
    //     }

    //     System.out.println("===================================");
    //     System.out.println("1. UPDATE JOB");
    //     System.out.println("2. DELETE JOB");
    //     System.out.println("0. KEMBALI");
    //     System.out.println("===================================");
    //     System.out.println("Input Menu : ");
    //     String input = Main.read.readLine();
    //     switch (input) {
    //         case "1":
    //             updateProfile(js.getId(), js.getIdJobseeker());
    //             Main.menuJobseeker(js.getId());
    //             break;
    //         case "0":
    //             Main.menuJobseeker(js.getId());
    //             break;
    //         default:
    //             System.out.println("Pilihan tidak valid.");
    //             break;
    //     }
    // }

    public static void showLamaran() {
        
    }


    

    // public static void lihatLowongan() {
    //     String query = "SELECT * FROM Job";
    //     try (Connection connection = koneksi.getConnection();
    //             PreparedStatement statement = connection.prepareStatement(query);
    //             ResultSet rs = statement.executeQuery()) {
    //         System.out.println("Daftar Job");
    //         // int i = 1;
    //         while (rs.next()) {
    //             String id = rs.getString("id_job");
    //             String job_name = rs.getString("job_name");
    //             String desc_job = rs.getString("desc_job");
    //             String salary = rs.getString("salary");
    //             String category = rs.getString("category");

    //             System.out.println("ID: " + id);
    //             System.out.println("Posisi: " + job_name);
    //             System.out.println("Deskripsi Pekerjaan:" + desc_job);
    //             System.out.println("Gaji: " + salary);
    //             System.out.println("Kategori Pekerjaan: " + category);
    //             // i++;
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         System.out.println("Gagal mengambil data lamaran: " + e.getMessage());
    //     }
    // }

    // public static String verifikasiLamaran(int id) {
    //     String query = "UPDATE Lamaran SET verifikasi = ? WHERE id_lamaran = ?";
    //     try (Connection conn = koneksi.getConnection();
    //             PreparedStatement pstmt = conn.prepareStatement(query)) {
    //         pstmt.setString(1, "Selesai");
    //         pstmt.setInt(2, id);
    //         int rowsUpdated = pstmt.executeUpdate();
    //         if (rowsUpdated > 0) {
    //             return "Lamaran sudah diverifikasi.";
    //         } else {
    //             return "Verifikasi lamaran gagal.";
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return "Terjadi kesalahan pada verifikasi lamaran.";
    //     }
    // }

    // public static void editLowongan() {
    //     try {
    //         conn = koneksi.getConnection();
    //         int id_job;
    //         String job_name, desc_job, category;
    //         Double salary;

    //         System.out.print("ID Pekerjaan yang akan diedit: ");
    //         id_job = Integer.parseInt(br.readLine());
    //         System.out.print("Posisi: ");
    //         job_name = br.readLine();
    //         System.out.print("Deskripsi: ");
    //         desc_job = br.readLine();
    //         System.out.print("Gaji: ");
    //         salary = Double.parseDouble(br.readLine());
    //         System.out.print("Kategori: ");
    //         category = br.readLine();

    //         String query = "UPDATE job SET job_name = ?, desc_job = ?, salary = ?, category = ? WHERE id_job = ?";
    //         try (PreparedStatement pst = conn.prepareStatement(query)) {
    //             pst.setString(1, job_name);
    //             pst.setString(2, desc_job);
    //             pst.setDouble(3, salary);
    //             pst.setString(4, category);
    //             pst.setInt(5, id_job);
    //             pst.executeUpdate();
    //             System.out.println("Lowongan berhasil diperbarui!");
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //             System.out.println("Gagal memperbarui lowongan: " + e.getMessage());
    //         }
    //     } catch (SQLException | IOException e) {
    //         e.printStackTrace();
    //         System.out.println("Gagal membuat koneksi: " + e.getMessage());
    //     }
    // }

    // public static void hapusLowongan() {
    //     try {
    //         conn = koneksi.getConnection();
    //         int id_job;

    //         System.out.print("ID Pekerjaan yang akan dihapus: ");
    //         id_job = Integer.parseInt(br.readLine());

    //         String query = "DELETE FROM job WHERE id_job = ?";
    //         try (PreparedStatement pst = conn.prepareStatement(query)) {
    //             pst.setInt(1, id_job);
    //             pst.executeUpdate();
    //             System.out.println("Lowongan berhasil dihapus!");
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //             System.out.println("Gagal menghapus lowongan: " + e.getMessage());
    //         }
    //     } catch (SQLException | IOException e) {
    //         e.printStackTrace();
    //         System.out.println("Gagal membuat koneksi: " + e.getMessage());
    //     }
    // }

}
