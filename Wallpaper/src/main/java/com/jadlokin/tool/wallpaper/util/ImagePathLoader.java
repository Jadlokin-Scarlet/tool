package com.jadlokin.tool.wallpaper.util;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@PropertySource("classpath:wallpaper.yml")
public class ImagePathLoader {

	private List<Path> paths = null;
	private static Random random = new Random();

	public ImagePathLoader(@NonNull @Value("${base-image-path}") String baseImagePath) throws IOException {
		this.paths = Files.list(Paths.get(baseImagePath)).collect(Collectors.toList());
	}

	public Path randomImagePath(){
		return paths.get(random.nextInt(paths.size()));
	}

}
