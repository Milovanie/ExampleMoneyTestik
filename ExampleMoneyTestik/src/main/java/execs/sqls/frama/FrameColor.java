package execs.sqls.frama;

import java.awt.Color;
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

public class FrameColor {
	static String selectOnePovar = "queries/mysql/selectOnePovar.sql";
	static String selectPovarName = "queries/mysql/selectPovarName.sql";

	public static void main(String[] args) {

		displayColorWindow(selectPovarName);

	}

	private static void displayColorWindow(  String selectPovarName) {
		JFrame frame = new JFrame("My Frame");
		JPanel panel = new JPanel();
		JButton colorButton = new JButton("change color");
		JButton resultButton = new JButton("result");

		JTextArea area = new JTextArea("Hello, World\n", 20, 20);
		JScrollPane scrollPane = new JScrollPane(area); 

		frame.setSize(300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ActionListener mySqlListener = (event) -> {
			ReadSqlFIleGetResult dm = new ReadSqlFIleGetResult();
			List<HashMap<String, Object>> mapResult = dm.getResult(selectPovarName);

			for (Map<String, Object> map : mapResult) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					// System.out.println(key + " : " + value);
					area.append(key + " : " + value + "\n");
				}
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
