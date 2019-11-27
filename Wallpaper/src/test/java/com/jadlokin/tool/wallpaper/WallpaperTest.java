package com.jadlokin.tool.wallpaper;

import com.jadlokin.tool.wallpaper.entity.Wallpaper;
import com.jadlokin.tool.wallpaper.util.ImagePathLoader;

import java.awt.*;
import java.io.IOException;
import java.util.Date;

public class WallpaperTest {

	public static void main(String[] args) throws IOException {
		ImagePathLoader imagePathLoader = new ImagePathLoader("E:\\down\\\u597D\u5EB7\u6846\u67B6+\u8FD0\u884C\u65F6\u5E93\\Download\\Cirno\\\u58C1\u7EB8\u6A2A\u56FE");
		Wallpaper wallpaper = new Wallpaper(imagePathLoader,60);
		try {
			Desktop.getDesktop().open(imagePathLoader.randomImagePath().toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
