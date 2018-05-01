package execs.sqls;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

import com.ibatis.common.jdbc.ScriptRunner;

public class ReadSqlFile {
	static String selectOnePovar = "queries/mysql/selectOnePovar.sql";

	public static void main(String[] argv) {
		Connection con;

		try {

			Properties props = new Properties();
			props.load(new FileReader("demo.properties"));
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			String dburl = props.getProperty("dburl");
			String baseName = props.getProperty("baseName");
//			---------------------

			Class.forName("com.mysql.jdbc.Driver");
			BufferedReader reader = new BufferedReader(new FileReader(selectOnePovar));
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + baseName, userName, password);
			
			con = DriverManager.getConnection(dburl + baseName, user, password);

			ScriptRunner runner = new ScriptRunner(con, false, false);
			runner.runScript(reader);

		} catch (Exception se) {
			System.out.println("Couldn't connect: exit.");
			se.printStackTrace();
			System.exit(1);
		}

	}
}