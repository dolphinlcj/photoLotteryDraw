package com.lcj.lottery;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PicPro extends WindowAdapter implements ActionListener {
	JFrame frame;
	JPanel panel1, panel3, panel4, panel5;
	JTextField tf1, tf2, tf3, tf4;
	JTextArea ta;
	JLabel label1, label2, label3, label4;
	JButton button, button1, button2;
	
	Font font1 = new Font("宋体", Font.PLAIN, 20);
	Font font2 = new Font("宋体", Font.PLAIN, 16);

	File filein;
	File fileout;
	File[] filelist;
	File wzw = new File("wzw");
	
	int width;
	int height;
	boolean flag_in;
	boolean flag_out;
	boolean flag_num;
	
	PicPro() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("批量处理图片");
		frame.setSize(800, 800);
		frame.setLocation(300, 10);
		frame.setResizable(false);
		
		panel1 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		
		tf1 = new JTextField(30);
		tf2 = new JTextField(30);
		tf3 = new JTextField(7);
		tf4 = new JTextField(7);
		tf1.setFont(font1);
		tf2.setFont(font1);
		tf3.setFont(font1);
		tf4.setFont(font1);
		
		label1 = new JLabel("源文件夹");
		label2 = new JLabel("目标文件夹");
		label3 = new JLabel("转换后X：");
		label4 = new JLabel("转换后Y:");
		label1.setFont(font2);
		label2.setFont(font2);
		label3.setFont(font2);
		label4.setFont(font2);
		
		ta = new JTextArea();
		ta.setFont(font2);
		ta.setEditable(false);
		ta.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane(ta);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		frame.add(scroll, BorderLayout.CENTER);
		ta.setText("请填写路径");
		
		button = new JButton("Change");
		button1 = new JButton("Check");
		button2 = new JButton("Check");
		button.setFont(font2);
		button1.setFont(font2);
		button2.setFont(font2);
		
		panel1.setLayout(new GridLayout(3, 1));
		panel1.add(panel3);
		panel1.add(panel4);
		panel1.add(panel5);
		panel3.add(label1);
		panel3.add(tf1);
		panel3.add(button1);
		panel4.add(label2);
		panel4.add(tf2);
		panel4.add(button2);
		panel5.add(label3);
		panel5.add(tf3);
		panel5.add(label4);
		panel5.add(tf4);
		panel5.add(button);
		
		frame.add(panel1, BorderLayout.NORTH);
		frame.setVisible(true);
		
		button.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		frame.addWindowListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button)
		{
			
		}
		else if (e.getSource() == button1) {
			
		}
		else if (e.getSource() == button2) {
			
		}
	}
	
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	/**
	 * 转换尺寸
	 * @param width
	 * @param height
	 * @param input
	 * @return output
	 */
	public static BufferedImage convert(int width, int height, 
			BufferedImage input) {
		BufferedImage output = null;
		
		return output;
	}
	
	/**
	 * 判断String是否为可转化为数字的字符串 
	 * @param s
	 * @return
	 */
	public static boolean checkNum(String s) {
		boolean flag = false;
		int i;
		char paste_ch[] = s.toCharArray();
		for(i = 0; i < s.length(); i++) {	
			if((paste_ch[i] != '1') && (paste_ch[i] != '2')
					&& (paste_ch[i] != '3') && (paste_ch[i] != '4')
					&& (paste_ch[i] != '5') && (paste_ch[i] != '6')
					&& (paste_ch[i] != '7') && (paste_ch[i] != '8')
					&& (paste_ch[i] != '9') && (paste_ch[i] != '0')
					&& (paste_ch[i] != '.') && (paste_ch[i] != '-')) {
				flag = false;
				break;
			}
			
			if((s.contains("-") == true && s.startsWith("-") == false) ||
					(s.contains(".") == true && s.startsWith(".") == true)) {
				flag = false;
				break;
			}
			
			flag = true;
		}
		
		return flag;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new PicPro();
	}

	

}
