package com.emilstrom.gravver.helper;

/**
 * Created by Emil on 2014-02-19.
 */
public class Input {
	public static final int NMBR_OF_FINGERS = 5;

	private boolean pressed[] = new boolean[NMBR_OF_FINGERS];
	private Vertex position[] = new Vertex[NMBR_OF_FINGERS];

	public Input(float xx[], float yy[], boolean p[]) {
		for(int i=0; i<position.length; i++) {
			position[i] = new Vertex(xx[i], yy[i]);
			pressed[i] = p[i];
		}
	}

	public boolean isPressed(int id) { return pressed[id]; }
	public Vertex getPosition(int id) { return new Vertex(position[id]); }
}