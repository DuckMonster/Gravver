package com.emilstrom.gravver.game;

import com.emilstrom.gravver.game.entity.Debree;
import com.emilstrom.gravver.game.entity.Player;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-02.
 */
public class Map {
	public Player player;
	public Debree debree;

	public Map() {
		player = new Player();
		debree = new Debree(new Vertex(2,0), player);
	}

	public void logic() {
		player.logic();
		debree.logic();
	}

	public void draw() {
		player.draw();
		debree.draw();
	}
}