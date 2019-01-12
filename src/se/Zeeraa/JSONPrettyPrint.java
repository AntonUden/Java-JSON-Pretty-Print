package se.Zeeraa;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.json.JSONObject;

public class JSONPrettyPrint {
	private JFrame f = new JFrame();

	private JPanel p = new JPanel();
	private JPanel iop = new JPanel();

	private JTextArea input = new JTextArea("{}");
	private JTextArea output = new JTextArea();

	private JScrollPane inputScrollPane = new JScrollPane(input);
	private JScrollPane outputScrollPane = new JScrollPane(output);

	private int indent = 4;

	public JSONPrettyPrint() {
		f.add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(new Dimension(800, 600));
		f.setLocationRelativeTo(null);

		iop.setLayout(new GridLayout());
		iop.add(inputScrollPane);
		iop.add(outputScrollPane);

		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(iop);

		input.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				update();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				update();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				update();
			}
		});

		output.setEditable(false);

		f.setVisible(true);
		update();
	}

	private boolean update() {
		try {
			JSONObject json = new JSONObject(input.getText());

			output.setText(json.toString(indent));
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		new JSONPrettyPrint();
	}
}