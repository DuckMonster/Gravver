package com.emilstrom.gravver.game.entity;

import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-02.
 */
public class Entity {
	Vertex position;

	public Entity() {
		position = new Vertex();
	}
	public Entity(Vertex pos) {
		position = new Vertex(pos);
	}

	public void logic() {
	}

	public void draw() {
	}
}
