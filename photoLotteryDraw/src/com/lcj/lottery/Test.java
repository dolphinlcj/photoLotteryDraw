package com.lcj.lottery;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class Test {

	public static void choose() {
		String path = null;
		// ���ý�����
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFileChooser jdir = new JFileChooser();
		// ����ѡ��·��ģʽ
		jdir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// ���öԻ������
		jdir.setDialogTitle("��ѡ��·��");
		if (JFileChooser.APPROVE_OPTION == jdir.showOpenDialog(null)) {// �û������ȷ��
			path = jdir.getSelectedFile().getAbsolutePath();// ȡ��·��ѡ��
		}
		System.out.println(path);
	}

	public static void main(String[] args) {
		Test.choose();
	}

}
