package dc.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class FrameUtils {

	/**
	 * 初始化窗口
	 * @param frame 窗口实例
	 * @param width 窗口宽度
	 * @param height 窗口高度
	 */
	public static void initFrame(JFrame frame,int width , int height){
		// 获取一个与系统相关工具类对象
		Toolkit toolkit = Toolkit.getDefaultToolkit(); 
		// 获取屏幕的分辨率
		Dimension d = toolkit.getScreenSize();
		// 获取到屏幕的宽度
		int x = (int) d.getWidth();
		// 获取到屏幕的高度
		int y = (int) d.getHeight();
		// 设置窗口的大小
		frame.setBounds((x-width)/2, (y-height)/2, width, height);
		// 显示窗口
		frame.setVisible(true);
		// 关闭窗口，退出Java虚拟机
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
