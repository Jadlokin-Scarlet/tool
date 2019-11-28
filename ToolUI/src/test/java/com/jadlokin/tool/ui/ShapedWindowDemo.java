package com.jadlokin.tool.ui;
import com.jadlokin.tool.util.Image;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class ShapedWindowDemo extends JFrame {

	private Image image = new Image("/img/Foot.png");

	private ShapedWindowDemo() {
		super("ShapedWindow");
		setLayout(new GridBagLayout());

		// It is best practice to set the window's shape in
		// the componentResized method.  Then, if the window
		// changes size, the shape will be correctly recalculated.
		addComponentListener(new ComponentAdapter() {
			// Give the window an elliptical shape.
			// If the window is resized, the shape is recalculated here.
			@Override
			public void componentResized(ComponentEvent e) {
				setShape(image.toShape());
			}
		});

		setUndecorated(true);
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new JButton("I am a Button"));
		setOpacity(0.7f);
		// Display the window.
		setVisible(true);
	}

	public static void main(String[] args) {
		new ShapedWindowDemo();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image.toBufferImage(), 0, 0, null);
	}
}