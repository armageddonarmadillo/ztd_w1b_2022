package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Start extends Scene{
    Button btn1, btn2, btn3;


    Start(){
        title = "Towerless Defense Game";
        font.getData().setScale(5.0f);
        layout.setText(font, title);
        btn1 = new Button("Start", Main.gw / 2 - 150 / 2, 300, 150, 75, Color.SALMON);
        btn2 = new Button("About", Main.gw / 2 - 150 / 2, 200, 150, 75, Color.OLIVE);
        btn3 = new Button("Achievements", Main.gw / 2 - 150 / 2, 100, 150, 75, Color.SLATE);
    }

    @Override
    void tap(int x, int y) {
        Game.effects.add(new Effect("shock_purple", x, y));

        if(btn1.hitbox().contains(x, y)) { Main.which = Main.screen.PLAY; Main.game = new Game();}
        if(btn2.hitbox().contains(x, y)) Main.which = Main.screen.ABOUT;
        if(btn3.hitbox().contains(x, y)) Main.which = Main.screen.ACHIEVEMENTS;
    }

    @Override
    void draw(SpriteBatch b) {
        ScreenUtils.clear(Color.LIME);
        font.draw(b, layout, (float)Main.gw / 2 - layout.width / 2, 555);
        btn1.draw(b);
        btn2.draw(b);
        btn3.draw(b);
        for(Effect e : Game.effects) e.draw(b);
        for (Effect e : Game.effects) if (!e.active) { Game.effects.remove(e); break; }
    }
}
