package com.lcj.lottery.util;

/*
 * 判断String是否为可转化为数字的字符串
 */
public class Tools {
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
}
