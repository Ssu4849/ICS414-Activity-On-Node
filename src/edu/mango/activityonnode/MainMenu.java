package edu.mango.activityonnode;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;

public class MainMenu {

	private JFrame frame;

	private static Project currentProject;

	/**
	 * Launch the main menu
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu menu = new MainMenu();
					menu.frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Main Menu");
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton createBtn = new JButton("Create new project");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel dialog = new JPanel(new GridLayout(0, 1));
				dialog.add(new JLabel("Enter a name for your project"));
				JTextField field1 = new JTextField();
				dialog.add(field1);

				int result = JOptionPane.showConfirmDialog(null, dialog, "Create Project", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					if (field1.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You must enter a name for the project.");
					} else {
						frame.setVisible(false);
						currentProject = new Project(field1.getText());
						ProjectWindow window = new ProjectWindow(currentProject);
						window.setVisible(true);
					}
				}
			}
		});
		createBtn.setBounds(125, 50, 184, 38);
		panel.add(createBtn);

		JButton loadBtn = new JButton("Load project from file");
		loadBtn.addActionListener(new loadProjectButtonListener());
		loadBtn.setBounds(125, 99, 184, 38);
		panel.add(loadBtn);
	}

	private class loadProjectButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			FileFilter fileFilter = new FileFilter() {
				@Override
				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					}

					String fileName = f.getName().toLowerCase();
					if (fileName.endsWith(".project")) {
						return true;
					}
					return false;
				}

				@Override
				public String getDescription() {
					return "Project File (*.project)";
				}
			};

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(fileFilter);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				currentProject = parseFile(selectedFile);
				ProjectWindow window = new ProjectWindow(currentProject);
				window.setVisible(true);
			
			}
			frame.setVisible(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		/**
		 * Reads and loads a project from file
		 * 
		 * @param file The project file to read
		 * @return an instance of the project
		 */
		private Project parseFile(File file) {
			Project project = null;
			try {
				project = serializeDataIn(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return project;
		}

		// https://stackoverflow.com/questions/10654236/java-save-object-data-to-a-file
		public Project serializeDataIn(File f) throws IOException {
			FileInputStream fin = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fin);
			Project project = null;
			try {
				project = (Project) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ois.close();
			return project;
		}
	}
}
