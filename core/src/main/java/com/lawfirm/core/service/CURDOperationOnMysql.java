package com.lawfirm.core.service;

import com.lawfirm.core.utils.Employee;
/**
 * 
 * @author Narsing Rao Karukola
 * This Interface is used to perform all the CRUD operations on Mysql Database
 */
public interface CURDOperationOnMysql {
	boolean insertData(Employee employee);
	boolean updateData(Employee employee);
	boolean deleteData(int EmpID);
	String getData(String tableName);
}
