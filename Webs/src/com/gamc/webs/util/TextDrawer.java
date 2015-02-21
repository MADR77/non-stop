package com.gamc.webs.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class TextDrawer {

	public static void drawCenteredString(String s, Font font, Color color, int w, int h, Graphics g, int yOffset) {
		g.setColor(color);
	    g.setFont(font);
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2) + yOffset;
	    Color colorB = g.getColor();
	    Font fontB = g.getFont();
	    g.drawString(s, x, y);
	    g.setColor(colorB);
	    g.setFont(fontB);
	  }
	
}
