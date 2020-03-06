package execs.sqls.frama;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import result.perfect.ReadSqlFIleGetResult;
import result.perfect.ReadSqlFIleGetResult2;

/**
 * https://stackoverflow.com/questions/27016675/setting-the-font-of-a-jtextarea
 */

public class FrameColor {
	static String selectOnePovar = "queries/mysql/selectOnePovar.sql";
	static String selectPovarName = "queries/mysql/selectPovarName.sql";
	static String selectAllPovars = "queries/mysql/selectAllPovars.sql";
	private static String[] fontOptions = { "Serif", "Agency FB", "Arial", "Calibri", "Cambrian", "Century Gothic",
			"Comic Sans MS", "Courier New", "Forte", "Garamond", "Monospaced", "Segoe UI", "Times New Roman",
			"Trebuchet MS", "Serif" };
	private static String[] sizeOptions = { "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28" };

	public static void main(String[] args) {

		displayColorWindow(selectAllPovars);

	}

	private static void displayColorWindow(String selectPovarName) {
		JFrame frame = new JFrame("My Frame");
		JPanel panel = new JPanel();
		JButton colorButton = new JButton("change color");
		JButton resultButton = new JButton("result");

		JTextArea area = new JTextArea(null, 20, 18);
		area.setLineWrap(true);

		Font font = new Font("Serif", Font.BOLD, 20);
		area.setFont(font);
		JScrollPane scrollPane = new JScrollPane(area);

		frame.setSize(500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ActionListener mySqlListener = (event) -> {

			ReadSqlFIleGetResult2 dm = new ReadSqlFIleGetResult2();
			List<HashMap<String, Object>> mapResult = dm.getResult(selectPovarName);

			for (Map<String, Object> map : mapResult) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					 System.out.println(key + " : " + value);
					area.append(key + " : " + value + "\n");
				}
				System.out.println();
				area.append("\r\n");
			}
			
			
			if (!area.getText().equals("")) {
				resultButton.setEnabled(false);
			} else {
				resultButton.setEnabled(true);
			}
		};

	

		resultButton.addActionListener(mySqlListener);
		// --------------------------
		panel.setBackground(Color.cyan);

		ActionListener myListener = (event) -> {
			Random rnd = new Random();
			Color rndColor = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			panel.setBackground(rndColor);
		};

		colorButton.addActionListener(myListener);
		panel.add(colorButton);
		panel.add(resultButton);
		panel.add(scrollPane);
		frame.getContentPane().add(panel);

		// frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
