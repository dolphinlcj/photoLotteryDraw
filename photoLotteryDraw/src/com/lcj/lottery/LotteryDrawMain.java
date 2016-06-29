package com.lcj.lottery;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	//Constructor
	public LotteryDrawMain() {
		
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
	
	private void addWidgets() {
		
	}
	public static void main(String[] args) {
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
