package org.gaofeng.ui;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

import org.gaofeng.common.MappingRule;
import org.gaofeng.common.MyPrintStream;
import org.gaofeng.common.properties.PropertiesTool;
import org.gaofeng.sqltodto.Main;

public class Start extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel domainNameLable = null;
	private JLabel sqlLable = null;
	private JTextField domainNameTextField = null;
	private JTextArea sqlTextField = null;
	private JButton runButton = null;

	private JTextComponent jTextField = null;
	private JPanel jContentPane = null;

	private JCheckBox ifDomain;
//	private JCheckBox ifDao;
	private JCheckBox ifMapper;
	private JCheckBox ifSql;
	/**
	 * This method initializes pathTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getDomainNameTextField() {
		if (domainNameTextField == null) {
			domainNameTextField = new JTextField();
			domainNameTextField.setBounds(new Rectangle(172, 33, 615, 29));
		}
		return domainNameTextField;
	}

	/**
	 * This method initializes saveTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextArea getSqlTextField() {
		if (sqlTextField == null) {
			sqlTextField = new JTextArea();
			sqlTextField.setBounds(new Rectangle(171, 93, 616, 405));
		}
		return sqlTextField;
	}

	/**
	 * This method initializes runButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getRunButton() {
		if (runButton == null) {
			runButton = new JButton();
			runButton.setBounds(new Rectangle(167, 508, 113, 36));
			runButton.setText("生成");
			runButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					String domainName = domainNameTextField.getText();
					if (domainName == null  || domainName.length() == 0  ) {
						JOptionPane.showMessageDialog(getFrame(), "填写不完整",
								"提示", JOptionPane.INFORMATION_MESSAGE);
					} else {
						runAction();
					}
				}
			});
		}
		return runButton;
	}

	private void runAction() {
		String domainName = domainNameTextField.getText();
		String sql = sqlTextField.getText();
		try {
			MappingRule.ifDomain=ifDomain.isSelected();
//			MappingRule.ifDao=ifDao.isSelected();
			MappingRule.ifMapper=ifMapper.isSelected();
			MappingRule.ifSql=ifSql.isSelected();
			
			Main.makeDomain(domainName, sql);
			JOptionPane.showMessageDialog(getFrame(), "生成完成", "提示",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			String exceptionStr = "";
			if (e.toString().indexOf("You have an error in your SQL syntax;") != -1) {
				exceptionStr = "请检查sql语句是否能正确执行,并且查询结果不存在重复列";
			} else if (e.toString().indexOf("Access denied for user") != -1) {
				exceptionStr = "请检查数据库口令是否正确";
			} else if (e.toString().indexOf("Communications link failure") != -1) {
				exceptionStr = "请检查数据库地址是否正确";
			} else {
				exceptionStr = e.toString();
			}
			System.out.println(exceptionStr);
			JOptionPane.showMessageDialog(getFrame(), "生成失败" + exceptionStr,
					"错误", JOptionPane.INFORMATION_MESSAGE);
			// TODO: handle exception
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Start thisClass = new Start();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public Start() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(1274, 606);
		this.setContentPane(getJContentPane());
		this.setTitle("sql语句生成domain工具");
		System.out.println("当前配置数据库为:"+PropertiesTool.TYPE);
		System.out.println("当前配置数据库连接为:"+PropertiesTool.URL);
		System.out.println("当前配置生成文件路径为:"+PropertiesTool.DOMAINPATH);
		System.out.println("生成包名为:"+PropertiesTool.DOMAINPACKAGENAME);

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {

			jTextField = new JTextArea();
			domainNameLable = new JLabel();
			domainNameLable.setBounds(new Rectangle(48, 33, 96, 28));
			domainNameLable.setText("实体类名称:");
			sqlLable = new JLabel();
			sqlLable.setBounds(new Rectangle(48, 93, 96, 28));
			sqlLable.setText("sql语句:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(domainNameLable, null);
			jContentPane.add(getDomainNameTextField(), null);
			jContentPane.add(sqlLable, null);
			jContentPane.add(getRunButton(), null);
			MyPrintStream mps = new MyPrintStream(System.out, jTextField);
			System.setOut(mps);
			System.setErr(mps);
			JScrollPane scroll = new JScrollPane();
			scroll.setBounds(797, 10, 451, 548);
			jContentPane.add(scroll, null);
			scroll.setViewportView(jTextField);

			JScrollPane scroll2 = new JScrollPane();
			scroll2.setBounds(171, 93, 616, 405);
			jContentPane.add(scroll2, null);
			scroll2.setViewportView(getSqlTextField());
			
			ifDomain = new JCheckBox("生成Domain");
			ifDomain.setSelected(true);
			ifDomain.setBounds(172, 68, 103, 23);
			jContentPane.add(ifDomain);
			
//			ifDao = new JCheckBox("生成dao");
//			ifDao.setSelected(true);
//			ifDao.setBounds(293, 68, 103, 23);
//			jContentPane.add(ifDao);
			
			ifMapper = new JCheckBox("生成Mapper");
			ifMapper.setSelected(true);
			ifMapper.setBounds(293, 68, 103, 23);
			jContentPane.add(ifMapper);
			
			ifSql = new JCheckBox("生成sql");
			ifSql.setSelected(true);
			ifSql.setBounds(398, 68, 103, 23);
			jContentPane.add(ifSql);

		}
		return jContentPane;
	}

	private JFrame getFrame() {
		return this;
	}
} // @jve:decl-index=0:visual-constraint="152,54"
