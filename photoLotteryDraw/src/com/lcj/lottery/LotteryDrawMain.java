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

	// ��ʵͼƬ��
	int real_num_images = 0;
	// ͼƬ�����б�
	List<ImageIcon> imageIcons = new ArrayList<ImageIcon>();
	// ͼƬ�����б�
	List<String> imageNames = new ArrayList<String>();
	
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
	private void readImagesToArrays() throws IOException
	{
		//get image directory to put data to it
		String path = new StringBuffer(rootDir).append(IMAGE_DIR).toString();
		
		if(!imageDirExists()){
			throw new IOException(new StringBuffer("ͼƬĿ¼images��ȡʧ�ܣ�").append(path).toString());
		}
		
		//��ȡͼƬ
		File dir = new File(path);
		File[] files = dir.listFiles();
		
		for(int i = 0; i < files.length; i++){
			if(files[i].isDirectory()){
				continue;
			}
			String fileName = files[i].getName();
			
			//����ϵͳ�ļ�
			if(fileName.equals("Thumbs.db") || fileName.startsWith(".")) {
				continue;
			}
			
			//�ļ�����׺��ת��ΪСд,�����ܼ��ݴ�д��׺����
			String suffix= fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			
			//���Է�jpg��ʽ��ͼƬ
			if(!suffix.equals("jpg") && !suffix.equals("JPG")){
				continue;
			}
			
			//����ͼƬ
			ImageIcon icon = null;
			BufferedImage bi = ImageIO.read(files[i]);
			if(bi == null) {
				throw new IOException(new StringBuffer("ImageIO��ȡͼƬ�ļ�ʧ�ܣ�").append(fileName)
						.append(", ����jpgͼƬ��ʽ��").toString());
			}
			icon = new ImageIcon(bi);
			
			//ͼƬ�����
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
			phaseBlank1.setText("��ϲ");
			phaseBlank2.setText("�н�����");
			//delete image of winning the award
			moveLuckImage(phaseResult.getText());
			
			//�ؽ�ͼƬ�ļ��б�
			try {
				readImagesToArrays();
			} catch (IOException e) {
				// TODO: handle exception
				System.err.println(new StringBuffer("�����쳣�˳���" +
						"������ͼƬĿ¼�������⣬����imagesĿ¼�Ƿ���ڣ������Ƿ���ͼƬ : ").append(e));
				System.exit(1);
			}
			
		}
		else{
			run = true;
			new Thread() {
				public void run() {
					while(run) {
						//�����ʾͼƬ
						int index = random(real_num_images);
						phaseIconLabel.setIcon(imageIcons.get(index));
						phaseBlank1.setText("");
						
						//��ʾͼƬ���ƣ���Ҫ��չ��
						try {
							String imageName = imageNames.get(index);
							phaseResult.setText(imageName.substring(0, imageName.lastIndexOf('.')));
							phaseBlank2.setText("");
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							System.err.println(new StringBuffer
									("���ִ��󣬿�����������ͼƬ������ : ").append(e));
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
	 * ɾ��������Ƭ
	 * @param imageName
	 */
	private void moveLuckImage(String imageName) {
		String imageFileName = new StringBuffer(imageName).append(".jpg").toString();
		String srcFile = new StringBuffer(rootDir).append(IMAGE_DIR).append(imageFileName).toString();
		
		File file = new File(srcFile);
		if (file.exists()) {
			//�ȸ����н���Ƭ��winningĿ¼
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
