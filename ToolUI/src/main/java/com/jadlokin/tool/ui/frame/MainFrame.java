package com.jadlokin.tool.ui.frame;

import com.jadlokin.tool.ui.listener.MoveListener;
import com.jadlokin.tool.util.Image;
import com.jadlokin.tool.util.Point;
import com.jadlokin.tool.wallpaper.entity.Wallpaper;
import com.sun.awt.AWTUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.io.IOException;

@Slf4j
@Component
@PropertySource("classpath:tool-ui.yml")
public class MainFrame extends JFrame {
	public final Point ORIGIN;
	private Wallpaper wallpaper;
	private Image backgroundImage;

	@Autowired
	public MainFrame(Wallpaper wallpaper,
					 @Value("${x0}") Integer x0,
					 @Value("${y0}") Integer y0,
					 @Value("${main-image-path}") String backgroundImagePath) {
		log.info("frame start to init");
		this.wallpaper = wallpaper;
		ORIGIN = new Point(x0, y0);
		initFrame(backgroundImagePath);
	}

	public void updateWallpaper() {
		new Thread(wallpaper::updateWallpaper).start();
	}

	public void close() {
		System.exit(0);
	}

	public void openImage() throws IOException{
		wallpaper.openImage();
	}

	private void initBackgroundImage() {
		Shape shape = new Area(new Rectangle(0,0,1,1));
		this.setSize(0, 0);
	}

	private void initBackgroundImage(String backgroundImagePath) {
		backgroundImage = new Image(backgroundImagePath);
		Shape shape = backgroundImage.toShape();
		//设定窗体大小和图片一样大
		this.setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
		//调用AWTUtilities的setWindowShape方法设定本窗体为制定的Shape形状
		AWTUtilities.setWindowShape(this,shape);
	}

	private void initFrame(String backgroundImagePath) {
		AWTUtilities.setWindowOpacity(this, 1.0f);
		//设定禁用窗体装饰，这样就取消了默认的窗体结构
		this.setUndecorated(true);
		//置顶
		setAlwaysOnTop(true);
		setType(java.awt.Window.Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initBackgroundImage(backgroundImagePath);
		moveToOrigin();

		setVisible(true);
	}

	/* 我们可以选择在窗体上绘制图片，是窗体完全呈现出图片的样式，
	 * 当然我们也可以根据需要不绘制它，而采取其他操作
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backgroundImage.toBufferImage(), 0, 0, null);
	}

	public void moveToOrigin() {
		moveTo(ORIGIN.clone());
	}

	public void moveTo(Point point) {
		setLocation(point);
	}

}
