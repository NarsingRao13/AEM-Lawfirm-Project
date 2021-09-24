package com.lawfirm.core.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.commons.datasource.poolservice.DataSourcePool;

@Component(service = DatabaseService.class, immediate = true)
public class DatabaseService {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(DatabaseService.class);
	@Reference
	DataSourcePool source;
	DataSource dataSource = null;
	Connection connection = null;
	
	public Connection getConnection(String datasourceName) {
		
		try {
			LOG.info("Inside Connection, source is {}", source);
			dataSource = (DataSource) source.getDataSource(datasourceName);
			connection = dataSource.getConnection();
			return connection;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}
	
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}
	}
}
