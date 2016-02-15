package ru.bmstu.rk9.rao.ui.process.advance;

import java.io.Serializable;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;

import ru.bmstu.rk9.rao.ui.process.NodeWithProperty;

public class Advance extends NodeWithProperty implements Serializable {

	private static final long serialVersionUID = 1;
	
	public Advance() {
		super(backgroundColor.getRGB());
	}

	private static Color backgroundColor = ColorConstants.green;
	public static String name = "Advance";

}
