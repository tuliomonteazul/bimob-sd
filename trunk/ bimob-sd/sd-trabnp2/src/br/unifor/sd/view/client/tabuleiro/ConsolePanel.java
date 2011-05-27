package br.unifor.sd.view.client.tabuleiro;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class ConsolePanel extends JPanel {
	
	private JTextArea tfConsole;
	private JTextField tfInput;
	
	public ConsolePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setBackground(Color.LIGHT_GRAY);
		
		tfConsole = new JTextArea();
		final JScrollPane scrollPane = new JScrollPane(tfConsole);
		tfConsole.setLineWrap(true);
		tfConsole.setWrapStyleWord(true);
		scrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		tfConsole.setBackground(Color.LIGHT_GRAY);
		tfConsole.setRows(4);
		tfConsole.setSize(400, 50);
		tfConsole.setEditable(false);
		add(scrollPane);
		
		tfInput = new JTextField();
		add(tfInput);
	}
	
	public void addText(String text) {
		String oldText = tfConsole.getText();
		tfConsole.setText(oldText == null? "" : oldText + text);
	}
	
	
}
