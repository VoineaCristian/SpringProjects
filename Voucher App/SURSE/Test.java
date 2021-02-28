import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FileChooserUI;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.Component;

import com.sun.security.auth.NTNumericCredential;

import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.io.*;
import java.time.*;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
public class Test {

	private JFrame frame;
	private JFrame frame2;
	User user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() throws Exception{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		
		LocalDateTime N = LocalDateTime.now();
		int logat = 0;
		VMS.getInstance();
		VMS.getInstance().Actual = new TIME(N.getYear(),N.getMonthValue(),N.getDayOfMonth(),N.getHour(), N.getMinute());
		frame = new JFrame();
		frame2 = new JFrame();
		JFrame frame3 = new JFrame();
		frame3.setBounds(0, 0, 707, 412);
	
		JPanel panel_1 = new JPanel();
		panel_1.setVisible(false);
		panel_1.setBounds(0, 0, 707, 412);
		
		
		panel_1.setLayout(null);
		JButton Foloseste  = new JButton("Foloseste Voucher");
		Foloseste.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Foloseste.setForeground(SystemColor.infoText);
		Foloseste.setBackground(SystemColor.activeCaption);
		Foloseste.setBounds(210, 30, 200, 60);
		
		JButton Logout  = new JButton("Logout");
		Logout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Logout.setForeground(SystemColor.infoText);
		Logout.setBackground(SystemColor.activeCaption);
		Logout.setForeground(SystemColor.infoText);
		Logout.setBackground(SystemColor.inactiveCaption);
		Logout.setBounds(210, 300, 200, 60);
		panel_1.add(Logout);
		
		JButton btnNewButton_1 = new JButton("Campanii Existente");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(10, 22, 169, 58);
		panel_1.add(btnNewButton_1);
		panel_1.add(Foloseste);
		
		JButton btnAdaugaCampanie = new JButton("Adauga Campanie");
		
		btnAdaugaCampanie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdaugaCampanie.setBounds(436, 22, 169, 58);
		panel_1.add(btnAdaugaCampanie);
		
		JButton btnEditareCampanie = new JButton("Editare Campanie");
		btnEditareCampanie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEditareCampanie.setBounds(436, 263, 169, 58);
		panel_1.add(btnEditareCampanie);
		
		JButton btnInchidereCampanie = new JButton("Inchidere Campanie");
		btnInchidereCampanie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInchidereCampanie.setBounds(436, 100, 169, 58);
		panel_1.add(btnInchidereCampanie);
		
		
		
		JButton btnInchidereCampanie_1 = new JButton("Vouchere");
		btnInchidereCampanie_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInchidereCampanie_1.setBounds(5, 180, 169, 58);
		panel_1.add(btnInchidereCampanie_1);
		
		JButton btnGenerareVoucher = new JButton("Toate Voucherele");
		btnGenerareVoucher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGenerareVoucher.setBounds(436, 180, 169, 58);
		panel_1.add(btnGenerareVoucher);
		
		JButton btnGenerareVoucher_1 = new JButton("Generare Voucher");
		btnGenerareVoucher_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGenerareVoucher_1.setBounds(5, 263, 169, 58);
		panel_1.add(btnGenerareVoucher_1);
		
		JButton btnNotificari = new JButton("Notificari");
		btnNotificari.setBounds(10, 96, 169, 58);
		panel_1.add(btnNotificari);
		btnNotificari.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
	
		
		btnNotificari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel CTableMod3 = new DefaultTableModel();
				
				JTable CTable3 = new JTable(CTableMod3);
				CTable3.setEnabled(false);
				User us = null;
				for(int i = 0; i < VMS.getInstance().GetUsers().size(); i++)
				{
					if(VMS.getInstance().GetUsers().get(i).logat == 1)
						us = VMS.getInstance().GetUsers().get(i);
				}
				CTableMod3.addColumn("Id");
				CTableMod3.addColumn("Data");
				CTableMod3.addColumn("Vouchere");
				CTableMod3.addColumn("Tip");
				
				
			
//				for(int i = 0; i < us.getNotifications().size(); i++)
//				{
//					Notification N = us.getNotifications().get(i);
//					CTableMod3.addRow(new Object[]{N.id, N.date, N.VoucherCodeList, N.Type});
//					
//				}
				us.getNotifications().forEach(N -> CTableMod3.addRow(new Object[]{N.id, N.date, N.VoucherCodeList, N.Type}));
				
				
				
				CTable3.setVisible(true);
				JFrame frame13 = new JFrame();
				frame13.getContentPane().add(new JScrollPane(CTable3));
				frame13.setBounds(0,0,1000,500);
				
				frame13.setVisible(true);
			}
		});
		
		
		panel_1.setBounds(0, 0, 239, 93);
		
		frame3.getContentPane().add(panel_1);
		panel_1.setVisible(true);
		
		
	
	
		frame.setBounds(100, 100, 732, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JFileChooser Choose = new JFileChooser();
		frame2.getContentPane().add(Choose);
		frame2.setBounds(100, 100, 400, 400);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 686, 372);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JButton btnAddUsers = new JButton("Add Users");
		
		btnAddUsers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddUsers.setBounds(10, 171, 174, 57);
		panel.add(btnAddUsers);
		
		JButton btnNewButton = new JButton("Add Campaigns");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(458, 171, 174, 57);
		panel.add(btnNewButton);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(250, 154, 154, 19);
		textPane.setVisible(false);
		panel.add(textPane);
		
		JPasswordField textPane_1 = new JPasswordField();
		textPane_1.setBounds(250, 209, 154, 19);
		textPane_1.setVisible(false);
		textPane_1.setEchoChar('*');
		panel.add(textPane_1);
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setEditable(false);
		txtpnUsername.setBackground(SystemColor.menu);
		txtpnUsername.setForeground(SystemColor.desktop);
		txtpnUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnUsername.setText("Username");
		txtpnUsername.setBounds(135, 148, 92, 25);
		txtpnUsername.setVisible(false);
		panel.add(txtpnUsername);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setEditable(false);
		txtpnPassword.setBackground(SystemColor.menu);
		txtpnPassword.setText("Password");
		txtpnPassword.setForeground(Color.BLACK);
		txtpnPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnPassword.setBounds(135, 203, 92, 25);
		txtpnPassword.setVisible(false);
		
		panel.add(txtpnPassword);
		
		JButton btnLogare = new JButton("Logare");
		btnLogare.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogare.setBounds(235, 259, 174, 33);
		btnLogare.setVisible(false);
		panel.add(btnLogare);
		
		btnInchidereCampanie_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			JFrame frame7 = new JFrame();	
			DefaultTableModel CTableMod2 = new DefaultTableModel();
			JTable CTable2 = new JTable(CTableMod2);
			CTable2.setEnabled(false);
			User user = null ;
			
			CTableMod2.addColumn("Tip");
			CTableMod2.addColumn("ID");
			CTableMod2.addColumn("Cod");
			CTableMod2.addColumn("Valoare");
			CTableMod2.addColumn("Status");
			CTableMod2.addColumn("Mail");
			CTableMod2.addColumn("ID Campanie");
			CTableMod2.addColumn("Folosit la data de");			
			CTableMod2.addColumn("Valabil Pana La");
			
			
			for(int i = 0 ; i < VMS.getInstance().GetUsers().size(); i++)
			{
				
				if(VMS.getInstance().GetUsers().get(i).logat == 1)
					user = VMS.getInstance().GetUsers().get(i);		
			}
			
			
			for(int i = 0; i < VMS.getInstance().Campaigns.size(); i++)
			{
				 
				Campaign C = VMS.getInstance().Campaigns.get(i);
				Vector<Voucher> V = (Vector<Voucher>) user.GetVouchers().getValues(C.Id);
				if(V != null )
					for(int j = 0; j < V.size(); j++ )
					{	
						System.out.println("ar trebui sa afiseze");
						Voucher W = V.get(j);
						if(W instanceof LoyalityVoucher)
							CTableMod2.addRow(new Object[]{"Loyality Voucher",W.id, W.Code,((LoyalityVoucher) W).percentage, W.Status, W.Email, W.CampaignId,W.UDate, VMS.getInstance().GetCampaign(W.CampaignId).stop});
						if(W instanceof GiftVoucher)
							CTableMod2.addRow(new Object[]{"Gift Voucher",W.id, W.Code,((GiftVoucher) W).value, W.Status, W.Email, W.CampaignId,W.UDate, VMS.getInstance().GetCampaign(W.CampaignId).stop});
					}
			}
			
			
			
			System.out.println(user);
			CTable2.setVisible(true);	
			frame7.getContentPane().add(new JScrollPane(CTable2));	
			frame7.setBounds(0,0,1000,500);
			
			CTable2.setVisible(true);
			frame7.setVisible(true);
			
			
			
			
			
			}
			
		});
		btnGenerareVoucher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame14 = new JFrame();
			
				DefaultTableModel CTableMod5 = new DefaultTableModel();
				JTable table = new JTable(CTableMod5);		
				table.setEnabled(false);
				table.getRowSorter();
				table.setBounds(10, 338, 810, -263);			
				frame14.setBounds(100, 100, 900, 500);
							
				CTableMod5.addColumn("Tip");
				CTableMod5.addColumn("ID");
				CTableMod5.addColumn("Cod");
				CTableMod5.addColumn("Valoare");
				CTableMod5.addColumn("Status");
				CTableMod5.addColumn("Mail");
				CTableMod5.addColumn("ID Campanie");
				CTableMod5.addColumn("Folosit la data de");
				CTableMod5.addColumn("Valabil Pana La");
				frame14.getContentPane().add(new JScrollPane(table));
				table.setVisible(true);
				frame14.setVisible(true);
				
				
				
					
						for(int k = 0; k < VMS.getInstance().GetCampaigns().size(); k++)
						{	Campaign C = VMS.getInstance().GetCampaigns().get(k);
							for(int i = 0; i < VMS.getInstance().GetUsers().size(); i++)
							{
								User U = VMS.getInstance().GetUsers().get(i);
								Vector<Voucher> V = (Vector)U.GetVouchers().getValues(C.Id);
								if(V != null )
									for(int j = 0 ; j < V.size(); j++)
									{
										Voucher W = V.get(j);
										if(W instanceof LoyalityVoucher)
											CTableMod5.addRow(new Object[]{"Loyality Voucher",W.id, W.Code,((LoyalityVoucher) W).percentage, W.Status, W.Email, W.CampaignId,W.UDate,VMS.getInstance().GetCampaign(W.CampaignId).stop});
										if(W instanceof GiftVoucher)
											CTableMod5.addRow(new Object[]{"Gift Voucher",W.id, W.Code,((GiftVoucher) W).value, W.Status, W.Email, W.CampaignId,W.UDate, VMS.getInstance().GetCampaign(W.CampaignId).stop});
									}
							}
						}
							
						
							

			}
			
		});
		btnGenerareVoucher_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame8 = new JFrame();
				frame8.setUndecorated(true);
				
				frame8.setBounds(100, 100, 636, 440);
				
				frame8.getContentPane().setLayout(null);
				JButton btnNewButton2 = new JButton("Trimite");
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(154, 86, 366, 21);
				frame8.getContentPane().add(comboBox);
				for(int i = 0; i < VMS.getInstance().GetUsers().size(); i++)
				{
					comboBox.addItem(VMS.getInstance().GetUsers().get(i));
				}
				
				JButton btnDistribuireMultipla = new JButton("Distribuire Multipla");
				btnDistribuireMultipla.setBounds(342, 294, 230, 53);
				frame8.getContentPane().add(btnDistribuireMultipla);
				frame8.setVisible(true);
				JComboBox comboBox_1 = new JComboBox();
				comboBox_1.setBounds(154, 55, 366, 21);
				frame8.getContentPane().add(comboBox_1);
				
				for(int i = 0; i < VMS.getInstance().GetCampaigns().size(); i++)
				{
					if(VMS.getInstance().GetCampaigns().get(i).Status != CampaignStatusType.CANCELLED)
					{
						
						comboBox_1.addItem(VMS.getInstance().GetCampaigns().get(i));
					}
				}
								
				JComboBox comboBox_2 = new JComboBox();
				comboBox_2.setBounds(154, 163, 150, 21);
				frame8.getContentPane().add(comboBox_2);
				comboBox_2.addItem("GiftVoucher");
				comboBox_2.addItem("LoialityVoucher");
				JTextField txtSugetieUser = new JTextField();
				txtSugetieUser.setText("Sugetie User");
				txtSugetieUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtSugetieUser.setEditable(false);
				txtSugetieUser.setColumns(10);
				txtSugetieUser.setBackground(SystemColor.menu);
				txtSugetieUser.setBounds(48, 129, 96, 25);
				frame8.getContentPane().add(txtSugetieUser);
				
				JTextField textField = new JTextField();
				textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
				textField.setColumns(10);
				textField.setBackground(SystemColor.menu);
				textField.setBounds(154, 128, 366, 25);
				frame8.getContentPane().add(textField);
				frame8.setVisible(true);
					
				
				JTextField txtCampanie = new JTextField();
				txtCampanie.setEditable(false);
				txtCampanie.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtCampanie.setText("Campanie");
				txtCampanie.setBackground(SystemColor.menu);
				txtCampanie.setBounds(48, 51, 96, 25);
				frame8.getContentPane().add(txtCampanie);
				txtCampanie.setColumns(10);
				
				JTextField txtUser = new JTextField();
				txtUser.setEditable(false);
				txtUser.setText("User");
				txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtUser.setColumns(10);
				txtUser.setBackground(SystemColor.menu);
				txtUser.setBounds(48, 86, 96, 25);
				frame8.getContentPane().add(txtUser);
				
				JTextField txtTipVoucher = new JTextField();
				txtTipVoucher.setEditable(false);
				txtTipVoucher.setText("Tip Voucher");
				txtTipVoucher.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtTipVoucher.setColumns(10);
				txtTipVoucher.setBackground(SystemColor.menu);
				txtTipVoucher.setBounds(48, 164, 96, 25);
				frame8.getContentPane().add(txtTipVoucher);
				
				JTextField txtValoare = new JTextField();
				txtValoare.setEditable(false);
				txtValoare.setText("Valoare");
				txtValoare.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtValoare.setColumns(10);
				txtValoare.setBackground(SystemColor.menu);
				txtValoare.setBounds(49, 219, 96, 25);
				frame8.getContentPane().add(txtValoare);
				JButton btnAnulare = new JButton("Anulare");
				btnAnulare.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnAnulare.setBounds(215, 378, 157, 52);
				frame8.getContentPane().add(btnAnulare);
				
				JTextPane textPane = new JTextPane();
				textPane.setBounds(155, 219, 88, 25);
				frame8.getContentPane().add(textPane);
				
				
				btnNewButton2.setBounds(48, 294, 230, 53);
				frame8.getContentPane().add(btnNewButton2);
				frame8.setVisible(true);
				
				textField.setEditable(false);
				Campaign C = (Campaign)comboBox_1.getSelectedItem();
				if(C.getObserver() != null)
				{
					C = (Campaign)comboBox_1.getSelectedItem();
					if(C.Strategy.equals("A"))
					{
						Random R = new Random();
						int i = R.nextInt();
						if(i < 0)
							i = 0 - i;
						
						i = i % C.getObserver().size();
						textField.setText(VMS.getInstance().GetUser(i).email);
						
						
						
					}
					if(C.Strategy.equals("B"))
					{
					
						int max = -1;
						int maxi = -1;
						for(int i = 0; i < C.getObserver().size(); i++)
						{
							Vector<Voucher> V = (Vector<Voucher>)C.getObserver().get(i).GetVouchers().getValues(C.Id);
							if(V != null)
							{
								int k = 0;
								for(int j = 0; j < V.size(); j++)
								{
									if(V.get(j).Status == VoucherStatusType.USED)
									{
										k++;
									}
								}
								if(k > max)
								{
									max = k;
									maxi = i;
								}
							}
						}
						if(max != -1)
						{
							textField.setText(VMS.getInstance().GetUser(maxi).email);
							C.StrUs = 1;
						
						}
					}
					if(C.Strategy.equals("C"))
					{
						int min = 999;
						int mini = 999;
						for(int i = 0; i < C.getObserver().size(); i++)
						{
							Vector<Voucher> V = (Vector<Voucher>)C.getObserver().get(i).GetVouchers().getValues(C.Id);
							if(V != null)
							{
								
								if(V.size() < min)
								{
									min = V.size();
									mini = i;
								}
							}
						}
						if(min != 999)
						{
							textField.setText(VMS.getInstance().GetUser(mini).email);
							C.StrUs = 1;
							
						}
						
					}
				}
				
			
				btnAnulare.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame8.setVisible(false);
					}
				});
				btnDistribuireMultipla.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					
						Campaign C = (Campaign) comboBox_1.getSelectedItem();
						if(Choose.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
						{
							File UFile = Choose.getSelectedFile();
							System.out.println(UFile.getAbsolutePath());
							
							try {
								BufferedReader ReadU = new BufferedReader(new FileReader(UFile));
								String R = null;
								R = ReadU.readLine();
								int count = Integer.parseInt(R);
								
								while((R = ReadU.readLine()) != null)
								{
									
									StringTokenizer st = new StringTokenizer(R,";");
									User U = VMS.getInstance().getUserEmail(st.nextToken());
									String tip = st.nextToken();
									
									C.addObserver(U);
									VMS.getInstance().Actual = new TIME(N.getYear(),N.getMonthValue(),N.getDayOfMonth(),N.getHour(), N.getMinute());
									Voucher V = C.GenerateVoucher(U.email,tip, Float.parseFloat(st.nextToken()));
									U.GetVouchers().addVoucher(V);
									frame8.setVisible(false);
									
									
									 
								}
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							
						}
						
					
						
					}
				});

				btnNewButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						Campaign C = (Campaign)comboBox_1.getSelectedItem();
						if(C.budget - Integer.parseInt(textPane.getText()) >=0  )
						{		
							User U = (User)comboBox.getSelectedItem();
							Voucher V = C.GenerateVoucher(U.email, comboBox_2.getSelectedItem().toString(), Integer.parseInt(textPane.getText()));
							U.GetVouchers().addVoucher(V);
							C.addObserver(U);
							C.budget -= Integer.parseInt(textPane.getText());
							frame8.setVisible(false);
						}	else 
						{	
							JFrame frame9 = new JFrame();
							frame9.setBounds(400, 400, 261, 101);
							frame9.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							frame9.getContentPane().setLayout(null);
							
							JButton btnNewButton9 = new JButton("Buget Insuficient");
							btnNewButton9.setFont(new Font("Tahoma", Font.PLAIN, 20));
							btnNewButton9.setBounds(10, 10, 232, 46);
							frame9.getContentPane().add(btnNewButton9);
							frame9.setVisible(true);
							btnNewButton9.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									
									frame9.setVisible(false);
									frame8.setVisible(false);
								}
							});
						}
					}
				});
				
				
			}
		});
				JButton button = new JButton("");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ka = 1;
				btnAdaugaCampanie.setVisible(!false);
				btnEditareCampanie.setVisible(!false);
				btnGenerareVoucher.setVisible(!false);
				btnInchidereCampanie.setVisible(!false);
				btnGenerareVoucher_1.setVisible(!false);
				if(frame2.getBackground().equals(Color.red))
				{
					button.setVisible(!true);
					txtpnPassword.setVisible(!false);
					txtpnUsername.setVisible(!false);
					textPane.setVisible(!false);
					textPane_1.setVisible(!false);
					btnLogare.setVisible(!false);
					ka = 0;
					 System.out.println("parola rea");
				} else if(frame2.getBackground().equals(Color.green))
				{
						
						btnAdaugaCampanie.setVisible(false);
						btnEditareCampanie.setVisible(false);
						btnGenerareVoucher.setVisible(false);
						btnInchidereCampanie.setVisible(false);
						btnGenerareVoucher_1.setVisible(false);
						
						
						
						
				}
				if(ka == 1)
				{
					button.setVisible(!true);
					txtpnPassword.setVisible(!false);
					txtpnUsername.setVisible(!false);
					textPane.setVisible(!false);
					textPane_1.setVisible(!false);
					btnLogare.setVisible(!false);
					frame.setVisible(false);
					frame3.setVisible(true);
					System.out.println("ar trebui");
				}
					
			
				
			}
		});
		
		btnAdaugaCampanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame10 = new JFrame();
				frame10.getContentPane().setBackground(SystemColor.activeCaption);
				frame10.getContentPane().setLayout(null);
				frame10.setBounds(100, 100, 469, 457);
				JTextPane txtpnGenerareCampanie = new JTextPane();
				txtpnGenerareCampanie.setBackground(SystemColor.activeCaption);
				txtpnGenerareCampanie.setFont(new Font("Tahoma", Font.PLAIN, 25));
				txtpnGenerareCampanie.setBounds(116, 10, 227, 45);
				txtpnGenerareCampanie.setText("Generare Campanie");
				txtpnGenerareCampanie.setEditable(false);
				frame10.getContentPane().add(txtpnGenerareCampanie);
				
				JTextField txtDenumire = new JTextField();
				txtDenumire.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtDenumire.setBackground(Color.PINK);
				txtDenumire.setText("Denumire");
				txtDenumire.setBounds(70, 101, 80, 19);
				frame10.getContentPane().add(txtDenumire);
				txtDenumire.setColumns(10);
				txtDenumire.setEditable(false);
				
				JTextField txtDescriere = new JTextField();
				txtDescriere.setBackground(Color.PINK);
				txtDescriere.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtDescriere.setText("Descriere");
				txtDescriere.setColumns(10);
				txtDescriere.setBounds(70, 148, 80, 19);
				frame10.getContentPane().add(txtDescriere);
				txtDescriere.setEditable(false);
				
				JTextField txtBuget = new JTextField();
				txtBuget.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtBuget.setBackground(Color.PINK);
				txtBuget.setText("Buget");
				txtBuget.setColumns(10);
				txtBuget.setBounds(70, 193, 50, 25);
				frame10.getContentPane().add(txtBuget);
				txtBuget.setEditable(false);
				
				JTextField textField_3 = new JTextField();
				textField_3.setColumns(10);
				textField_3.setBounds(227, 150, 136, 19);
				frame10.getContentPane().add(textField_3);
				
				JTextField textField_4 = new JTextField();
				textField_4.setColumns(10);
				textField_4.setBounds(227, 103, 136, 19);
				JTextField textField_5 = new JTextField();
				textField_5.setColumns(10);
				textField_5.setBounds(227, 103, 136, 19);
				frame10.getContentPane().add(textField_4);
				
				JTextField textField = new JTextField();
				textField.setColumns(10);
				textField.setBounds(227, 198, 136, 19);
				frame10.getContentPane().add(textField);
				frame10.setVisible(true);
				JButton btnTrimte = new JButton("Genereaza");
				btnTrimte.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnTrimte.setBounds(141, 347, 151, 40);
				frame10.getContentPane().add(btnTrimte);
				
				JTextField textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(227, 245, 136, 19);
				frame10.getContentPane().add(textField_1);
				
				JTextField txtDataDeFinal = new JTextField();
				txtDataDeFinal.setText("Data De Final");
				txtDataDeFinal.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtDataDeFinal.setEditable(false);
				txtDataDeFinal.setColumns(10);
				txtDataDeFinal.setBackground(Color.PINK);
				txtDataDeFinal.setBounds(70, 245, 97, 25);
				frame10.getContentPane().add(txtDataDeFinal);
				
				JTextField txtStrategie = new JTextField();
				txtStrategie.setText("Strategie");
				txtStrategie.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtStrategie.setEditable(false);
				txtStrategie.setColumns(10);
				txtStrategie.setBackground(Color.PINK);
				txtStrategie.setBounds(70, 296, 97, 25);
				frame10.getContentPane().add(txtStrategie);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(227, 300, 136, 21);
				comboBox.addItem("A");
				comboBox.addItem("B");
				comboBox.addItem("C");
				textField_1.setText("YYYY-MM-DD HH:MM");
				frame10.getContentPane().add(comboBox);
				btnTrimte.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Random rand = new Random();
						int idd = rand.nextInt(999);
						int i = 0;
						while(i < VMS.getInstance().GetCampaigns().size())
						{
							if(VMS.getInstance().GetCampaigns().get(i).Id == idd && idd > 0 )
							{
								i = 0;
								idd = rand.nextInt();
								
							}
							i++;
						}
						VMS.getInstance().Actual = new TIME(N.getYear(),N.getMonthValue(),N.getDayOfMonth(),N.getHour(), N.getMinute());
						VMS.getInstance().AddCampaign(new Campaign(idd,textField_4.getText()  , textField_3.getText(), VMS.getInstance().Actual,new TIME(textField_1.getText()) ,Float.parseFloat(textField.getText()),comboBox.getSelectedItem().toString()));
						
						frame10.setVisible(false);
						btnGenerareVoucher_1.setEnabled(!false);
						btnEditareCampanie.setEnabled(!false);
						btnInchidereCampanie.setEnabled(!false);
						
					}
				});
				
				
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel CTableMod = new DefaultTableModel();
			
				JTable CTable = new JTable(CTableMod);
				CTable.setEnabled(false);
				CTableMod.addColumn("ID");
				CTableMod.addColumn("Nume");
				CTableMod.addColumn("Descriere");
				CTableMod.addColumn("Data de Inceput");
				CTableMod.addColumn("Data de Final");
				CTableMod.addColumn("Buget");
				CTableMod.addColumn("Strategie");
				CTableMod.addColumn("Stare");
				
			
				for(int i = 0; i < VMS.getInstance().GetCampaigns().size(); i++)
				{
					Campaign C;
					C = VMS.getInstance().GetCampaigns().get(i);
					CTableMod.addRow(new Object[]{C.Id,C.name,C.Description, C.start, C.stop, C.budget, C.Strategy, C.Status});
				}
				
				
				
				CTable.setVisible(true);
				JFrame frame4 = new JFrame();
				frame4.getContentPane().add(new JScrollPane(CTable));
				frame4.setBounds(0,0,1000,500);
				
				frame4.setVisible(true);
				
				
				
			}
		});
		
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				frame.setVisible(true);
				frame3.setVisible(false);
				for(int i = 0; i < VMS.getInstance().GetUsers().size();i++)
				{
					VMS.getInstance().GetUsers().get(i).logat = 0;
				}
				
			}
		});
		
		btnEditareCampanie.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e) {
						JFrame frame5 = new JFrame();
						frame5.setBounds(0,0,500,500);
						frame5.setVisible(true);
						JPanel pan = new JPanel();
						pan.setLayout(null);
						pan.setBounds(0,0,700,700);
					
						JComboBox Combo = new JComboBox();
						Combo.setBounds(10,40,500,20);
						for(int i = 0; i < VMS.getInstance().GetCampaigns().size(); i++)
						{
							if(VMS.getInstance().GetCampaigns().get(i).Status != CampaignStatusType.CANCELLED)
								Combo.addItem(VMS.getInstance().GetCampaigns().get(i));
						}
						
						pan.add(Combo);
						pan.setVisible(true);
						frame5.add(pan);
						JButton Bug = new JButton();
						JButton sf = new JButton();
						Bug.setBounds(150, 150, 200, 30);
						Bug.setText("Modifica Bugetul");
						Bug.setVisible(true);
						sf.setBounds(150, 200, 200, 30);
						sf.setText("Modifica Data De Sfarsit");
						sf.setVisible(true);
						JButton mod = new JButton();
						mod.setBounds(150, 250, 200, 30);
						mod.setText("Aplica Strategia");
						
						pan.add(sf);
						pan.add(Bug);
						pan.add(mod);
			
						JFrame frame6 = new JFrame();
						Campaign C;
						C =(Campaign) Combo.getSelectedItem();
						mod.setVisible(true);
						if(C.StrUs == 1)
							mod.setEnabled(false);
						
						Combo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Campaign C2 =(Campaign) Combo.getSelectedItem();
								mod.setEnabled(true);
								if(C2.StrUs == 1)
									mod.setEnabled(false);
							}
						});
					
						
						
						frame6.setBounds(100, 100, 636, 194);
						frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame6.getContentPane().setLayout(null);
						
						JTextPane txtpnVechiulBugetEste = new JTextPane();
						txtpnVechiulBugetEste.setEditable(false);
						txtpnVechiulBugetEste.setBackground(SystemColor.menu);
						txtpnVechiulBugetEste.setFont(new Font("Tahoma", Font.PLAIN, 15));
						txtpnVechiulBugetEste.setText("Vechiul Buget Este De :");
						txtpnVechiulBugetEste.setBounds(21, 44, 165, 25);
						frame6.getContentPane().add(txtpnVechiulBugetEste);
						
						JTextPane txtpnIntroducetiNoulBuget = new JTextPane();
						txtpnIntroducetiNoulBuget.setEditable(false);
						txtpnIntroducetiNoulBuget.setBackground(SystemColor.menu);
						txtpnIntroducetiNoulBuget.setFont(new Font("Tahoma", Font.PLAIN, 15));
						txtpnIntroducetiNoulBuget.setText("Introduceti Noul Buget :");
						txtpnIntroducetiNoulBuget.setToolTipText("");
						txtpnIntroducetiNoulBuget.setBounds(21, 107, 175, 25);
						frame6.getContentPane().add(txtpnIntroducetiNoulBuget);
						
						JTextPane textPane = new JTextPane();
						textPane.setEditable(false);
						textPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
						textPane.setBackground(SystemColor.menu);
						textPane.setBounds(185, 44, 137, 25);
						textPane.setText(String.valueOf(C.budget));
						frame6.getContentPane().add(textPane);
						
						JButton btnNewButtonM = new JButton("Modifica");
						btnNewButtonM.setFont(new Font("Tahoma", Font.PLAIN, 19));
						btnNewButtonM.setBounds(387, 44, 175, 69);
						frame6.getContentPane().add(btnNewButtonM);
						
						JTextPane textPane_1 = new JTextPane();
						textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
						textPane_1.setBackground(SystemColor.activeCaption);
						
						textPane_1.setBounds(199, 107, 137, 25);
						frame6.getContentPane().add(textPane_1);
						
						Bug.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
						frame6.setVisible(true);
						Campaign C =(Campaign) Combo.getSelectedItem();
						textPane.setText(String.valueOf(C.budget));
						btnNewButtonM.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
										Campaign C =(Campaign) Combo.getSelectedItem();
										String NewBudget = textPane_1.getText();
										VMS.getInstance().GetCampaign(C.Id).budget = (float)Integer.valueOf(NewBudget);
										
										VMS.getInstance().UpdateCampaign(C.Id, C);
										textPane.setText(NewBudget);
										frame6.setVisible(false);
										frame5.setVisible(false);
										
									}
									});
						
						
						
				
									
								
					}
				});
						sf.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								Campaign C =(Campaign) Combo.getSelectedItem();
								textPane.setText(C.stop.toString());
								txtpnVechiulBugetEste.setText("Vechea Data: ");
								txtpnIntroducetiNoulBuget.setText("Introduceti Noua Data :");
								frame6.setVisible(true);
								
								btnNewButtonM.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
										String NewDate = textPane_1.getText();
										VMS.getInstance().GetCampaign(C.Id).stop = new TIME(NewDate);
										frame6.setVisible(false);
										
										VMS.getInstance().Actual = new TIME(N.getYear(),N.getMonthValue(),N.getDayOfMonth(),N.getHour(), N.getMinute());
										VMS.getInstance().GetCampaign(C.Id).notifyAllObservers(new Notification(VMS.getInstance().Actual, C.Id, null, NotificationType.EDIT));;
										frame5.setVisible(false);
									}
									});
								
							}
						});
						mod.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Campaign C =(Campaign) Combo.getSelectedItem();
								if(C.Strategy.equals("A"))
								{
									Random R = new Random();
									int i = R.nextInt();
									if(i < 0)
										i = 0 - i;
									i = i % C.getObserver().size();
									Voucher voucher = C.GenerateVoucher(C.Users.get(i).email, "GiftVoucher", 100);
									C.Users.get(i).GetVouchers().addVoucher(voucher);
									C.StrUs = 1;
									frame5.setVisible(false);
									
									
								}
								if(C.Strategy.equals("B"))
								{
								
									int max = -1;
									int maxi = -1;
									for(int i = 0; i < C.getObserver().size(); i++)
									{
										Vector<Voucher> V = (Vector<Voucher>)C.getObserver().get(i).GetVouchers().getValues(C.Id);
										if(V != null)
										{
											int k = 0;
											for(int j = 0; j < V.size(); j++)
											{
												if(V.get(j).Status == VoucherStatusType.USED)
												{
													k++;
												}
											}
											if(k > max)
											{
												max = k;
												maxi = i;
											}
										}
									}
									if(max != -1)
									{
										Voucher voucher = C.GenerateVoucher(C.Users.get(maxi).email, "LoyalityVoucher", 50);
										C.Users.get(maxi).GetVouchers().addVoucher(voucher);
										System.out.println(voucher);
										C.StrUs = 1;
										frame5.setVisible(false);
									}
								}
								if(C.Strategy.equals("C"))
								{
									int min = 999;
									int mini = 999;
									for(int i = 0; i < C.getObserver().size(); i++)
									{
										Vector<Voucher> V = (Vector<Voucher>)C.getObserver().get(i).GetVouchers().getValues(C.Id);
										if(V != null)
										{
											
											if(V.size() < min)
											{
												min = V.size();
												mini = i;
											}
										}
									}
									if(min != 999)
									{
										Voucher voucher = C.GenerateVoucher(C.Users.get(mini).email, "LoyaityVoucher", 50);
										C.Users.get(mini).GetVouchers().addVoucher(voucher);
										C.StrUs = 1;
										frame5.setVisible(false);
									}
									
								}
								frame5.setVisible(false);
								System.out.println("is aici");
								
							}
							});
				
				
			}
				
			});
			
		

		
			
			
		button.setBounds(223, 148, 203, 101);
		button.setVisible(false);
		panel.add(button);
		
		btnLogare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String name = textPane.getText();
				String pass = textPane_1.getText();
				System.out.println(pass);
				
				for(int i = 0; i < VMS.getInstance().GetUsers().size(); i++)
				{
					if(name.equals(VMS.getInstance().GetUsers().get(i).getName()) && pass.equals(VMS.getInstance().GetUsers().get(i).getPass()))
					{
						System.out.println("am verificat");
						
						VMS.getInstance().GetUsers().get(i).logat = 1;
						System.out.println("s-a logat " + VMS.getInstance().GetUsers().get(i));
						System.out.println(user);
						button.setText("<html><font size=-1><b><u>Te-ai logat cu succes</u></b>" + "<p>Click Pentru A Continua</html>");
						button.setBackground(Color.BLUE);
						
						
						if(VMS.getInstance().GetUsers().get(i).getStat() == UserType.ADMIN)
							frame2.setBackground(Color.blue);
						
						
						else frame2.setBackground(Color.green);
						break;
					}
					
					if(i == VMS.getInstance().GetUsers().size() - 1)
					{
						button.setText("<html><font size=-1><b><u>Nume Sau Parola Gresita </u></b>" + "<p>Click Pentru A Reincerca</html>");
						button.setBackground(Color.red);
						frame2.setBackground(Color.red);
						
						
					}
					
					
					
					
				}
				
				
				
			
				button.setVisible(true);
				txtpnPassword.setVisible(false);
				txtpnUsername.setVisible(false);
				textPane.setVisible(false);
				textPane_1.setVisible(false);
				btnLogare.setVisible(false);
				
				
				
				
			}
		});
		btnInchidereCampanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame11 = new JFrame();
				frame11.getContentPane().setBackground(SystemColor.activeCaption);
				frame11.getContentPane().setLayout(null);
				
				JComboBox comboBox5 = new JComboBox();
				comboBox5.setBounds(119, 77, 222, 21);
				frame11.getContentPane().add(comboBox5);
				
				JButton btnInchideCampania = new JButton("Inchide Campania");
				btnInchideCampania.setFont(new Font("Tahoma", Font.PLAIN, 17));
				btnInchideCampania.setBounds(144, 170, 169, 56);
				frame11.getContentPane().add(btnInchideCampania);
				frame11.setBounds(100, 100, 469, 370);
				JTextField textField_5 = new JTextField();
				textField_5.setColumns(10);
				textField_5.setBounds(227, 103, 136, 19);
				int k = 0;
				for(int i = 0; i < VMS.getInstance().GetCampaigns().size(); i++)
				{
					
					if(VMS.getInstance().GetCampaigns().get(i).Status != CampaignStatusType.CANCELLED)
					{
						comboBox5.addItem(VMS.getInstance().GetCampaigns().get(i));
						k++;
														
					}
					
				}
				
				
					
				
				btnInchideCampania.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Campaign C = (Campaign) comboBox5.getSelectedItem();
						VMS.getInstance().ActiveC--;
						VMS.getInstance().CancelCampaign(C.Id);
						frame11.setVisible(false);
						if(VMS.getInstance().ActiveC == 0)
						{
							btnGenerareVoucher_1.setEnabled(false);
							btnEditareCampanie.setEnabled(false);
							btnInchidereCampanie.setEnabled(false);
						}
					}
				});
				frame11.setVisible(true);
				
				
				
			}
		});
		
		Foloseste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame11 = new JFrame();
				frame11.getContentPane().setBackground(SystemColor.activeCaption);
				frame11.getContentPane().setLayout(null);
				
				JComboBox comboBox5 = new JComboBox();
				comboBox5.setBounds(119, 77, 222, 21);
				frame11.getContentPane().add(comboBox5);
				
				JButton btnInchideCampania = new JButton("Foloseste");
				btnInchideCampania.setFont(new Font("Tahoma", Font.PLAIN, 17));
				btnInchideCampania.setBounds(144, 170, 169, 56);
				frame11.getContentPane().add(btnInchideCampania);
				frame11.setBounds(100, 100, 469, 370);
				JTextField textField_5 = new JTextField();
				textField_5.setColumns(10);
				textField_5.setBounds(227, 103, 136, 19);
				int k = 0;
				User user = null;
				for(int i = 0; i < VMS.getInstance().GetUsers().size(); i++)
				{
					if(VMS.getInstance().GetUsers().get(i).logat == 1)
						user = VMS.getInstance().GetUsers().get(i);
				}
				for(int j = 0; j < VMS.getInstance().GetCampaigns().size(); j++)
				{
					Campaign C = (Campaign) VMS.getInstance().GetCampaigns().get(j);
					Vector <Voucher> V = null ;
					System.out.println(user);
					if(user.GetVouchers().getValues(C.Id) != null )
					{
						V = (Vector<Voucher>) user.GetVouchers().getValues(C.Id);
					}
					if(V != null)
						for(int i = 0; i < V.size(); i++)
						{
							
							if(V.get(i).Status != VoucherStatusType.USED && V.get(i).Status != VoucherStatusType.EXPIRED)
							{
								comboBox5.addItem(V.get(i));
															
							}
							
						}
				}
				
				
				
					
				
				btnInchideCampania.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Voucher V = (Voucher) comboBox5.getSelectedItem();
						VMS.getInstance().ActiveC--;
						VMS.getInstance().Actual = new TIME(N.getYear(),N.getMonthValue(),N.getDayOfMonth(),N.getHour(), N.getMinute());
						VMS.getInstance().GetCampaign(V.CampaignId).redeemVoucher(V.id, VMS.getInstance().Actual);
						frame11.setVisible(false);
						
					}
				});
				frame11.setVisible(true);
				
				
				
			}
		});
		btnAddUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				
				if(Choose.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					File UFile = Choose.getSelectedFile();
					System.out.println(UFile.getAbsolutePath());
					btnAddUsers.setEnabled(false);
					try {
						BufferedReader ReadU = new BufferedReader(new FileReader(UFile));
						String R = null;
						R = ReadU.readLine();
						int count = Integer.parseInt(R);
						
						while((R = ReadU.readLine()) != null)
						{
							
							StringTokenizer st = new StringTokenizer(R,";");
							
							  VMS.getInstance().AddUser(new User(Integer.parseInt(st.nextToken()),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()));
							 
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
				
				System.out.println(VMS.getInstance().GetUsers());
				if(!btnNewButton.isEnabled() && !btnAddUsers.isEnabled())
				{
					btnAddUsers.setVisible(false);
					btnNewButton.setVisible(false);
					txtpnPassword.setVisible(true);
					txtpnUsername.setVisible(true);
					textPane.setVisible(true);
					textPane_1.setVisible(true);
					btnLogare.setVisible(true);
					
				}
				
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(Choose.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
					{
						File CFile = Choose.getSelectedFile();
						System.out.println(CFile.getAbsolutePath());
						btnNewButton.setEnabled(false);
						try {
							BufferedReader ReadC = new BufferedReader(new FileReader(CFile));
							String R = null;
							R = ReadC.readLine();
							int count = Integer.parseInt(R);
							
							while((R = ReadC.readLine()) != null)
							{
								
								
						
								System.out.println(VMS.getInstance().Actual);
								while(count != 0)
								{
									R = ReadC.readLine();
									StringTokenizer st = new StringTokenizer(R,";");
									LocalDateTime tm = LocalDateTime.now();
									VMS.getInstance().AddCampaign(new Campaign(Integer.parseInt(st.nextToken()),st.nextToken(),st.nextToken(),new TIME(st.nextToken()),new TIME(st.nextToken()),Integer.parseInt(st.nextToken()),st.nextToken()));
									count --;
								}		 
							}
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
						
						
						
						
					
				}
					System.out.println(VMS.getInstance().GetCampaigns());
					if(!btnNewButton.isEnabled() && !btnAddUsers.isEnabled())
					{
						btnAddUsers.setVisible(false);
						btnNewButton.setVisible(false);
						txtpnPassword.setVisible(true);
						txtpnUsername.setVisible(true);
						textPane.setVisible(true);
						textPane_1.setVisible(true);
						btnLogare.setVisible(true);
						
					}
			}
		});
		
		
		
		
		
		
		
		
	}
}

