package com.jadlokin.tool.util;

public class Point extends java.awt.Point {

	public Point(int x, int y) {
		super(x, y);
	}

	public Point clone() {
		return (Point) super.clone();
	}

	public Point translate(Point point) {
		super.translate(point.x, point.y);
		return this;
	}

	public Point translateToNewPoint(Point point) {
		return this.clone().translate(point);
	}

	public Point translateToNewPoint(int dx, int dy) {
		return this.translateToNewPoint(new Point(dx, dy));
	}
}
