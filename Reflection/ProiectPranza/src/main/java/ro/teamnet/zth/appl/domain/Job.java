package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Alexandru.Negura on 7/12/2017.
 */
@Table(name = "departments")
public class Job {
    @Id(name = "JOB_ID")
    Long jobId;
    @Column(name = "JOB_TITLE")
    String JobTitle;
    @Column(name = "MAX_SALARY")
    Long maxSalary;
    @Column(name = "MIN_SALARY")
    Long minSalary;

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", JobTitle='" + JobTitle + '\'' +
                ", maxSalary=" + maxSalary +
                ", minSalary=" + minSalary +
                '}';
    }

    public Long getJobId() {
        return jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (!jobId.equals(job.jobId)) return false;
        if (!JobTitle.equals(job.JobTitle)) return false;
        if (!maxSalary.equals(job.maxSalary)) return false;
        return minSalary.equals(job.minSalary);
    }

    @Override
    public int hashCode() {
        int result = jobId.hashCode();
        result = 31 * result + JobTitle.hashCode();
        result = 31 * result + maxSalary.hashCode();
        result = 31 * result + minSalary.hashCode();
        return result;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }
}
