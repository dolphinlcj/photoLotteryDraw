package com.lcj.lottery;

import java.util.Random;

/*
 * ³é½±Ëæ»úËã·¨
 * @author dolphinlcj
 */
public class GameMath {
	private Random random;
	
	public GameMath() {
		this.random = null;
		this.random = new Random();
	}
	
	public int RandRange(int min, int max) {
		int randomNum = (int)Math.floor(this.random.nextFloat()
				* (max - min + 1))
				+ min;
		
		return randomNum;
	}
	
	public int getOrder(int min, int max, int pre) {
		int orderInt = pre;
		
		if(pre < max) { 
			orderInt = pre + 1;
		}
		else {
			orderInt = min;
		}
		return orderInt;
	
	}
}
