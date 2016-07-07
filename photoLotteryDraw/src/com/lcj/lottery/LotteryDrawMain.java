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
	
	// 图片切换间隔时间 ms
	final static int DURATION = 50;
	// 图片显示区域左右边距
	final static int DISPLAY_MARGIN = 20;
	// 通用边距
	final static int MARGIN = 10;
	// 按键宽度
	final static int BUTTON_WIDTH = 200;
	// 结果框宽度
	final static int RESULT_WIDTH = 400;
	// 按键和结果框公用高度
	final static int DOWN_HEIGHT = 100;

	JPanel mainPanel, selectPanel, displayPanel, resultPanel;
	JLabel phaseIconLabel = null, phaseResult = null, phaseBlank1 = null, phaseBlank2 = null; 

	//Constructor
	public LotteryDrawMain() {
		
		checkScreenSize();
		
		setPanelsSize();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		mainPanel.add(selectPanel);
		mainPanel.add(displayPanel);
		mainPanel.add(resultPanel);
		
		//添加插件，读取所有图片入数组
		addWidgets();
	}
	
	private void checkScreenSize()	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	} 
	
	private void setPanelsSize() {
		int x, y, w, h = 0;
		//抽奖按钮区面板
		selectPanel = new JPanel();
		//抽奖和暂停按钮键
		x = screenWidth / 2 - BUTTON_WIDTH;
		y = screenHeight - MARGIN * 2 - DOWN_HEIGHT;
		w = BUTTON_WIDTH;
		h = DOWN_HEIGHT;
		selectPanel.setBounds(x, y, w, h);
		
		//图片显示区域面板
		displayPanel = new JPanel();
		
		x = DISPLAY_MARGIN;
		y = MARGIN;
		w = screenWidth - DISPLAY_MARGIN * 2;
		h = screenHeight - DOWN_HEIGHT - MARGIN * 3;
		displayPanel.setBounds(x, y, w, h);
		
		//获奖显示区域面板
		resultPanel = new JPanel();
		x = screenWidth / 2 + MARGIN;
		y = screenHeight - MARGIN * 2 - DOWN_HEIGHT;
		w = RESULT_WIDTH;
		h = DOWN_HEIGHT;
		resultPanel.setBounds(x, y, w, h);
		
	}
	
	private void addWidgets() {
		//读取图片 
		readImagesToArrays();
		
		//use JLabel to display the photo
		phaseIconLabel = new JLabel();
		phaseIconLabel.setHorizontalAlignment(JLabel.CENTER);
		phaseIconLabel.setVerticalAlignment(JLabel.CENTER);
		phaseIconLabel.setVerticalTextPosition(JLabel.CENTER);
		phaseIconLabel.setHorizontalTextPosition(JLabel.CENTER);
		phaseIconLabel.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLoweredBevelBorder(),
			BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		
		phaseResult = new JLabel(); 
 		phaseBlank1 = new JLabel(); 
 		phaseBlank2 = new JLabel();
 		
 		//创建控制按键
 		phaseChoice = new JButton("开始/停止");

		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LotteryDrawMain lot = new LotteryDrawMain();
		
		//set UI
		JFrame frame = new JFrame("抽奖啦");
		
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
