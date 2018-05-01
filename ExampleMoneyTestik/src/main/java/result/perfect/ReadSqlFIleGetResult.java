package result.perfect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ReadSqlFIleGetResult {

	public static void main(String[] args) {
//		  String selectOnePovar = "queries/mysql/selectOnePovar.sql";
//		  String selectPovarName = "queries/mysql/selectPovarName.sql";

//		DJMapper d = new DJMapper();
//		d.getResult(selectPovarName);

	}

	public List<HashMap<String, Object>> getResult(String selectPovarName) {
		List<HashMap<String, Object>> convertResultListMap = null;
	
		Connection con;
		try {
			Properties props = new Properties();
			props.load(new FileReader("demo.properties"));
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			String dburl = props.getProperty("dburl");
			String baseName = props.getProperty("baseName");
			// ====================================================
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dburl + baseName, user, password);
			BufferedReader reader = new BufferedReader(new FileReader(selectPovarName));
			StringBuilder message = new StringBuilder();
			String stroka = "";
			while ((stroka = reader.readLine()) != null) {
				message.append(stroka);
				if (stroka.endsWith(";")) {
					message.replace(message.length() - 1, message.length(), "");
				}
			}

//			System.out.println(selectPovarName + " ----> " + message);

			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet resultSet = stmt.executeQuery(message.toString());

			if (!resultSet.isBeforeFirst()) {
				System.out.println("No data");
			} else {
				convertResultListMap = convertResultSetToList(resultSet);
			}

			reader.close();
			con.close();
			final long startTime = System.currentTimeMillis();

			for (HashMap<String, Object> hashMap : convertResultListMap) {
				// System.out.println(hashMap.entrySet() + " ");
			}

			for (Map<String, Object> map : convertResultListMap) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					// System.out.println(key + " : " + value);
				}
			}

			System.out.println("==========================");
//			final long endTime = System.currentTimeMillis();

//			System.out.println("Total execution time: " + (endTime - startTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertResultListMap;
	}

	// -----------------------

	public static List<HashMap<String, Object>> convertResultSetToList(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		while (rs.next()) {
			HashMap<String, Object> row = new HashMap<String, Object>(columns);
			for (int i = 1; i <= columns; ++i) {
				row.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(row);
		}
//		System.out.println("-------------OK------------");
		return list;
	}

	// --------------------
}
