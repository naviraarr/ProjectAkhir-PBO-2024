package com.projectakhir;

public class Job {
    private int id_job;
    private int id_recruiter;
    private String job_name;
    private String description;
    private String salary;
    private String category;

    public Job(int id_job, int id_recruiter, String job_name, String description, String salary, String category) {
        this.id_job = id_job;
        this.id_recruiter = id_recruiter;
        this.job_name = job_name;
        this.description = description;
        this.salary = salary;
        this.category = category;
    }

    public Job(){

    }

    public int getIdJob(){
        return id_job;
    }

    public void setIdJob(int id_job) {
        this.id_job = id_job;
    }

    public int getIdRecruiter(){
        return id_recruiter;
    }

    public void setIdRecruiter(int id_recruiter) {
        this.id_recruiter = id_recruiter;
    }
    
    public String getJobName() {
        return job_name;
    }

    public void setJobName(String job_name) {
        this.job_name = job_name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}

