package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tooltip {
    int x, y, w, h;
    String type;
    boolean hidden = true;                  // hides the tooltip
    Button close;                           // special close button to hide tooltip
    BitmapFont font = new BitmapFont();     // used to draw text
    GlyphLayout layout = new GlyphLayout(); // used to calculate dimensions of the font's text

    Tooltip(String type, Button p){
        this.type = type;
        w = 200;
        h = 100;
        x = (p.x + p.w / 2) - w / 2;
        y = p.y - h - (h / 10);
        close = new Button("close", x + w - 27, y + h - 27);
        close.locked = false;
    }

    void draw(SpriteBatch b){
        if(hidden) return;
        b.draw(Resources.tan, x, y, w, h);
        close.draw(b);
        //the following line will look up a phrase from the descriptions table then split it by all spaces in the phrase.
        String[] words = (Maps.descriptions.get("tooltips_" + type) == null ? "No tooltip found..." : Maps.descriptions.get("tooltips_" + type)).split(" ");
        int rtx = 25, rty = 5; //relative position to the tooltip positon
        for(String l : words){
            if(rtx + layout.width >= w - 28){
                rtx = 25;
                rty += layout.height + 2;
            }
            font.setColor(Color.MAGENTA);
            font.draw(b, l, x + rtx, y + h - rty);
            layout.setText(font, " " + l);
            rtx += layout.width;
        }

        String cashmoney = "UNLOCK: " + (Maps.values.get("unlock_"+type) == null ? 200 : Maps.values.get("unlock_"+type)); //switch 888 with lookup
        font.setColor(Color.DARK_GRAY); //because money
        font.getData().setScale(1.5f);
        font.draw(b, cashmoney, x + 25 + 1, y + 37 - 1);
        font.setColor(Color.GOLD); //because money
        font.draw(b, cashmoney, x + 25, y + 37);
        font.getData().setScale(1.f);

        font.setColor(Color.WHITE);
        font.draw(b, "(tap again to unlock)", x + 35 + 1, y + 15 - 1);
        font.setColor(Color.BLACK);
        font.draw(b, "(tap again to unlock)", x + 35, y + 15);
    }

    Rectangle hitbox(){
        return new Rectangle(x, y, w, h);
    }
}
