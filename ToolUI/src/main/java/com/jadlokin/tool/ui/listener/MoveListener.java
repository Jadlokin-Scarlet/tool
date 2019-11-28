package com.jadlokin.tool.ui.listener;

import com.jadlokin.tool.ui.frame.MainFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

@Component
public class MoveListener implements MouseMotionListener, MouseListener {

	private MainFrame mainFrame;

	private Point mouseOriginPoint = new Point();

	@Autowired
	public MoveListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3){
			mainFrame.close();
		}else if(e.getButton()==MouseEvent.BUTTON1){
			mainFrame.openImage();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseOriginPoint = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(getDy(e) > 5){
			mainFrame.updateWallpaper();
		}
		mainFrame.moveToOrigin();

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mainFrame.moveTo(mainFrame.ORIGIN.translateToNewPoint(0, getDy(e)));
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	private int getDy(MouseEvent e) {
		return (int)Math.sqrt(e.getY() - mouseOriginPoint.y);
	}

}
