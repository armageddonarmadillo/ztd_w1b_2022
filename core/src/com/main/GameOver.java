package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOver extends Scene{
    Button btn1, btn2;

    GameOver(){
        title = "Game Over";
        font.getData().setScale(5.0f);
        layout.setText(font, title);
        btn1 = new Button("Play Again", Main.gw / 2 - 150 / 2, 300, 150, 75, Color.SALMON);
        btn2 = new Button("Back to Start", Main.gw / 2 - 150 / 2, 200, 150, 75, Color.SALMON);
    }

    @Override
    void tap(int x, int y) {
        if(btn1.hitbox().contains(x, y)) { Main.which = Main.screen.PLAY; Main.game = new Game();}
        if(btn2.hitbox().contains(x, y)) Main.which = Main.screen.START;
    }

    @Override
    void draw(SpriteBatch b) {
        ScreenUtils.clear(Color.GOLD);
        font.draw(b, layout, (float)Main.gw / 2 - layout.width / 2, 555);
        btn1.draw(b);
        btn2.draw(b);
    }
}
