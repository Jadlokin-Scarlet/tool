package com.jadlokin.tool.ui.listener;

import com.jadlokin.tool.ui.frame.ImagePanel;
import com.jadlokin.tool.ui.frame.MainFrame;
import com.jadlokin.tool.util.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Area;

@Component
public class MainFrameComponentListener implements ComponentListener {

	private MainFrame mainFrame;
	private ImagePanel imagePanel;

//	@Autowired
	public MainFrameComponentListener(MainFrame mainFrame, ImagePanel imagePanel) {
		this.mainFrame = mainFrame;
		this.imagePanel = imagePanel;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Area area = mainFrame.getBackgroundImage().getShapeArea();
		Area ImagePanelArea = imagePanel.getBackgroundImage().getShapeArea();
		area.add(ImagePanelArea);
		mainFrame.setShape(area);
	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}
}
