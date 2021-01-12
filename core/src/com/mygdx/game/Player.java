package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Player {
    public Rectangle position;
    public String name;
    public Color color;

    public static final float SPEED = 100;
//    public static BitmapFont font = new BitmapFont();

    static {
//        font.setColor(Color.RED);
    }

    public Player() {
    }

    public Player(String name) {
        Random random = new Random();
        this.name = name + random.nextInt(100);
        position = new Rectangle(random.nextInt(100), random.nextInt(100), 10 * 2, 20 * 2);
        color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
    }

    public void draw(Texture texture, SpriteBatch batch) {
//        font.draw(batch, name, position.x - 20, position.y + 60);
        batch.setColor(color);
        batch.draw(texture, position.x, position.y, position.width, position.height);
    }

    public boolean move() {
        boolean move = false;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            move = true;
            position.x += SPEED * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            move = true;
            position.x -= SPEED * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            move = true;
            position.y += SPEED * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            move = true;
            position.y -= SPEED * Gdx.graphics.getDeltaTime();
        }
        return move;
    }

    @Override
    public String toString() {
        return "Player{" +
                "position=" + position +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}
