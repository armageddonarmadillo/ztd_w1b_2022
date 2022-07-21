package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    int x, y, w, h;
    boolean selected, locked = true;
    String type;
    Color color;
    BitmapFont font;
    GlyphLayout layout;
    Tooltip t;

    Button(String type, int x, int y, int w, int h, Color color){
        this.type = type;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        if(Maps.resources.get("buttons_"+type) != null) return;
        this.color = color;
        font = new BitmapFont();
        font.setColor(Resources.inverse_color(color));
        layout = new GlyphLayout();

        while(layout.width < w - (3 * (float)(w / 10)) && layout.height < h - (3 * (float)(h / 10))){
            font.getData().setScale(font.getData().scaleX + .1f);
            layout.setText(font, type);
        }
    }

    Button(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        this.w = (Maps.resources.get("buttons_"+type) == null ? Resources.button_cannon : Maps.resources.get("buttons_"+type)).getWidth();
        this.h = (Maps.resources.get("buttons_"+type) == null ? Resources.button_cannon : Maps.resources.get("buttons_"+type)).getHeight();
        if(!type.equals("close")) t = new Tooltip(type, this);
    }

    void draw(SpriteBatch b){
        if(Maps.resources.get("buttons_"+type) == null) {
            b.draw(Resources.create_texture(color), x, y, w, h);
            font.draw(b, layout, (float) (x + w / 2) - (layout.width / 2), (float) (y + h / 2) + (layout.height / 2));
        } else {
            b.draw(Maps.resources.get("buttons_"+type), x, y, w, h);
            if(selected) b.draw(Resources.border, x - 7, y - 7);
            if(locked) b.draw(Resources.lock, x, y);
            if(t!=null) t.draw(b);
        }
    }

    Rectangle hitbox(){
        return new Rectangle(x, y, w, h);
    }
}
