package com.jadlokin.tool.ui.frame;

import com.jadlokin.tool.util.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Slf4j
@Component
public class ImagePanel extends JPanel {

	private Image backgroundImage;

	@Autowired
	public ImagePanel(
			@Value("${image-panel-image-path}")String imagePanelImagePath
	) {
		backgroundImage = new Image(imagePanelImagePath);
//		setBackground(null);
//		setOpaque(false);

//		setLayout(null);
		setBounds(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());

//		setBackground(Color.CYAN);
//		JLabel lab = new JLabel(new ImageIcon(imagePanelImage.toBufferImage()));
//		lab.setBounds(0, 0, imagePanelImage.getWidth(), imagePanelImage.getHeight());
//		add(lab);

//		setVisible(true);
	}




	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backgroundImage.toBufferImage(), 0, 0, null);
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}
}
