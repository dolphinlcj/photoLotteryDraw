package com.lcj.lottery.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class CopyFile {

	/**
	 * @param args
	 * @throws IOException 
	 * 
	 */
	public static long copy(String srcFile, String dstFile) throws IOException {
		long copySizes = 0;
		
		FileChannel fcin = null;
		FileChannel fcout = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(srcFile);
			fcin = fis.getChannel();
			fos = new FileOutputStream(dstFile);
			fcout = fis.getChannel();
			long size = fcin.size();
			fcin.transferTo(0, fcin.size(), fcout);
			copySizes = size;
		}finally{
			fcout.close();
			fos.close();
			fcin.close();
			fis.close();
		} 
		
		return copySizes;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String srcFile = "/Users/litao/Downloads/swf_flv_player.dmg";
		String dstFile = "/Users/litao/Desktop/aa.dmg";
		
		long rest = 0L;
		try {
			rest = CopyFile.copy(srcFile, dstFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result:" + rest);
	}

}
