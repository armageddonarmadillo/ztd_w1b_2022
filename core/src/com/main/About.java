package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class About extends Scene{
    Button btn1;

    About(){
        title = "About";
        font.getData().setScale(5.0f);
        layout.setText(font, title);
        btn1 = new Button("Back to Start", Main.gw / 2 - 150 / 2, 300, 150, 75, Color.SALMON);
    }

    @Override
    void tap(int x, int y) {
        if(btn1.hitbox().contains(x, y)) Main.which = Main.screen.START;
    }

    @Override
    void draw(SpriteBatch b) {
        ScreenUtils.clear(Color.GOLD);
        font.draw(b, layout, (float)Main.gw / 2 - layout.width / 2, 555);
        btn1.draw(b);
    }
}
