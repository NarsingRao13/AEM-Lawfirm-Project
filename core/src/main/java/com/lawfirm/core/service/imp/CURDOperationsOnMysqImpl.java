package com.lawfirm.core.service.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawfirm.core.service.CURDOperationOnMysql;
import com.lawfirm.core.service.DatabaseService;
import com.lawfirm.core.utils.Employee;

/**
 * 
 * @author Narsing Rao Karukola
 * This class is the implementation of CURDOperationOnMysql interface which is used to perform CRUD operations on Mysql Database
 */
@Component(service = CURDOperationOnMysql.class)
public class CURDOperationsOnMysqImpl implements CURDOperationOnMysql {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(CURDOperationsOnMysqImpl.class);
	@Reference
	private DatabaseService databaseService;
	private Connection connection;
	@Override
	public boolean insertData(Employee employee) {
		connection = databaseService.getConnection("test");
		if(connection == null){
			LOG.info("connection is null");
			return false;
		}
		try {
			PreparedStatement ps = connection.prepareStatement("insert into employee values(?,?,?,?)");
			ps.setInt(1, employee.EmpID);
			ps.setString(2, employee.Name);
			ps.setDouble(3, employee.Salary);
			ps.setString(4, employee.getEmpDept());
			int statusCode = ps.executeUpdate();
			LOG.info("insert status code = "+statusCode);
			databaseService.closeConnection();
			if(statusCode>0) {
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			return false;
		}
	}
	@Override
	public boolean updateData(Employee employee) {
		connection = databaseService.getConnection("test");
		if(connection == null){
			LOG.info("connection is null");
			return false;
		}
		try {
			PreparedStatement ps = connection.prepareStatement("update employee set Name=?,Salary=?,EmpDept=? where EmpID=?");
			ps.setString(1, employee.getName());
			ps.setDouble(2, employee.getSalary());
			ps.setString(3, employee.getEmpDept());
			ps.setInt(4, employee.getEmpID());
			int statusCode = ps.executeUpdate();
			LOG.info("update status code = "+statusCode);
			databaseService.closeConnection();
			if(statusCode>0) {
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			return false;
		}
	}
	@Override
	public boolean deleteData(int EmpID) {
		connection = databaseService.getConnection("test");
		if(connection == null){
			LOG.info("connection is null");
			return false;
		}
		try {
			PreparedStatement ps = connection.prepareStatement("delete from employee where EmpID=?");
			ps.setInt(1, EmpID);
			int statusCode = ps.executeUpdate();
			LOG.info("delete status code = "+statusCode);
			databaseService.closeConnection();
			if(statusCode>0) {
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			return false;
		}
	}
	@Override
	public String getData(String tableName) {
		connection = databaseService.getConnection("test");
		String data = "";
		if(connection != null){
			Connection connection = databaseService.getConnection("test");
			try {
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select * from "+tableName);
				
				while (rs.next()){
					data += rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4)+"\n";	
				}
				connection.close();
				LOG.info(data);
				return data;
			} catch (SQLException e) {
				LOG.error(e.getMessage());
				return "Error in retriving data";
			}
		} else {
			LOG.info("connection is null");
			return "Failed to get data";
		}
		
	}

}
