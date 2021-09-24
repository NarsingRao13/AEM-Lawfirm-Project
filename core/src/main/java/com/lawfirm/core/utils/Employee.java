package com.lawfirm.core.utils;

public class Employee {
	public int EmpID;
	public String Name;
	public double Salary;
	public String EmpDept;

	public Employee(int empID, String name, double salary, String empDept) {
		super();
		EmpID = empID;
		Name = name;
		Salary = salary;
		EmpDept = empDept;
	}

	public int getEmpID() {
		return EmpID;
	}

	public void setEmpID(int empID) {
		EmpID = empID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getSalary() {
		return Salary;
	}

	public void setSalary(double salary) {
		Salary = salary;
	}

	public String getEmpDept() {
		return EmpDept;
	}

	public void setEmpDept(String empDept) {
		EmpDept = empDept;
	}

}
