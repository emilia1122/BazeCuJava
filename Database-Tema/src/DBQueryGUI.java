import java.awt.*;
import java.awt.event.*;

public class DBQueryGUI extends Frame  implements WindowListener, ActionListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TextField sqlExec;
	Button executeButton;
	Label label1;
	static TextArea edit1;
	Label label2;
	Button quitButton;
	Button resetButton;
	SimpleSelect ob;

	public void init() {

		this.addWindowListener(this);
		LayoutManager layoutman = new BorderLayout();
		this.setLayout(layoutman);
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();
		sqlExec = new TextField(50);
		edit1 = new TextArea(12, 80);
		label1 = new Label("Enter an SQL statement:");
		label2 = new Label("Output");
		executeButton = new Button("Execute");
		quitButton = new Button("Quit");
		resetButton = new Button("Reset");
		p1.add(label1);
		p1.add(sqlExec);
		p2.add(executeButton);
		p2.add(resetButton);
		p2.add(quitButton);
		p3.add(label2);
		p3.add(edit1);
		this.add("North", p1);
		this.add("Center", p3);
		this.add("South", p2);
		executeButton.addActionListener(this);
		quitButton.addActionListener(this);
		resetButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Execute"))
			clickedExecuteButton();
		if (command.equals("Quit"))
			clickedQuitButton();
		if (command.equals("Reset"))
			sqlExec.setText("");
	}

	public void clickedExecuteButton() {
		edit1.setText("");
		edit1.repaint();
		if (ob == null) {
			ob = new SimpleSelect();
		}
		if (!ob.open()) {
			edit1.setText("Nu se poate stabili o conexiune cu baza de                             date !");
			return;
		}
		String sqlString, result;
		sqlString = sqlExec.getText();
		result = ob.queryDatabase(sqlString);
		if (result != null)
			edit1.setText(result);
		else
			edit1.setText("A aparut o eroare !");
	}

	public void clickedQuitButton() {
		if (ob != null)
			//ob.clone();
		System.exit(0);
	}

	public static void main(String argv[]) {
		DBQueryGUI sql = new DBQueryGUI();
		sql.init();
		sql.pack();
		sql.setTitle("JDBC/ODBC GUI");
		sql.setVisible(true);
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}
}