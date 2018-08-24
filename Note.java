package stickynotes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

public class Note extends JFrame {
	public int wide;
	public int high;
	public static Color c = new Color(203, 255, 211);
	public JTextArea text = new JTextArea("", 30, 28);

	public Note(int w, int h) {
		this.wide = w;
		this.high = h;
		this.setLayout(new FlowLayout());
		this.setResizable(false);
		this.setPreferredSize(new Dimension(w, h));
		this.setMinimumSize(new Dimension(w, h));
		this.setMaximumSize(new Dimension(w, h));

		MenuBar menuBar = new MenuBar();
		Menu m1 = new Menu("File");
		Menu m2 = new Menu("Note Color");
		Menu m3 = new Menu("Edit");
		Menu m4 = new Menu("Save Note");

		MenuItem itemAbout = new MenuItem("About");
		itemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuAboutSelected();
			}

		});
		MenuItem itemExit = new MenuItem("Exit");
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuExitSelected();
			}
		});
		MenuItem newNote = new MenuItem("New Note");
		newNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuNewNotePressed();
			}
		});
		MenuItem floatNote = new MenuItem("Floating Window");
		floatNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFloatNotePressed();
			}
		});

		MenuItem pink = new MenuItem("Pink");
		pink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNotePink();
			}
		});
		MenuItem purple = new MenuItem("Purple");
		purple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNotePurple();
			}
		});
		MenuItem yellow = new MenuItem("Yellow");
		yellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNoteYellow();
			}
		});
		MenuItem blue = new MenuItem("Blue");
		blue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNoteBlue();
			}
		});
		MenuItem saveNoteTxt = new MenuItem("txt");
		saveNoteTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveNoteTxt(text);
			}
		});
		MenuItem saveNoteDocx = new MenuItem("docx");
		saveNoteDocx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveNoteDocx(text);
			}
		});
		MenuItem openNote = new MenuItem("Open Note");
		openNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openNote(text);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		m3.add(m2);
		m2.add(pink);
		m2.add(purple);
		m2.add(yellow);
		m2.add(blue);
		m3.add(floatNote);
		m4.add(saveNoteTxt);
		m4.add(saveNoteDocx);
		m1.add(newNote);
		m1.add(m4);
		m1.add(openNote);
		m1.add(itemAbout);
		m1.add(itemExit);

		menuBar.add(m1);
		menuBar.add(m3);
		this.setMenuBar(menuBar);
		this.setVisible(true);

	}

	private void setNotePink() {
		text.setBackground(new Color(255, 208, 230));
		Border border = BorderFactory.createLineBorder(new Color(255, 208, 230));
		text.setBorder(border);
	}

	private void setNotePurple() {
		text.setBackground(new Color(210, 208, 255));
		Border border = BorderFactory.createLineBorder(new Color(210, 208, 255));
		text.setBorder(border);
	}

	private void setNoteYellow() {
		text.setBackground(new Color(255, 249, 202));
		Border border = BorderFactory.createLineBorder(new Color(255, 249, 202));
		text.setBorder(border);
	}

	private void setNoteBlue() {
		text.setBackground(new Color(185, 220, 255));
		Border border = BorderFactory.createLineBorder(new Color(185, 220, 255));
		text.setBorder(border);
	}

	private void menuFloatNotePressed() {
		if (isAlwaysOnTop())
			setAlwaysOnTop(false);
		else
			setAlwaysOnTop(true);
	}

	private void menuNewNotePressed() {
		Note n2 = new Note(335, 250);
		n2.enterWords();

	}

	private void menuAboutSelected() {
		JOptionPane.showMessageDialog(this,
				"Note App v1.0\nBy Kateryna Osadchuk", "About",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void menuExitSelected() {
		int rsp = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit",
				JOptionPane.YES_NO_OPTION);
		if (rsp == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	private void saveNoteTxt(JTextArea text) {
		String content = text.getText();
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("/home/me/Documents"));
		int retval = chooser.showSaveDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			try {
				FileWriter fw = new FileWriter(chooser.getSelectedFile()
						+ ".txt");
				fw.write(content.toString());
				fw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private void saveNoteDocx(JTextArea text) {
		String content = text.getText();
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("/home/me/Documents"));
		int retval = chooser.showSaveDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			try {
				FileWriter fw = new FileWriter(chooser.getSelectedFile()
						+ ".docx");
				fw.write(content.toString());
				fw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private void openNote(JTextArea text) throws FileNotFoundException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("/home/me/Documents"));
		int retval = chooser.showOpenDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			try {
				FileReader file = new FileReader(chooser.getSelectedFile());
				BufferedReader reader = new BufferedReader(file);
				String line = null;
				StringBuffer sb = new StringBuffer();
				String ls = System.getProperty("line.separator");

				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append(ls);
				}

				reader.close();
				Note n3 = new Note(335, 250);
				n3.enterWords();
				n3.text.setText(sb.toString());
				repaint();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void enterWords() {
		Border border = BorderFactory.createLineBorder(c);
		add(text);
		text.setSize(30, 28);
		text.setBackground(c);
		text.setBorder(border);
		text.setFont(new Font("Rockwell", Font.PLAIN, 14));
		text.setLayout(new FlowLayout());
		text.setLineWrap(true);
		setVisible(true);
	}

	public static void main(String args[]) {
		Note n1 = new Note(335, 250);
		n1.enterWords();
	}
}
