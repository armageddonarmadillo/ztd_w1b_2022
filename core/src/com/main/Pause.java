package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Pause extends Scene{
    Button btn1, btn2, btn3;

    Pause(){
        title = "Pause Menu";
        font.getData().setScale(5.0f);
        layout.setText(font, title);
        btn1 = new Button("Resume", Main.gw / 2 - 150 / 2, 300, 150, 75, Color.SALMON);
        btn2 = new Button("Restart", Main.gw / 2 - 150 / 2, 200, 150, 75, Color.OLIVE);
        btn3 = new Button("Back to Main Menu", Main.gw / 2 - 150 / 2, 100, 150, 75, Color.SLATE);
    }

    @Override
    void tap(int x, int y) {
        if(btn1.hitbox().contains(x, y)) Main.which = Main.screen.PLAY;
        if(btn2.hitbox().contains(x, y)) { Main.which = Main.screen.PLAY; Main.game = new Game();}
        if(btn3.hitbox().contains(x, y)) Main.which = Main.screen.START;
    }

    @Override
    void draw(SpriteBatch b) {
        b.draw(Resources.violet, 0, 0, Main.gw, Main.gh);
        font.draw(b, layout, (float)Main.gw / 2 - layout.width / 2, 475);
        btn1.draw(b);
        btn2.draw(b);
        btn3.draw(b);
    }
}
