package com.jadlokin.tool.util;

public class PointTest {

	public static void main(String[] args) {
		final Point point = new Point(0, 0);
		Point point1 = point.translate(new Point(1,0));
		Point point2 = point1.clone();

	}

}
