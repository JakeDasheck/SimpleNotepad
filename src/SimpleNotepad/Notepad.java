package SimpleNotepad;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Notepad extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JMenuBar mbar;
	JMenu file, edit, options, font, adresses, background, foreground;
	JMenuItem open, save, saveas, separator, exit;
	JMenuItem home, school, work;
	JMenuItem fsize1, fsize2, fsize3, fsize4, fsize5;
	JMenuItem fsize6, fsize7, fsize8, fsize9;

	JPanel mainpanel;
	JScrollPane scroll;
	JEditorPane text;

	Font f;

	private final static Map<String, ColorOptions> AVAILABLE_COLORS = new HashMap<>();
	static {
		AVAILABLE_COLORS.put("Blue", new ColorOptions(Color.blue));
		AVAILABLE_COLORS.put("Yellow", new ColorOptions(Color.yellow));
		AVAILABLE_COLORS.put("Orange", new ColorOptions(Color.orange));
		AVAILABLE_COLORS.put("Red", new ColorOptions(Color.red));
		AVAILABLE_COLORS.put("White", new ColorOptions(Color.white));
		AVAILABLE_COLORS.put("Black", new ColorOptions(Color.black));
		AVAILABLE_COLORS.put("Green", new ColorOptions(Color.green));
	}

	String command = " ";
	String str = " ";

	String str1 = " ", str2 = " ", str3 = " ";
	String str4 = " ";

	String str6 = " ";
	String str7 = " ", str8 = " ", str9 = " ";

	String str10 = " ", str11 = " ", str12 = " ", str13 = " ";

	int len, len1, len2;

	int i = 0;

	public Notepad(String str) {

		super(str);

		// Panel
		mainpanel = new JPanel();
		mainpanel = (JPanel) getContentPane();
		mainpanel.setLayout(new BorderLayout());

		// Menu bar
		mbar = new JMenuBar();
		setJMenuBar(mbar);

		// Menu
		file = new JMenu("File");
		edit = new JMenu("Edit");
		options = new JMenu("Options");
		font = new JMenu("Size");
		adresses = new JMenu("Adresy");
		foreground = new JMenu("Foreground");
		background = new JMenu("Background");

		// File menu and shortcuts
		file.add(open = new JMenuItem("Open"));
		file.add(save = new JMenuItem("Save"));
		file.add(saveas = new JMenuItem("Save As..."));
		file.add(separator = new JMenuItem("------------"));
		file.add(exit = new JMenuItem("Exit"));
		mbar.add(file);

		// Edit menu and shortcuts
		edit.add(adresses);
		adresses.add(work = new JMenuItem("Praca"));
		adresses.add(school = new JMenuItem("Szkoła"));
		adresses.add(home = new JMenuItem("Dom"));

		mbar.add(edit);

		// shortcuts - mnemonics
		open.setMnemonic(KeyEvent.VK_O);
		save.setMnemonic(KeyEvent.VK_S);
		saveas.setMnemonic(KeyEvent.VK_F5);
		exit.setMnemonic(KeyEvent.VK_F1);
		home.setMnemonic(KeyEvent.VK_H);
		school.setMnemonic(KeyEvent.VK_L);
		work.setMnemonic(KeyEvent.VK_W);

		// Shortcuts accelerator
		KeyStroke keyStrokeOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
		open.setAccelerator(keyStrokeOpen);
		KeyStroke keyStrokeSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
		save.setAccelerator(keyStrokeSave);
		KeyStroke keyStrokeSaveAs = KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.CTRL_DOWN_MASK);
		saveas.setAccelerator(keyStrokeSaveAs);
		KeyStroke keyStrokeExit = KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_DOWN_MASK);
		exit.setAccelerator(keyStrokeExit);
		KeyStroke keyStrokeHome = KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK);
		home.setAccelerator(keyStrokeHome);
		KeyStroke keyStrokeSchool = KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK);
		school.setAccelerator(keyStrokeSchool);
		KeyStroke keyStrokeWork = KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK);
		work.setAccelerator(keyStrokeWork);

		// Options menu
		options.add(font);
		font.add(fsize1 = new JMenuItem("8"));
		font.add(fsize2 = new JMenuItem("10"));
		font.add(fsize3 = new JMenuItem("12"));
		font.add(fsize4 = new JMenuItem("14"));
		font.add(fsize5 = new JMenuItem("16"));
		font.add(fsize6 = new JMenuItem("18"));
		font.add(fsize7 = new JMenuItem("20"));
		font.add(fsize8 = new JMenuItem("22"));
		font.add(fsize9 = new JMenuItem("24"));

		// Submenu foreground
		options.add(foreground);
		options.add(background);

		// init foreground and background
		for (Map.Entry<String, ColorOptions> entry : AVAILABLE_COLORS.entrySet()) {

			JMenuItem colorFore = new JMenuItem(entry.getKey());
			colorFore.addActionListener(this);
			colorFore.addActionListener(e -> text.setForeground(entry.getValue().getColor()));
			colorFore.setIcon(entry.getValue().getIcon());

			JMenuItem colorBack = new JMenuItem(entry.getKey(), entry.getValue().getIcon());
			colorBack.addActionListener(this);
			colorBack.addActionListener(e -> text.setBackground(entry.getValue().getColor()));

			foreground.add(colorFore);
			background.add(colorBack);
		}

		mbar.add(options);

		// Actions
		open.addActionListener(this);
		save.addActionListener(this);
		saveas.addActionListener(this);
		exit.addActionListener(this);
		home.addActionListener(this);
		school.addActionListener(this);
		work.addActionListener(this);

		fsize1.addActionListener(this);
		fsize2.addActionListener(this);
		fsize3.addActionListener(this);
		fsize4.addActionListener(this);
		fsize5.addActionListener(this);
		fsize6.addActionListener(this);
		fsize7.addActionListener(this);
		fsize8.addActionListener(this);
		fsize9.addActionListener(this);

		// Text panel
		text = new JEditorPane();
		scroll = new JScrollPane(text);
		mainpanel.add(scroll, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Default font
		f = new Font("Arial", Font.PLAIN, 15);
		text.setFont(f);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		// commands
		command = (String) ae.getActionCommand();

		try {

			if (command.equals("Open")) {

				str4 = "";
				FileDialog dialog = new FileDialog(this, "Open");
				dialog.setVisible(true);

				str1 = dialog.getDirectory();
				str2 = dialog.getFile();
				str3 = str1 + str2;

				File f = new File(str3);
				FileInputStream fobj = new FileInputStream(f);
				len = (int) f.length();
				for (int j = 0; j < len; j++) {
					char str5 = (char) fobj.read();
					str4 = str4 + str5;
				}
				fobj.close();

				text.setText(str4);
				this.setTitle(str2);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// save as
		try {

			if (command.equals("Save As...")) {
				FileDialog dialog1 = new FileDialog(this, "Save As", FileDialog.SAVE);
				dialog1.setVisible(true);

				str7 = dialog1.getDirectory();
				str8 = dialog1.getFile();
				str9 = str7 + str8;

				str6 = text.getText();
				len1 = str6.length();
				byte buf[] = str6.getBytes();

				File f1 = new File(str9);
				FileOutputStream fobj1 = new FileOutputStream(f1);
				for (int k = 0; k < len1; k++) {
					fobj1.write(buf[k]);
				}
				fobj1.close();
				this.setTitle(str8);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// Save from "Open"
		try {

			if (command.equals("Save")) {

				str10 = text.getText();
				len2 = str10.length();
				byte buff[] = str10.getBytes();

				File f2 = new File(str3);
				FileOutputStream fboj2 = new FileOutputStream(f2);
				for (int i = 0; i < len2; i++) {
					fboj2.write(buff[i]);
				}
				fboj2.close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// Save from "Save as.."
		try {

			if (command.equals("Save")) {

				str10 = text.getText();
				len2 = str10.length();
				byte buff[] = str10.getBytes();

				File f2 = new File(str9);
				FileOutputStream fboj2 = new FileOutputStream(f2);
				for (int i = 0; i < len2; i++) {
					fboj2.write(buff[i]);
				}
				fboj2.close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (command.equals("Exit")) {
			System.exit(0);
		}

		// Adresy menu
		Document doc = text.getDocument();
		int cursor = text.getCaretPosition();

		try {

			if (command.equals("Dom")) {
				String home = "adres domowy";
				doc.insertString(cursor, home, null);
			}

			if (command.equals("Szkoła")) {
				String school = "adres szkoły";
				doc.insertString(cursor, school, null);
			}

			if (command.equals("Praca")) {
				String work = "adres pracy";
				doc.insertString(cursor, work, null);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// set font size
		if (command.equals("8")) {
			String fontName = f.getName();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 8);
			text.setFont(f);
		}

		if (command.equals("12")) {
			String fontName = f.getName();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 12);
			text.setFont(f);
		}

		if (command.equals("14")) {
			String fontName = f.getName();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 14);
			text.setFont(f);
		}
		if (command.equals("18")) {
			String fontName = f.getName();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 18);
			text.setFont(f);
		}
		if (command.equals("20")) {
			String fontName = f.getName();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 20);
			text.setFont(f);

		}

		if (command.equals("22")) {
			String fontName = f.getName();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 22);
			text.setFont(f);

		}

		if (command.equals("24")) {
			String fontName = f.getName();
			int fontStyle = f.getStyle();

			f = new Font(fontName, fontStyle, 24);
			text.setFont(f);

		}
	}

}