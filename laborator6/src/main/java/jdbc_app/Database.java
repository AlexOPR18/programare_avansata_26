package jdbc_app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
	private static Database instance;
	private HikariDataSource dataSource;
	
	private Database() {
		HikariConfig config = new HikariConfig();
	
		config.setJdbcUrl("jdbc:mysql://localhost:3306/laboratorul6");
		config.setUsername("root");
		config.setPassword("");
		
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(2);
		
		dataSource = new HikariDataSource(config);
	}
	
	public static synchronized Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public void closePool() {
		if (dataSource!=null) {
			dataSource.close();
		}
	}
}
