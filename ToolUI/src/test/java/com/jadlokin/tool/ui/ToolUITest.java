package com.jadlokin.tool.ui;

import com.jadlokin.tool.ui.frame.MainFrame;
import com.jadlokin.tool.ui.listener.MoveListener;
import com.jadlokin.tool.wallpaper.entity.Wallpaper;

public class ToolUITest {

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame(
				null,
				1800,
				-130,
				"/img/Foot.png"
		);
		new MoveListener(mainFrame);
	}

}
