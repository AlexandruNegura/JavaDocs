package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.util.Date;

/**
 * Created by Alexandru.Negura on 7/12/2017.
 */
@Table(name = "employee")
public class Employee {
    @Id(name = "employee_id")
    private Long employeeID;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "hire_date")
    private Date hireDate;
    @Column(name = "job_id")
    private Job jobId;
    @Column(name = "salary")
    private Float salary;
    @Column(name = "commission_pct")
    private Float commissionPct;
    @Column(name = "manager_id")
    private Employee managerId;
    @Column(name = "department_id")
    private Department departmentId;


}
