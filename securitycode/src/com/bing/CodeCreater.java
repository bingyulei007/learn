package com.bing;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;


public class CodeCreater {
	// 验证码图片的宽度。
		private int width = 80;
		// 验证码图片的高度。
		private int height = 35;
		// 验证码字符个数
		private int codeCount = 4;

		private int x = 0;
		// 字体高度
		private int fontHeight;
		private int codeY;
		private final static Random random = new Random();
		public void init()  {
			
			x = width / (codeCount + 2);
			fontHeight = height - 2;
			codeY = height - 4;
			fontHeight = height - 2;

		}
	public Map<String, Object> createCode(CodeType type) throws IOException  {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		init();
		// 定义图像buffer
				BufferedImage buffImg = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g = buffImg.createGraphics();

				// 将图像填充为白色
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, width, height);

				// 创建字体，字体的大小应该根据图片的高度来定。
				Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
				// 设置字体。
				g.setFont(font);

				// 画边框。
				g.setColor(Color.gray);
				g.drawRect(0, 0, width - 1, height - 1);

				// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
				g.setColor(getRandColor(160, 200));
				for (int i = 0; i < 155; i++) {
					int x = random.nextInt(width);
					int y = random.nextInt(height);
					int xl = random.nextInt(12);
					int yl = random.nextInt(12);
					g.drawLine(x, y, x + xl, y + yl);
				}

				// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
				int red = 0, green = 0, blue = 0;

				// 随机产生codeCount数字的验证码。
				String randomCode=null;
				String codeCopy=null;
				if(type.equals(CodeType.RANDOM_NUM)){
				   randomCode = ValidateCodeUtil.genRandomCode(codeCount);
				   codeCopy=randomCode;
				}
				else if(type.equals(CodeType.CALCULATE)){
					 String[] res  = ValidateCodeUtil.genRandomCalculateCode();
					   randomCode=res[0];
						codeCopy=res[1];
				}
				if(randomCode==null){
					throw new IllegalStateException("验证码类型错误");
				}
				for (int i = 0; i < randomCode.length(); i++) {
					// 得到随机产生的验证码数字。
					String strRand = String.valueOf(randomCode.charAt(i));
					// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
					red = 20 + random.nextInt(110);
					green = 20 + random.nextInt(110);
					blue = 20 + random.nextInt(110);

					// 用随机产生的颜色将验证码绘制到图像中。
					g.setColor(new Color(red, green, blue));
					g.drawString(strRand,(i + 1) * x, codeY);

				}
				
			
					ImageIO.write(buffImg, "jpeg",bos);
					byte[] byteArr = bos.toByteArray();
					bos.close();
					Map<String, Object> map=new HashMap<>();
					map.put("code", codeCopy);
					map.put("img", byteArr);
					return map;
				
				
				
	}
	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}


}
