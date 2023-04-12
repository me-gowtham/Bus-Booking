package theater;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;

public class Project {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtdate;
	private JTextField txtbno;
	private JTextField txtf;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project window = new Project();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Project() {
		initialize();
		Connect();
		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtid;

	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travels", "root", "root");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public void table_load() {
		try {
			pst = con.prepareStatement("select * from table_1");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 901, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("WELCOME TO GK TRAVELS");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(251, 21, 420, 61);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Booking",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setToolTipText("");
		panel.setBounds(25, 81, 324, 366);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(32, 37, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Source");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(32, 80, 46, 14);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Destination");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(32, 126, 78, 14);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Date");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(32, 172, 78, 14);
		panel.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Bus no");
		lblNewLabel_1_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(32, 265, 78, 14);
		panel.add(lblNewLabel_1_1_1_2);

		JLabel lblNewLabel_1_1_1_3 = new JLabel("Price");
		lblNewLabel_1_1_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1_3.setBounds(32, 318, 78, 14);
		panel.add(lblNewLabel_1_1_1_3);

		txtname = new JTextField();
		txtname.setBounds(137, 35, 162, 20);
		panel.add(txtname);
		txtname.setColumns(10);

		txtdate = new JTextField();
		txtdate.setColumns(10);
		txtdate.setBounds(137, 170, 162, 20);
		panel.add(txtdate);

		txtbno = new JTextField();
		txtbno.setColumns(10);
		txtbno.setBounds(137, 263, 162, 20);
		panel.add(txtbno);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Gender");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1.setBounds(32, 215, 78, 14);
		panel.add(lblNewLabel_1_1_1_1_1);

		JRadioButton male = new JRadioButton("Male");
		JRadioButton female = new JRadioButton("Female");
		male.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (male.isSelected()) {
					female.setSelected(false);
				}
			}
		});
		male.setBounds(148, 212, 61, 23);
		panel.add(male);

		female.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (female.isSelected()) {
					male.setSelected(false);
				}
			}
		});
		female.setBounds(211, 212, 68, 23);
		panel.add(female);
		String[] districts = { "Select your source", "Palani", "Udumalpet", "Pollachi", "Coimbatore", "Tiruppur",
				"Dindukal", "Ottanchatharam", "Theni", "Madurai", "Thirunalvellai" };
		JComboBox comboBox = new JComboBox(districts);
		comboBox.setBounds(137, 77, 162, 22);
		panel.add(comboBox);
		String[] district = { "Select your Destination", "Palani", "Udumalpet", "Pollachi", "Coimbatore", "Tiruppur",
				"Dindukal", "Ottanchatharam", "Theni", "Madurai", "Thirunalvellai" };
		JComboBox comboBox_1 = new JComboBox(district);
		comboBox_1.setBounds(137, 123, 162, 22);
		panel.add(comboBox_1);

		txtf = new JTextField();
		txtf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int l = comboBox.getSelectedIndex();
				l = comboBox_1.getSelectedIndex() - l;
				l = l * 150;
				if (l < 0)
					l = l * (-1);
				txtf.setText(String.valueOf(l));
			}
		});
		txtf.setBounds(137, 316, 162, 20);
		panel.add(txtf);
		txtf.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(359, 86, 499, 184);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("BOOK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name, src, des, date, busno, g;
				name = txtname.getText();
				src = (String) comboBox.getSelectedItem();
				des = (String) comboBox_1.getSelectedItem();
				date = txtdate.getText();
				busno = txtbno.getText();
				if (male.isSelected())
					g = "M";
				else
					g = "F";
				try {
					pst = con.prepareStatement(
							"insert into table_1(Name,Source,Destination,Date,Bus_no,Gender)values(?,?,?,?,?,?)");
					pst.setString(1, name);
					pst.setString(2, src);
					pst.setString(3, des);
					pst.setString(4, date);
					pst.setString(5, busno);
					pst.setString(6, g);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
					txtname.setText("");
					comboBox.setSelectedIndex(0);
					comboBox_1.setSelectedIndex(0);
					txtdate.setText("");
					txtbno.setText("");
					male.setSelected(false);
					female.setSelected(false);
					txtname.requestFocus();
				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(369, 281, 228, 50);
		frame.getContentPane().add(btnNewButton);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnExit.setBounds(618, 281, 228, 50);
		frame.getContentPane().add(btnExit);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(359, 342, 499, 93);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnExit_1 = new JButton("CANCEL");
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				try {
					pst = con.prepareStatement("DELETE FROM travels.table_1 WHERE (id = ?)");
					pst.setString(1, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!!!!!");
					table_load();
					txtname.setText("");
					comboBox.setSelectedIndex(0);
					comboBox_1.setSelectedIndex(0);
					txtdate.setText("");
					txtbno.setText("");
					male.setSelected(false);
					female.setSelected(false);
					txtname.requestFocus();
				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnExit_1.setBounds(374, 32, 101, 35);
		panel_1.add(btnExit_1);
		btnExit_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		JLabel lblNewLabel_1_1_1_3_1 = new JLabel("Booking ID");
		lblNewLabel_1_1_1_3_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1_1_3_1.setBounds(20, 40, 78, 16);
		panel_1.add(lblNewLabel_1_1_1_3_1);

		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String id = txtid.getText();
				String name, str, ptr, date, busno, g;
				int src = 0, des = 0;
				try {
					pst = con.prepareStatement("SELECT * FROM table_1 WHERE (id = ?)");
					pst.setString(1, id);
					rs = pst.executeQuery();
					if (rs.next() == true) {
						name = rs.getString(1);
						str = rs.getString(2);
						ptr = rs.getString(3);
						for (int i = 0; i < district.length; i++) {
							if (districts[i].equals(str))
								src = i;
							else if (district[i].equals(ptr))
								des = i;
						}
						date = rs.getString(4);
						busno = rs.getString(5);
						g = rs.getString(6);
						if (g.charAt(0) == 'M')
							male.setSelected(true);
						else
							female.setSelected(true);
						txtname.setText(name);
						comboBox.setSelectedIndex(src);
						comboBox_1.setSelectedIndex(des);
						txtdate.setText(date);
						txtbno.setText(busno);
					}
				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		txtid.setColumns(10);
		txtid.setBounds(108, 35, 101, 25);
		panel_1.add(txtid);

		JButton btnExit_1_1 = new JButton("UPDATE");
		btnExit_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name, src, des, date, busno, g;
				name = txtname.getText();
				src = (String) comboBox.getSelectedItem();
				des = (String) comboBox_1.getSelectedItem();
				date = txtdate.getText();
				busno = txtbno.getText();
				if (male.isSelected())
					g = "M";
				else
					g = "F";
				try {
					pst = con.prepareStatement(
							"UPDATE travels.table_1 SET Name=? ,Source=?, Destination=?, Date=? ,Bus_no=?, Gender=? WHERE (id = ?)");
					pst.setString(1, name);
					pst.setString(2, src);
					pst.setString(3, des);
					pst.setString(4, date);
					pst.setString(5, busno);
					pst.setString(6, g);
					pst.setString(7, txtid.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updateddd!!!!!");
					table_load();
					txtname.setText("");
					comboBox.setSelectedIndex(0);
					comboBox_1.setSelectedIndex(0);
					txtdate.setText("");
					txtbno.setText("");
					male.setSelected(false);
					female.setSelected(false);
					txtname.requestFocus();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnExit_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnExit_1_1.setBounds(240, 32, 102, 35);
		panel_1.add(btnExit_1_1);
	}
}
