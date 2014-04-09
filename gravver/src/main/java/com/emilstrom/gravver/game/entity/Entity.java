package com.emilstrom.gravver.game.entity;

import com.emilstrom.gravver.game.Map;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-02.
 */
public class Entity {
	Map map;
	Vertex position;

	public Entity(Map m) {
		map = m;
		position = new Vertex();
	}
	public Entity(Map m, Vertex pos) {
		map = m;
		position = new Vertex(pos);
	}

	public void logic() {
	}

	public void draw() {
	}
}
