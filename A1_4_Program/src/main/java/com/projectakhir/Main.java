package com.projectakhir;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;

public class Main {
    public static DB db = new DB();
    public static Connection conn = db.con;

    public static InputStreamReader input = new InputStreamReader(System.in);
    public static BufferedReader read = new BufferedReader(input);
    public static AkunController akuncontr = new AkunController(conn);
    public static JobSeeker js = new JobSeeker();
    public static JobSeekerController jobseekcontr = new JobSeekerController(conn);
    public static Recruiter rc = new Recruiter();
    public static RecruiterController recruitercontr = new RecruiterController(conn);
    public static Job job = new Job();
    public static JobController jobcontr = new JobController(conn);
    public static Lamaran lamaran = new Lamaran();
    public static LamaranController lamarancontr = new LamaranController(conn);

    public static boolean isLogged = false;

    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void regisJobSeeker() throws IOException, SQLException {
        cls();
        System.out.println("===================================");
        System.out.println("    REGISTER JOBSEEKER");
        System.out.println("===================================");
        System.out.print("Nama Lengkap  : ");
        String nama = read.readLine();
        System.out.print("Email         : ");
        String email = read.readLine();
        System.out.print("No. Telepon   : ");
        String no_tlp = read.readLine();
        System.out.print("Tanggal Lahir : ");
        String tgl_lahir = read.readLine();
        System.out.print("Jenis Kelamin : ");
        String jenis_kelamin = read.readLine();
        System.out.print("Alamat        : ");
        String alamat = read.readLine();
        System.out.print("Kota          : ");
        String kota = read.readLine();
        System.out.print("Keahlian      : ");
        String keahlian = read.readLine();
        System.out.print("Username      : ");
        String uname = read.readLine();
        System.out.print("Password      : ");
        String pw = read.readLine();
        System.out.println("===================================");
        String role = "jobseeker";
        int id_akun = akuncontr.getLastIdAkun()+1;

        JobSeeker newJobSeeker = new JobSeeker(id_akun, uname, pw, role, 0, nama, email, no_tlp, tgl_lahir, jenis_kelamin, alamat, kota, keahlian);

        try{
            jobseekcontr.addJobSeeker(newJobSeeker);
            jobseekcontr.addAkunJobSeeker(newJobSeeker);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void regisRecruiter() throws IOException, SQLException {
        cls();
        System.out.println("===================================");
        System.out.println("    REGISTER RECRUITER");
        System.out.println("===================================");
        System.out.print("Nama          : ");
        String nama = read.readLine();
        System.out.print("Email         : ");
        String email = read.readLine();
        System.out.print("No. Telepon   : ");
        String no_tlp = read.readLine();
        System.out.print("Alamat        : ");
        String alamat = read.readLine();
        System.out.print("Instansi      : ");
        String instansi = read.readLine();
        System.out.print("Username      : ");
        String uname = read.readLine();
        System.out.print("Password      : ");
        String pw = read.readLine();
        System.out.println("===================================");
        String role = "rectuiter";
        int id_akun = akuncontr.getLastIdAkun()+1;

        Recruiter newRecruiter = new Recruiter(id_akun, uname, pw, role, 0, nama, email, no_tlp, alamat, instansi);

        try{
            recruitercontr.addRecruiter(newRecruiter);
            recruitercontr.addRecruiter(newRecruiter);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void menuRecruiter(int get_id_akun) throws IOException, SQLException {
        while (true) {
            cls();
            Recruiter rc = Main.recruitercontr.getRecruiterById(get_id_akun);
            System.out.println("===================================");
            System.out.println("          MENU RECRUIITER          ");
            System.out.println("===================================");
            System.out.println("      1. LIHAT PROFILE             ");
            System.out.println("      1. TAMBAH LOWONGAN           ");
            System.out.println("      2. LIHAT LOWONGAN            ");
            System.out.println("      3. LIHAT LAMARAN             ");
            System.out.println("      4. VERIFIKASI LAMARAN        ");
            System.out.println("      0. BACK TO MAIN MENU         ");
            System.out.println("===================================");
            System.out.print("Input Menu : ");
            String input = read.readLine();
            switch (input) {
                case "1":
                    rc.showProfileRecuiter(rc.getIdRecruiter());
                    break;
                case "2":
                    rc.addJob(rc.getIdRecruiter());
                    break;
                case "3":
                    rc.showJob(rc.getIdRecruiter());
                    break;
                case "0":
                    rc.
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;    
            }
            break;
        }
        
    }

    public static void menuJobseeker(int get_id_akun) throws IOException, SQLException{
        while (true) {
            cls();
            System.out.println("===================================");
            System.out.println("           MENU JOBSEEKER          ");
            System.out.println("===================================");
            System.out.println("         1. LIHAT PROFILE           ");
            System.out.println("         2. LIHAT PEKERJAAN         ");
            System.out.println("         3. LIHAT LAMARAN           ");
            System.out.println("         0. KELUAR                  ");
            System.out.println("===================================");
            System.out.print("Input Menu : ");
            String input = read.readLine();
            switch (input) {
                case "1":
                    js.profile(get_id_akun);
                    break;
                case "2":
                    js.showJob(get_id_akun);
                    break;
                case "3":
                    
                    break;
                case "0":

                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;    
            }
            break;
        }
    }

    public static int verify(String username, String password) throws IOException, SQLException {
        for (Akun acc : akuncontr.getAllAkun()){
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)){
                isLogged = true;
                return acc.getId();
            }
        }
        return -1;
    }

    public static void login() throws IOException, SQLException {
        cls();
        System.out.println("===================================");
        System.out.println("               LOGIN              ");
        System.out.println("===================================");
        System.out.print("Enter Username: ");
        String uname = read.readLine();
        System.out.print("Enter Password: ");
        String pw = read.readLine();
        System.out.println("===================================");
        int verify = verify(uname, pw);
        if (verify != -1){
            Akun akun = akuncontr.getAkunById(verify);
            if (akun.getRole().equals("recruiter")){
                menuRecruiter(akun.getId());
            } else if (akun.getRole().equals("jobseeker")){
                menuJobseeker(akun.getId());
            }
        } else {
            System.out.println("Invalid Akun");
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        while (true) {
            if (isLogged == false) {
                cls();
                System.out.println("===================================");
                System.out.println("        WELCOME TO JOB PORTAL      ");
                System.out.println("===================================");
                System.out.println("         1. LOGIN                  ");
                System.out.println("         2. REGISTER RECRUITER     ");
                System.out.println("         3. REGISTER JOBSEEKER     ");
                System.out.println("         0. EXIT FROM SYSTEM       ");
                System.out.println("===================================");
                System.out.print("Input Menu : ");
                String input = read.readLine();
                switch (input) {
                    case "1":
                        login();
                        break;
                    case "2":
                        regisJobSeeker();
                        break;
                    case "3":
                        regisJobSeeker();
                        break;
                    case "0":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            }
        }
    }
}