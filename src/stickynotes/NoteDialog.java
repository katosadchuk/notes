package stickynotes;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;

public class NoteDialog extends JDialog {
	Note _note;
	public NoteDialog(Note note) {
		this.setSize(363, 250);
		_note = note;
		JTextArea text2 = new JTextArea(" ", 30, 30);
		Border border2 = BorderFactory.createLineBorder(new Color(203, 255, 211));
	

		this.add(text2);
		text2.setSize(30, 30);
		text2.setBackground(new Color(203, 255, 211));
		text2.setBorder(border2);
		text2.setFont(new Font("Rockwell", Font.PLAIN, 14));   
		text2.setLayout(new FlowLayout());
		text2.setLineWrap(true);
	}
	
	
	/*void onActive(){
		_note.setCurrentNoteDialog(this);
	}
	*/
}