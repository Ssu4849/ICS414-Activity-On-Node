package edu.mango.activityonnode;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GUI {

	private JFrame frame;
	private JTable activityTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(null);

		JPanel activityTablePanel = new JPanel();
		frame.getContentPane().add(activityTablePanel, BorderLayout.CENTER);
		activityTablePanel.setLayout(new BorderLayout(0, 0));

		JPanel optionsPanel = new JPanel();
		optionsPanel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(optionsPanel, BorderLayout.SOUTH);
		optionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		String columnNames[] = { "Activity", "Duration", "EST", "EET", "LST", "LET", "Slack Time",
				"Preceding Activities" };
		DefaultTableModel tableModel = new DefaultTableModel(null, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		activityTable = new JTable(tableModel);
		activityTable.setBackground(new Color(255, 255, 255));
		((DefaultTableCellRenderer) activityTable.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.LEFT);
		JScrollPane tableContainer = new JScrollPane(activityTable);
		activityTablePanel.add(tableContainer, BorderLayout.CENTER);

		for (int count = 1; count <= 22; count++) {
			tableModel.addRow(new Object[] { "data", "data", "data", "data", "data", "data", "data", "data" });
		}
		
		JLabel lblNewLabel = new JLabel("Project Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(21, 11, 458, 28);
		titlePanel.add(lblNewLabel);

		// borders for activity panel
		JPanel BorderLeft = new JPanel();
		BorderLeft.setBackground(new Color(255, 255, 255));
		@SuppressWarnings("unused")
		FlowLayout flowLayout_1 = (FlowLayout) BorderLeft.getLayout();
		activityTablePanel.add(BorderLeft, BorderLayout.WEST);
		JPanel BorderRight = new JPanel();
		BorderRight.setBackground(new Color(255, 255, 255));
		@SuppressWarnings("unused")
		FlowLayout flowLayout = (FlowLayout) BorderRight.getLayout();
		activityTablePanel.add(BorderRight, BorderLayout.EAST);

		// set dimensions for the three main panels
		titlePanel.setPreferredSize(new Dimension(activityTablePanel.getWidth(), 50));
		activityTablePanel.setPreferredSize(new Dimension(activityTablePanel.getWidth(), frame.getHeight() - 100));
		optionsPanel.setPreferredSize(new Dimension(optionsPanel.getWidth(), 50));

		JButton addBtn = new JButton("Add Activity");
		addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JTextField field1 = new JTextField();
				JTextField field2 = new JTextField();
				JTextField field3 = new JTextField();
				JPanel dialog = new JPanel(new GridLayout(0, 1));
				dialog.add(new JLabel("Name:"));
				dialog.add(field1);
				dialog.add(new JLabel("Duration:"));
				dialog.add(field2);
				dialog.add(new JLabel("Preceding activities:"));
				dialog.add(field3);

				int result = JOptionPane.showConfirmDialog(null, dialog, "Add a new activity",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					System.out.println(field1.getText() + " " + field2.getText() + " " + field3.getText() + " ");
				}
			}
		});
		optionsPanel.add(addBtn);

		JButton modifyBtn = new JButton("Modify Activity");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] activityChoices = { "A", "B", "C", "D", "E" };
				JComboBox<String> combo = new JComboBox<>(activityChoices);
				JTextField field1 = new JTextField();
				JTextField field2 = new JTextField();
				JTextField field3 = new JTextField();
				JPanel dialog = new JPanel(new GridLayout(0, 1));
				dialog.add(new JLabel("Activity to modify:"));
				dialog.add(combo);
				dialog.add(new JLabel("Name:"));
				dialog.add(field1);
				dialog.add(new JLabel("Duration:"));
				dialog.add(field2);
				dialog.add(new JLabel("Preceding activities:"));
				dialog.add(field3);

				int result = JOptionPane.showConfirmDialog(null, dialog, "Modify an activity",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					System.out.println(field1.getText() + " " + field2.getText() + " " + field3.getText() + " "
							+ combo.getSelectedItem());
				}
			}
		});
		optionsPanel.add(modifyBtn);

		JButton deleteBtn = new JButton("Delete Activity");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] activityChoices = { "A", "B", "C", "D", "E" };
				JComboBox<String> combo = new JComboBox<>(activityChoices);
				JPanel dialog = new JPanel(new GridLayout(0, 1));
				dialog.add(new JLabel("Select an activity to delete:"));
				dialog.add(combo);
				
				int result = JOptionPane.showConfirmDialog(null, dialog, "Delete an activity",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					System.out.println(combo.getSelectedItem());
				}
			}
		});
		optionsPanel.add(deleteBtn);

		JButton infoBtn = new JButton("More Info");
		infoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel dialog = new JPanel(new GridLayout(0, 1));
				dialog.add(new JLabel("Critical Path:"));
				dialog.add(new JLabel("Topological sort:"));
				
				JOptionPane.showConfirmDialog(null, dialog, "Additional Info",
						 JOptionPane.PLAIN_MESSAGE);
			}
		});
		optionsPanel.add(infoBtn);

		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		optionsPanel.add(exitBtn);
	}
}
