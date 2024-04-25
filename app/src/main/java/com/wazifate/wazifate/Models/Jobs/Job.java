package com.wazifate.wazifate.Models.Jobs;

import com.google.gson.annotations.SerializedName;

public class Job {
    String id;
    String jobtitle;
    String jobtype;
    String location;

    @SerializedName("min")
    String minSalary;
    @SerializedName("max")
    String maxSalary;
    @SerializedName("salary")
    String salaryCurrency;

    public Job(String id, String jobtitle, String jobtype, String location, String minSalary, String maxSalary, String salaryCurrency) {
        this.id = id;
        this.jobtitle = jobtitle;
        this.jobtype = jobtype;
        this.location = location;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.salaryCurrency = salaryCurrency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }
}
