package com.lcj.lottery;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lcj.lottery.util.CopyFile;

/**
 * @version 1.0
 * @param dolphinlcj
 */
public class LotteryDrawMain {

	// 真实图片数
	int real_num_images = 0;
	// 图片对象列表
	List<ImageIcon> imageIcons = new ArrayList<ImageIcon>();
	// 图片名称列表
	List<String> imageNames = new ArrayList<String>();
	
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
	private void readImagesToArrays() throws IOException
	{
		//get image directory to put data to it
		String path = new StringBuffer(rootDir).append(IMAGE_DIR).toString();
		
		if(!imageDirExists()){
			throw new IOException(new StringBuffer("图片目录images读取失败：").append(path).toString());
		}
		
		//读取图片
		File dir = new File(path);
		File[] files = dir.listFiles();
		
		for(int i = 0; i < files.length; i++){
			if(files[i].isDirectory()){
				continue;
			}
			String fileName = files[i].getName();
			
			//忽略系统文件
			if(fileName.equals("Thumbs.db") || fileName.startsWith(".")) {
				continue;
			}
			
			//文件名后缀（转换为小写,否则不能兼容大写后缀名）
			String suffix= fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			
			//忽略非jpg格式的图片
			if(!suffix.equals("jpg") && !suffix.equals("JPG")){
				continue;
			}
			
			//读入图片
			ImageIcon icon = null;
			BufferedImage bi = ImageIO.read(files[i]);
			if(bi == null) {
				throw new IOException(new StringBuffer("ImageIO读取图片文件失败：").append(fileName)
						.append(", 不是jpg图片格式？").toString());
			}
			icon = new ImageIcon(bi);
			
			//图片入队列
			imageIcons.add(icon);
			imageNames.add(fileName);
		}
		real_num_images = imageIcons.size();
	}
	
	private boolean imageDirExists() {
		boolean res = false;
		File dir = new File(new StringBuffer(rootDir).append(IMAGE_DIR)
				.toString());
		if(dir.exists() && dir.isDirectory()){
			res = true;
		}
		
		return res;
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
		phaseChoice.addActionListener((ActionListener) this);
	}
	
	 
	private int random(int max) {
		return (int)(Math.random() * real_num_images);
	}
	
	boolean run = false;
	public void actionPerformed(ActionEvent event) throws IOException
	{
		if(run) {
			run = false;
			phaseBlank1.setText("恭喜");
			phaseBlank2.setText("中奖啦！");
			//delete image of winning the award
			moveLuckImage(phaseResult.getText());
			
			//重建图片文件列表
			try {
				readImagesToArrays();
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println(new StringBuffer("程序异常退出，" +
						"可能是图片目录出现问题，请检查images目录是否存在，其中是否有图片 : ").append(e));
				System.exit(1);
			}
			
		}
		else{
			run = true;
			new Thread() {
				public void run() {
					while(run) {
						//随机显示图片
						int index = random(real_num_images);
						phaseIconLabel.setIcon(imageIcons.get(index));
						phaseBlank1.setText("");
						
						//显示图片名称，不要扩展名
						try {
							String imageName = imageNames.get(index);
							phaseResult.setText(imageName.substring(0, imageName.lastIndexOf('.')));
							phaseBlank2.setText("");
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							System.err.println(new StringBuffer
									("出现错误，可能是随机抽的图片不存在 : ").append(e));
							System.exit(1);
						}
						try {
							Thread.sleep(DURATION);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
											
					}
				}
			}.start();
			
		}
	}
	/**
	 * 删除获奖人照片
	 * @param imageName
	 */
	private void moveLuckImage(String imageName) {
		String imageFileName = new StringBuffer(imageName).append(".jpg").toString();
		String srcFile = new StringBuffer(rootDir).append(IMAGE_DIR).append(imageFileName).toString();
		
		File file = new File(srcFile);
		if (file.exists()) {
			//先复制中奖照片到winning目录
			String dstFile = srcFile.replaceAll(IMAGE_DIR, WINNER_DIR);
			try {
				CopyFile.copy(srcFile, dstFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file.delete();
		}
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
