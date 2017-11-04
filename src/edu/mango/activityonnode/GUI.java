import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;

public class GUI {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUI window = new GUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(600, 450);
		shell.setText("GUI Mock Up");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont(".SF NS Text", 16, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(206, 6, 165, 32);
		lblNewLabel.setText("Project Name");
		
		Label lblActi = new Label(shell, SWT.NONE);
		lblActi.setFont(SWTResourceManager.getFont(".SF NS Text", 13, SWT.NORMAL));
		lblActi.setText("Activity");
		lblActi.setBounds(21, 44, 56, 25);
		
		Label lblDuration = new Label(shell, SWT.NONE);
		lblDuration.setText("Duration");
		lblDuration.setFont(SWTResourceManager.getFont(".SF NS Text", 13, SWT.NORMAL));
		lblDuration.setBounds(83, 44, 61, 25);
		
		Label lblSlackTime = new Label(shell, SWT.NONE);
		lblSlackTime.setText("Slack Time");
		lblSlackTime.setFont(SWTResourceManager.getFont(".SF NS Text", 13, SWT.NORMAL));
		lblSlackTime.setBounds(151, 44, 71, 25);
		
		Label lblEarilerStartTime = new Label(shell, SWT.NONE);
		lblEarilerStartTime.setText("Eariler Start Time");
		lblEarilerStartTime.setFont(SWTResourceManager.getFont(".SF NS Text", 13, SWT.NORMAL));
		lblEarilerStartTime.setBounds(237, 44, 117, 25);
		
		Label lblLatestEndTime = new Label(shell, SWT.NONE);
		lblLatestEndTime.setText("Latest End Time");
		lblLatestEndTime.setFont(SWTResourceManager.getFont(".SF NS Text", 13, SWT.NORMAL));
		lblLatestEndTime.setBounds(360, 44, 109, 25);
		
		Label lblDependences = new Label(shell, SWT.NONE);
		lblDependences.setText("Dependencies");
		lblDependences.setFont(SWTResourceManager.getFont(".SF NS Text", 13, SWT.NORMAL));
		lblDependences.setBounds(475, 44, 112, 25);
		
		List ProjectList = new List(shell, SWT.BORDER);
		ProjectList.setBounds(10, 75, 577, 251);
		
		Button btnAdd = new Button(shell, SWT.NONE);
		btnAdd.setBounds(20, 332, 94, 28);
		btnAdd.setText("Add Task");
		
		Button btnModifyTask = new Button(shell, SWT.NONE);
		btnModifyTask.setText("Modify Task");
		btnModifyTask.setBounds(135, 332, 94, 28);
		
		Button btnDeleteTask = new Button(shell, SWT.NONE);
		btnDeleteTask.setText("Delete Task");
		btnDeleteTask.setBounds(251, 332, 94, 28);
		
		Button btnMoreInfo = new Button(shell, SWT.NONE);
		btnMoreInfo.setText("More Info");
		btnMoreInfo.setBounds(375, 332, 94, 28);
		
		Button btnExit = new Button(shell, SWT.NONE);
		btnExit.setText("Exit");
		btnExit.setBounds(493, 332, 94, 28);

	}
}
