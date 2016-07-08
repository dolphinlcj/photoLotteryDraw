package com.lcj.lottery;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * @version 1.0
 * @param dolphinlcj
 */
public class LotteryDrawMain {

	int screenWidth = 0;
	int screenHeight = 0;
	
	// ͼƬ�л����ʱ�� ms
	final static int DURATION = 50;
	// ͼƬ��ʾ�������ұ߾�
	final static int DISPLAY_MARGIN = 20;
	// ͨ�ñ߾�
	final static int MARGIN = 10;
	// �������
	final static int BUTTON_WIDTH = 200;
	// �������
	final static int RESULT_WIDTH = 400;
	// �����ͽ�����ø߶�
	final static int DOWN_HEIGHT = 100;

	JPanel mainPanel, selectPanel, displayPanel, resultPanel;
	
	// �齱���ư���
	JButton phaseChoice = null;
	// ��ʾ�����ǩ
	JLabel phaseIconLabel = null, phaseResult = null, phaseBlank1 = null,
				phaseBlank2 = null;
	//����Ŀ¼
	String rootDir = new StringBuffer(System.getProperty("user.dir")).
			append(File.separator).toString();
	
	//ͼƬĿ¼
	final static String IMAGE_DIR = "images/";
	final static String WINNER_DIR = "winners/";
	final static String WELCOME_IMAGE = "welcome/start.jpg";
	
	//Constructor
	public LotteryDrawMain() throws IOException {
		
		checkScreenSize();
		
		setPanelsSize();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		mainPanel.add(selectPanel);
		mainPanel.add(displayPanel);
		mainPanel.add(resultPanel);
		
		//��Ӳ������ȡ����ͼƬ������
		addWidgets();
	}
	
	private void checkScreenSize()	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	} 
	
	private void setPanelsSize() {
		int x, y, w, h = 0;
		//�齱��ť�����
		selectPanel = new JPanel();
		//�齱����ͣ��ť��
		x = screenWidth / 2 - BUTTON_WIDTH;
		y = screenHeight - MARGIN * 2 - DOWN_HEIGHT;
		w = BUTTON_WIDTH;
		h = DOWN_HEIGHT;
		selectPanel.setBounds(x, y, w, h);
		
		//ͼƬ��ʾ�������
		displayPanel = new JPanel();
		
		x = DISPLAY_MARGIN;
		y = MARGIN;
		w = screenWidth - DISPLAY_MARGIN * 2;
		h = screenHeight - DOWN_HEIGHT - MARGIN * 3;
		displayPanel.setBounds(x, y, w, h);
		
		//����ʾ�������
		resultPanel = new JPanel();
		x = screenWidth / 2 + MARGIN;
		y = screenHeight - MARGIN * 2 - DOWN_HEIGHT;
		w = RESULT_WIDTH;
		h = DOWN_HEIGHT;
		resultPanel.setBounds(x, y, w, h);
		
	}
	private void readImagesToArrays()
	{
		
	}
	private void addWidgets() throws IOException {
		//read image array
		readImagesToArrays();
		phaseIconLabel = new JLabel();
		phaseIconLabel.setHorizontalAlignment(JLabel.BOTTOM);
		phaseIconLabel.setVerticalAlignment(JLabel.BOTTOM);
		phaseIconLabel.setHorizontalTextPosition(JLabel.BOTTOM);
		phaseIconLabel.setHorizontalTextPosition(JLabel.BOTTOM);
		phaseIconLabel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLoweredBevelBorder(),
				BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		
		phaseResult = new JLabel();
		phaseBlank1 = new JLabel();
		phaseBlank2 = new JLabel();
		
		//create control button
		phaseChoice = new JButton("��ʼ/ֹͣ");
		
		//display the first welcome photo
		ImageIcon icon = null;
		String fileName = new StringBuffer(rootDir).append(WELCOME_IMAGE).toString();
		
		try {
			icon = new ImageIcon(ImageIO.read(new File(fileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException(new StringBuffer("ImageIO��ȡͼƬ�ļ�ʧ�� : ")
			.append(fileName).append(", ����jpgͼƬ��ʽ��").toString());
		}
		
		phaseIconLabel.setIcon(icon);
		phaseIconLabel.setText("");
		
		// ���������ñ߿�
		selectPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder("������"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		resultPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder("�����"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		// ͼƬ�������ñ߿�
		displayPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder("��Ƭ�б�"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		//��ʾ������Ӹ����
		selectPanel.add(phaseChoice);
		displayPanel.add(phaseIconLabel);
		resultPanel.add(phaseBlank1);
		resultPanel.add(phaseResult);
		resultPanel.add(phaseBlank2);
		
		//�����ư������ü�����
		//phaseChoice.addActionListener(this);
		
		
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LotteryDrawMain lot = new LotteryDrawMain();
		
		//set UI
		JFrame frame = new JFrame("�齱��");
		
		frame.setContentPane(lot.mainPanel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//display
		frame.pack();
		frame.setSize(lot.screenWidth, lot.screenHeight);
		frame.setVisible(true);
	}

}
