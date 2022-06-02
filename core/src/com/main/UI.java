package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UI {
    static BitmapFont font = new BitmapFont();
    static int money, life, score, wave;

    static void draw(SpriteBatch b){
        font.getData().setScale(1.5f);
        font.setColor(Color.DARK_GRAY);
        font.draw(b, "Money: " + money, 6, 594);
        font.setColor(Color.GOLD);
        font.draw(b, "Money: " + money, 5, 595);
        font.setColor(Color.DARK_GRAY);
        font.draw(b, "Life: " + life, 6, 574);
        font.setColor(Color.LIME);
        font.draw(b, "Life: " + life, 5, 575);
        font.setColor(Color.DARK_GRAY);
        font.draw(b, "Score: " + score, 6, 554);
        font.setColor(Color.CYAN);
        font.draw(b, "Score: " + score, 5, 555);
        font.setColor(Color.DARK_GRAY);
        font.draw(b, "Wave: " + wave, 6, 534);
        font.setColor(Color.SALMON);
        font.draw(b, "Wave: " + wave, 5, 535);
    }
}
