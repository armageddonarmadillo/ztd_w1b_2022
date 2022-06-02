package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    int x, y, w, h, speed;
    boolean active = true;
    float angle;
    String type;

    Bullet(String type, int x, int y, float angle){
        this.type = type;
        this.w = (Maps.resources.get("bullets_"+type) == null ? Resources.bullet : Maps.resources.get("bullets_"+type)).getWidth(); // cols;
        this.h = (Maps.resources.get("bullets_"+type) == null ? Resources.bullet : Maps.resources.get("bullets_"+type)).getHeight(); // rows;
        this.x = x - w / 2;
        this.y = y - h / 2;
        speed = 10;
        this.angle = angle;
    }

    void update(){
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
        be_collision();
    }

    void draw(SpriteBatch b){
        b.draw((Maps.resources.get("bullets_"+type) == null ? Resources.bullet : Maps.resources.get("bullets_"+type)), x, y);
    }

    Rectangle hitbox(){
        return new Rectangle(x, y, w, h);
    }

    void be_collision(){
        if(Game.enemies.isEmpty()) return;
        for(Enemy e : Game.enemies) if(e.hitbox().contains(hitbox())) { e.hp--; active = false; }
    }
}
