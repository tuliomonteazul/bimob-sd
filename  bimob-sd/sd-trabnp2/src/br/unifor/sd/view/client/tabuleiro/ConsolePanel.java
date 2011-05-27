package br.unifor.sd.view.client.tabuleiro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.unifor.sd.service.client.ClientOutputService;

public class ConsolePanel extends JPanel {
	
	private JTextArea tfConsole;
	private JTextField tfInput;
	private JScrollPane scrollPane;
	
	private ClientOutputService clientOutputService = ClientOutputService.getInstance();
	
	public ConsolePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setBackground(Color.LIGHT_GRAY);
		
		tfConsole = new JTextArea();
		tfConsole.setFont(new Font("Lucida Console", Font.PLAIN, 10));
		scrollPane = new JScrollPane(tfConsole);
		tfConsole.setLineWrap(true);
		tfConsole.setWrapStyleWord(true);
		tfConsole.setBackground(new Color(90, 90, 90));
		tfConsole.setForeground(new Color(240, 240, 240));
		tfConsole.setRows(5);
		tfConsole.setSize(400, 50);
		tfConsole.setEditable(false);
		add(scrollPane);
		
		tfInput = new JTextField();
		tfInput.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		tfInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					clientOutputService.writeConsole(tfInput.getText());
					tfInput.setText("");
				}
			}
		});
		add(tfInput);
	}
	
	public void addText(String text) {
		String oldText = tfConsole.getText();
		if (oldText == null || oldText.equals("") || oldText.length() == 0) 
			tfConsole.setText(text);
		else 
			tfConsole.setText(oldText + "\n" + text);
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
			}
		});
	}
	
	
}
