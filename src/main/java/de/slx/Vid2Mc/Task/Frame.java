package de.slx.Vid2Mc.Task;

import java.awt.image.BufferedImage;

import org.jcodec.common.model.Picture;

public class Frame {
	Picture picture;
	int index;

	Frame(Picture p, int i) {
		picture = p;
		index = i;
	}
}
