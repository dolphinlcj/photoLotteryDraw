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
	
	// 抽奖控制按键
	JButton phaseChoice = null;
	// 显示结果标签
	JLabel phaseIconLabel = null, phaseResult = null, phaseBlank1 = null,
				phaseBlank2 = null;
	//工作目录
	String rootDir = new StringBuffer(System.getProperty("user.dir")).
			append(File.separator).toString();
	
	//图片目录
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
		phaseChoice = new JButton("开始/停止");
		
		//display the first welcome photo
		ImageIcon icon = null;
		String fileName = new StringBuffer(rootDir).append(WELCOME_IMAGE).toString();
		
		try {
			icon = new ImageIcon(ImageIO.read(new File(fileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException(new StringBuffer("ImageIO读取图片文件失败 : ")
			.append(fileName).append(", 不是jpg图片格式？").toString());
		}
		
		phaseIconLabel.setIcon(icon);
		phaseIconLabel.setText("");
		
		// 各区域设置边框
		selectPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder("操作区"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		resultPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder("结果区"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		// 图片区域设置边框
		displayPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder("照片列表"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		//显示板上添加各组件
		selectPanel.add(phaseChoice);
		displayPanel.add(phaseIconLabel);
		resultPanel.add(phaseBlank1);
		resultPanel.add(phaseResult);
		resultPanel.add(phaseBlank2);
		
		//给控制按键设置监听器
		//phaseChoice.addActionListener(this);
		
		
		
	}
	public static void main(String[] args) throws IOException {
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
