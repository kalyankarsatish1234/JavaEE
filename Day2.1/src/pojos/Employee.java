package pojos;

import java.sql.Date;

//empid | name | salary | deptid |join_date
public class Employee {
	private int empid;
	private String name;
	private String address;
	private double salary;
	private String deptId;
	private Date joinDate;

	// def ctor: will not be used in JDBC BUT will be mandatory in Hibernate
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	// empid,name,salary,join_date
	public Employee(int empid, String name, double salary, Date joinDate) {
		super();
		this.empid = empid;
		this.name = name;
		this.salary = salary;
		this.joinDate = joinDate;
	}

	public Employee(String name, String address, double salary, String deptId, Date joinDate) {
		super();
		this.name = name;
		this.address = address;
		this.salary = salary;
		this.deptId = deptId;
		this.joinDate = joinDate;
	}

	// getters n setter
	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", address=" + address + ", salary=" + salary
				+ ", deptId=" + deptId + ", joinDate=" + joinDate + "]";
	}

}
