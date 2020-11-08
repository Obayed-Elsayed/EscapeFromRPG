package main;

import java.awt.Dimension;
import java.util.*;
import javax.swing.*;

import levelMaker.LevelManager;


public class MainFrame extends JFrame {

	public GraphicHandler canvas;

	public MainFrame(Integer widthX) {
		Integer heightY = widthX/16*9;
		this.initializeWindow(widthX, heightY);
		canvas = new GraphicHandler(this, widthX, heightY);

		this.addKeyListener(canvas.getPlayer());
		this.add(canvas);
		this.canvas.requestFocus();
		this.requestFocus();
	}

	private void initializeWindow(Integer widthX, Integer heightY) {

		this.setSize(widthX, heightY);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setMinimumSize(new Dimension(widthX, heightY));
		this.setResizable(false);

	}

}
