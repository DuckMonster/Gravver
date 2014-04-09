package com.emilstrom.gravver.game;

import com.emilstrom.gravver.helper.Color;
import com.emilstrom.gravver.helper.Input;
import com.emilstrom.gravver.helper.InputHelper;
import com.emilstrom.gravver.helper.Sprite;
import com.emilstrom.gravver.helper.Vertex;

/**
 * Created by Emil on 2014-04-02.
 */
public class PlayerController {
	static Vertex velocityButtonPosition = new Vertex(0,0), velocityButtonOffset = new Vertex(-0.75f, 1.27f),
				gravityButtonPosition = new Vertex(0,0), gravityButtonOffset = new Vertex(0.25f, 0.5f), gravityButtonSize = new Vertex(2.8f, 2.8f);
	static float velocityButtonRadius = 2f;

	Sprite velocityRingSprite, gravityButtonSprite;
	Input oldInput;

	int velocityFinger = -1;
	float velocityIndicatorAlpha = 0f;

	Vertex velocityPosition = new Vertex(0,0);

	boolean gravityButtonPressed = false;

	public PlayerController() {
		velocityRingSprite = new Sprite(Art.ring);
		gravityButtonSprite = new Sprite(Art.blank);
	}

	public Vertex getVelocityInput() {
		return new Vertex(velocityPosition);
	}
	public boolean getGravityInput() { return gravityButtonPressed; }
	public boolean getGravityButtonCollision(Vertex v) {
		return (v.x >= gravityButtonPosition.x - gravityButtonSize.x/2 &&
				v.x < gravityButtonPosition.x + gravityButtonSize.x/2 &&
				v.y >= gravityButtonPosition.y - gravityButtonSize.y/2 &&
				v.y < gravityButtonPosition.y + gravityButtonSize.y/2);
	}

	public void logic() {
		Input newInput = InputHelper.getInput();
		if (oldInput == null) oldInput = newInput;

		gravityButtonPressed = false; 		//Reset the gravity button

		//Check for presses
		for(int i=0; i<Input.NMBR_OF_FINGERS; i++) {
			if (velocityFinger == -1 && newInput.isPressed(i) && !oldInput.isPressed(i)) {
				if (Vertex.getLength(velocityButtonPosition, newInput.getPosition(i)) <= velocityButtonRadius) velocityFinger = i;
			}
			if (newInput.isPressed(i) && getGravityButtonCollision(newInput.getPosition(i))) gravityButtonPressed = true;
		}

		//Move the velocity indicator
		Vertex targetPos = new Vertex(0,0);

		if (velocityFinger != -1) {
			if (!newInput.isPressed(velocityFinger)) velocityFinger = -1;
			else {
				float distance = Math.min(Vertex.getLength(velocityButtonPosition, newInput.getPosition(velocityFinger)) / velocityButtonRadius, 1);
				Vertex dir = Vertex.getDirectionVertex(velocityButtonPosition, newInput.getPosition(velocityFinger));

				targetPos = dir.times(distance);
			}
		}

		velocityPosition.add(targetPos.minus(velocityPosition).times(Game.updateTime * 8));

		if (velocityFinger == -1) velocityIndicatorAlpha -= Game.updateTime * 1.3f;
		else velocityIndicatorAlpha = 1f;

		oldInput = newInput;
	}

	public void draw() {
		velocityRingSprite.setColor(Color.WHITE);
		velocityRingSprite.draw(velocityButtonPosition, new Vertex(velocityButtonRadius * 2, velocityButtonRadius * 2), 0);

		velocityRingSprite.setColor(new Color(1f, 1f, 1f, velocityIndicatorAlpha));
		velocityRingSprite.draw(velocityButtonPosition.plus(velocityPosition.times(velocityButtonRadius)), new Vertex(3, 3), 0);

		gravityButtonSprite.setColor(new Color(1f, 1f, 1f, gravityButtonPressed ? 1f : 0.4f));
		gravityButtonSprite.draw(gravityButtonPosition, gravityButtonSize, 0);
	}

	public static void recalculatePosition() {
		float x = Game.currentGame.gameWidth / 2 - velocityButtonRadius,
				y = -Game.currentGame.gameHeight / 2 + velocityButtonRadius;

		velocityButtonPosition = new Vertex(x, y);
		velocityButtonPosition.add(velocityButtonOffset);

		x = -Game.currentGame.gameWidth / 2 + gravityButtonSize.x;
		y = -Game.currentGame.gameHeight / 2 + gravityButtonSize.y;

		gravityButtonPosition = new Vertex(x, y);
		gravityButtonPosition.add(gravityButtonOffset);
	}
}
