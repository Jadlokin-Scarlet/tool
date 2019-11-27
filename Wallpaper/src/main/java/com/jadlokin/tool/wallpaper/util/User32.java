package com.jadlokin.tool.wallpaper.util;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class User32 {
	static {
		System.setProperty("jna.encoding", "GBK");
	}

	private interface MyUser32 extends StdCallLibrary {
		MyUser32 INSTANCE = (MyUser32) Native.loadLibrary("user32", MyUser32.class);
		boolean SystemParametersInfoA(int uiAction, int uiParam, String fnm, int fWinIni);
	}

	public static boolean setWallpaper(String path) {
		int SPI_SETDESKWALLPAPER = 0x0014;
		int SPIF_UPDATEINIFILE = 0x0001;
		int SPIF_SENDWININICHANGE = 0x0002;
//		new Thread(()->{
		log.info("update to " + path);
		return MyUser32.INSTANCE.SystemParametersInfoA(SPI_SETDESKWALLPAPER, 1, path, SPIF_UPDATEINIFILE |
				SPIF_SENDWININICHANGE);
//		}).start();
	}
}
