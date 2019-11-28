package com.jadlokin.tool.ui.frame;

import com.jadlokin.tool.ui.listener.MainFrameComponentListener;
import com.jadlokin.tool.ui.listener.MoveListener;
import com.jadlokin.tool.util.Image;
import com.jadlokin.tool.util.Point;
import com.jadlokin.tool.wallpaper.entity.Wallpaper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

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
					 @Value("${main-image-path}") String backgroundImagePath,
					 @Lazy MainFrameComponentListener mainFrameComponentListener,
					 @Lazy MoveListener moveListener,
					 ImagePanel imagePanel
	) {
		super("ShapedWindow");
		this.wallpaper = wallpaper;
		ORIGIN = new Point(x0, y0);
		backgroundImage = new Image(backgroundImagePath);

		initFrame();
		moveToOrigin();

		addComponentListener(mainFrameComponentListener);
		addMouseListener(moveListener);
		addMouseMotionListener(moveListener);

		add(imagePanel);
//		add(new JButton("I am a Button"));
		setVisible(true);
	}

	public void updateWallpaper() {
		new Thread(wallpaper::updateWallpaper).start();
	}

	public void close() {
		System.exit(0);
	}

	public void openImage() {
		wallpaper.openImage();
	}

	private void initFrame() {
		//置顶
		setAlwaysOnTop(true);
		setType(java.awt.Window.Type.UTILITY);
		//布局
//		setLayout(new GridBagLayout());
		setLayout(null);
		//设定禁用窗体装饰，这样就取消了默认的窗体结构
		setUndecorated(true);
		//设定窗体大小和图片一样大
		setSize(backgroundImage.getWidth(), backgroundImage.getHeight());
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

	public Image getBackgroundImage() {
		return backgroundImage;
	}
}
