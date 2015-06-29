/**
 * This file created at 2010-10-13.
 *
 * Copyright (c) 2002-2010 cloudmastersoft, Inc. All rights reserved.
 */
package com.bing;

import java.util.Map;
import java.util.Random;

/**
 * <code>{@link ValidateCodeUtil}</code>
 * 
 * TODO : document me
 * 
 * @author yohn
 */
public class ValidateCodeUtil {

	private final static char[] codeSequence = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9' };
	private final static char[] calcSequence = { '+','-' };
	private final static Random random = new Random();

	public static String genRandomCode(int codeCount) {

		StringBuffer ret = new StringBuffer();
		for (int i = 0; i < codeCount; i++) {
			ret.append(String.valueOf(codeSequence[random.nextInt(10)]));
		}

		return ret.toString();

	}

	public static String[] genRandomCalculateCode() {

		StringBuffer start = new StringBuffer();
		for (int i = 0; i < 2; i++) {
			start.append(String.valueOf(codeSequence[random.nextInt(10)]));
		}
		StringBuffer end = new StringBuffer();
		for (int i = 0; i < 2; i++) {
			end.append(String.valueOf(codeSequence[random.nextInt(10)]));
		}
		int startNum = Integer.valueOf(start.toString());
		int endNum = Integer.valueOf(end.toString());
		
		int i=random.nextInt(2);
		int calcRes;
		if(i==0){
			calcRes=startNum+endNum;
		}else{
			if (startNum < endNum) {
				int temp=startNum;
				startNum=endNum;
				endNum=temp;
			}
			calcRes=(startNum-endNum);
			
		}
		
		String[] res={ startNum+String.valueOf(calcSequence[i])+endNum+"=",String.valueOf(calcRes)};
		return res;
	}

}
