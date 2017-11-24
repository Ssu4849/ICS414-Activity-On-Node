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

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ProjectWindow {

	private JFrame frame;
	private JTable activityTable;
	private Project project;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project project = new Project("Test Project");
					ProjectWindow window = new ProjectWindow(project);
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
	public ProjectWindow(Project project) {
		this.project = project;
		initialize();
	}

	/**
	 * Refreshes contents of the activity table in this Project window
	 */
	private void refreshTable() {
		// reset JTable
		((DefaultTableModel) activityTable.getModel()).setRowCount(0);

		// recalculate times
		project.getCriticalPath();

		for (ActivityNode node : project.getActivities()) {
			Set<ActivityNode> nodeSet = project.getActivities();
			StringBuilder predecessors = new StringBuilder("");
			for (ActivityNode an : nodeSet) {
				if (an.getChildren().contains(node)) {
					predecessors.append(an.getName() + " ");
				}
			}
			((DefaultTableModel) activityTable.getModel()).addRow(new Object[] { node.getId(), node.getName(),
					node.getDuration(), node.getEarliestStartTime(), node.getEarliestFinishTime(),
					node.getLatestStartTime(), node.getLatestFinishTime(), node.getSlackTime(), predecessors });
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 400);
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

		String columnNames[] = { "ID", "Activity", "Duration", "EST", "EFT", "LST", "LFT", "Slack Time",
				"Preceding Activity IDs" };
		DefaultTableModel tableModel = new DefaultTableModel(null, columnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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

		activityTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		activityTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		activityTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		activityTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		activityTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		activityTable.getColumnModel().getColumn(4).setPreferredWidth(60);
		activityTable.getColumnModel().getColumn(5).setPreferredWidth(60);
		activityTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		activityTable.getColumnModel().getColumn(7).setPreferredWidth(80);
		activityTable.getColumnModel().getColumn(8).setPreferredWidth(145);

		JLabel lblNewLabel = new JLabel(project.getName());
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
				dialog.add(new JLabel("Preceding Activity IDs (separated by ','):"));
				dialog.add(field3);

				int result = JOptionPane.showConfirmDialog(null, dialog, "Add a new activity",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					System.out.println(field1.getText() + " " + field2.getText() + " " + field3.getText() + " ");

					if (field1.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You must enter a name for this activity.");
					} else if (field2.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "You must enter a duration for this activity.");
					} else {
						String name = field1.getText().trim();
						try {
							int duration = Integer.parseInt(field2.getText().trim());
							String[] IDStrings = field3.getText().trim().split(",");

							ActivityNode newActivity = new ActivityNode(name, duration);
							if (IDStrings.length > 0 && !IDStrings[0].trim().equals("")) {
								List<Integer> IDList = new ArrayList<Integer>();
								for (String ID : IDStrings) {
									try {
										IDList.add(Integer.parseInt(ID));
									} catch (NumberFormatException ex) {
										JOptionPane.showMessageDialog(null,
												"Preceding activity " + "'" + ID + "' is not a valid ID.");
									}
								}
								for (ActivityNode an : project.getActivities()) {
									if (IDList.contains(an.getId())) {
										an.getChildren().add(newActivity);
									}
								}
							}
							project.addActivity(newActivity);
							refreshTable();
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Duration must be a number.");
						}
					}
				}

			}
		});
		optionsPanel.add(addBtn);

		JButton modifyBtn = new JButton("Modify Selected Activity");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int row = activityTable.getSelectedRow();

				if (row != -1) {
					String IDString = activityTable.getModel().getValueAt(row, 0).toString();
					int selectedID = Integer.parseInt(IDString);

					ActivityNode selectedActivity = null;
					for (ActivityNode an : project.getActivities()) {
						if (an.getId() == selectedID) {
							selectedActivity = an;
						}
					}

					JTextField field1 = new JTextField();
					field1.setText(selectedActivity.getName());
					JTextField field2 = new JTextField();
					field2.setText(Integer.toString(selectedActivity.getDuration()));
					JTextField field3 = new JTextField();
					StringBuilder predecessors = new StringBuilder("");
					Set<Integer> IDs = new HashSet<Integer>();
					for (ActivityNode an : project.getActivities()) {
						if (an.getChildren().contains(selectedActivity)) {
							IDs.add(an.getId());
						}
					}
					for (int id : IDs) {
						predecessors.append(id + ",");
					}

					field3.setText(predecessors.toString());
					JPanel dialog = new JPanel(new GridLayout(0, 1));
					dialog.add(new JLabel("Name:"));
					dialog.add(field1);
					dialog.add(new JLabel("Duration:"));
					dialog.add(field2);
					dialog.add(new JLabel("Preceding activities:"));
					dialog.add(field3);

					int result = JOptionPane.showConfirmDialog(null, dialog, "Modify an activity",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						System.out.println(field1.getText() + " " + field2.getText() + " " + field3.getText());

						if (field1.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "You must enter a name for this activity.");
						} else if (field2.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "You must enter a duration for this activity.");
						} else {
							String name = field1.getText().trim();
							try {
								int duration = Integer.parseInt(field2.getText().trim());
								String[] IDStrings = field3.getText().trim().split(",");

								List<Integer> IDList = new ArrayList<Integer>();
								if (IDStrings.length > 0 && !IDStrings[0].trim().equals("")) {
									for (String ID : IDStrings) {
										try {
											IDList.add(Integer.parseInt(ID));
										} catch (NumberFormatException ex) {
											JOptionPane.showMessageDialog(null,
													"Preceding activity " + "'" + ID + "' is not a valid ID.");
										}
									}
								}
								for (ActivityNode an : project.getActivities()) {
									an.getChildren().remove(selectedActivity);
								}
								for (ActivityNode an : project.getActivities()) {
									if (IDList.contains(an.getId())) {
										an.getChildren().add(selectedActivity);
									}
								}
								selectedActivity.setDuration(duration);
								selectedActivity.setName(name);
								refreshTable();
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(null, "Duration must be a number.");
							}
						}
					}
				}

			}
		});
		optionsPanel.add(modifyBtn);

		JButton deleteBtn = new JButton("Delete Selected Activity");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = activityTable.getSelectedRow();

				if (row != -1) {
					String IDString = activityTable.getModel().getValueAt(row, 0).toString();
					int ID = Integer.parseInt(IDString);

					Set<ActivityNode> activities = new HashSet<>(project.getActivities());
					for (Iterator<ActivityNode> iterator = activities.iterator(); iterator.hasNext();) {
						ActivityNode activity = iterator.next();
						if (activity.getId() == ID) {
							project.deleteActivity(activity);
							System.out.println("Deleted activity: " + activity.getId());
						}
					}
				}
				refreshTable();
			}
		});
		optionsPanel.add(deleteBtn);

		JButton infoBtn = new JButton("More Info");
		infoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel dialog = new JPanel(new GridLayout(0, 1));
				List<ActivityNode> critPathNodes = project.getCriticalPath();
				StringBuilder criticalPath = new StringBuilder("");
				for (ActivityNode an : critPathNodes) {
					criticalPath.append(an.getName() + " ");
				}
				List<ActivityNode> sortedNodes = project.getTopologicalSort();
				StringBuilder sortedPath = new StringBuilder("");
				for (ActivityNode an : sortedNodes) {
					sortedPath.append(an.getName() + " ");
				}
				dialog.add(new JLabel("Critical Path: " + criticalPath));
				dialog.add(new JLabel("Topological sort: " + sortedPath));

				JOptionPane.showConfirmDialog(null, dialog, "Additional Info", JOptionPane.PLAIN_MESSAGE);
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
