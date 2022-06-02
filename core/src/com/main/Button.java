package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    int x, y, w, h;
    boolean selected, locked;
    String type;
    Color color;
    BitmapFont font;
    GlyphLayout layout;

    Button(String type, int x, int y, int w, int h, Color color){
        this.type = type;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        font = new BitmapFont();
        font.setColor(Resources.inverse_color(color));
        font.getData().setScale(2.0f);
        layout = new GlyphLayout();
        layout.setText(font, "Button");
    }

    Button(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        this.w = (Maps.resources.get("buttons_"+type) == null ? Resources.button_cannon : Maps.resources.get("buttons_"+type)).getWidth();
        this.h = (Maps.resources.get("buttons_"+type) == null ? Resources.button_cannon : Maps.resources.get("buttons_"+type)).getHeight();
    }

    void draw(SpriteBatch b){
        if(Maps.resources.get("buttons_"+type) == null) {
            b.draw(Resources.create_texture(color), x, y, w, h);
            font.draw(b, layout, (float) (x + w / 2) - (layout.width / 2), (float) (y + h / 2) + (layout.height / 2));
        } else {
            if(selected) b.draw(Resources.border, x - 7, y - 7);
            b.draw(Maps.resources.get("buttons_"+type), x, y);
        }
    }

    Rectangle hitbox(){
        return new Rectangle(x, y, w, h);
    }
}
