package com.jadlokin.tool.wallpaper.entity;

import com.jadlokin.tool.wallpaper.util.ImagePathLoader;
import com.jadlokin.tool.wallpaper.util.User32;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;

@Slf4j
@Component
@PropertySource("classpath:wallpaper.yml")
public class Wallpaper {
	private final Integer cycle;

	private Instant updateTime;

	private Path nowImagePath;
	private ImagePathLoader imagePathLoader;

	@Autowired
	public Wallpaper(@NonNull ImagePathLoader imagePathLoader,
					 @NonNull @Value("${cycle}")Integer cycle) {
		this.imagePathLoader = imagePathLoader;
		this.cycle = cycle;
		updateTime = Instant.now().minusSeconds(cycle);
		new Timer().schedule(() -> {
			Instant lastUpdateTime = updateTime.plusSeconds(cycle);
			if (lastUpdateTime.isBefore(Instant.now())) {
				updateWallpaper();
			}
		}, cycle / 2);
	}

	public synchronized void updateWallpaper() {
		Path path = imagePathLoader.randomImagePath();
		boolean success = User32.setWallpaper(path.toString());
		if (success) {
			this.nowImagePath = path;
		} else {
			log.warn("壁纸更新失败");
		}
		updateTime = this.updateTime.plusSeconds(cycle);
	}

	public void openImage() {
		try {
			Desktop.getDesktop().open(nowImagePath.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
