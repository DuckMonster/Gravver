package com.emilstrom.gravver.game;

import com.emilstrom.gravver.game.effect.Effect;
import com.emilstrom.gravver.game.entity.Actor;
import com.emilstrom.gravver.game.entity.Debree;
import com.emilstrom.gravver.game.entity.Player;
import com.emilstrom.gravver.helper.GameMath;
import com.emilstrom.gravver.helper.Timer;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-02.
 */
public class Map {
	Timer debreeSpawnTimer = new Timer(5f, true);

	public Player player;
	public Debree debreeList[] = new Debree[30];
	int debreeListIndex = 0;

	Effect effectList[] = new Effect[50];
	int effectListIndex = 0;

	public Map() {
		player = new Player(this);
	}

	public void spawnDebree() {
		int spawnIndex = debreeListIndex;

		while(debreeList[spawnIndex] != null)
			spawnIndex++;

		Vertex spawnPosition = new Vertex(
				(float)GameMath.getRndDouble(-Game.currentGame.gameWidth/2, Game.currentGame.gameWidth/2),
				Game.currentGame.gameHeight/2 + 2f
		);

		debreeList[spawnIndex] = new Debree(this, spawnPosition, player);
	}

	public Actor getCollision(Actor caller, Vertex position, float size) {
		if (caller != player && player.getCollision(position, size)) return player;
		for(Debree d : debreeList) {
			if (d != null && d != caller && d.getCollision(position, size)) return d;
		}

		return null;
	}

	public void addEffect(Effect e) {
		effectList[effectListIndex] = e;
		effectListIndex = (effectListIndex + 1) % effectList.length;
	}

	public void logic() {
		player.logic();

		debreeSpawnTimer.logic();

		if (debreeSpawnTimer.isDone()) {
			spawnDebree();
			debreeSpawnTimer.reset();
		}

		for(Debree d : debreeList) if (d != null) d.logic();
		for(Effect e : effectList) if (e != null) e.logic();
	}

	public void draw() {
		player.draw();
		for(Debree d : debreeList) if (d != null) d.draw();
		for(Effect e : effectList) if (e != null) e.draw();
	}
}