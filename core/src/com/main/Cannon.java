package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Cannon extends Sprite {
    int x, y, w, h;
    int counter = 0, delay, hp, hp_max, hp_counter = 0, hp_delay;
    boolean active = true;
    String type;

    Cannon(String type, int x, int y){
        super((Maps.resources.get("cannons_"+type) == null ? Resources.cannon : Maps.resources.get("cannons_"+type)));
        this.type = type;
        this.w = (Maps.resources.get("cannons_"+type) == null ? Resources.cannon : Maps.resources.get("cannons_"+type)).getWidth(); // cols;
        this.h = (Maps.resources.get("cannons_"+type) == null ? Resources.cannon : Maps.resources.get("cannons_"+type)).getHeight(); // rows;
        this.x = lock(x, w);
        this.y = lock(y, h);
        this.hp = 100;
        hp_max = this.hp;
        hp_delay = 22;
        delay = 30;
        setPosition(this.x, this.y);
    }

    void update(){
        super.setRotation((float)Math.toDegrees(calc_angle()));
        if(counter++ >= delay) { fire(); counter = 0; }
        if(hp_counter++ >= hp_delay) { hp--; hp_counter = 0; }
        active = !(hp <= 0);
        ce_collision();
    }

    void draw(SpriteBatch b){
        super.draw(b);
        b.draw(Resources.create_texture(Color.WHITE), x, y + h, w, 5);
        b.draw(Resources.create_texture(Color.GREEN), x, y + h, hp * ((float)w / hp_max), 5);
    }

    int lock(int coord, int sizer){
        return coord - coord % sizer;
    }

    void fire(){
        Game.bullets.add(new Bullet(type, x + w / 2, y + h / 2, calc_angle()));
    }

    Rectangle hitbox(){
        return new Rectangle(x, y, w, h);
    }

    void ce_collision(){
        if(Game.enemies.isEmpty()) return;
        for(Enemy e : Game.enemies) if(e.hitbox().overlaps(hitbox())) { e.hp -= 50; active = false; }
    }

    float calc_angle(){
        Enemy closest = null; // HAS A VALUE OF NULL BECAUSE THERE IS NO CONSTRUCTOR CALL
        //find the closest zombie
        for(Enemy e : Game.enemies){
            if(closest == null) { closest = e; continue; }  //SINCE THE CLOSEST IS NULL INITIALLY,
            // WE SET CLOSEST TO E RIGHT AWAY, AND CONTINUE SKIPS TO THE NEXT INDEX
            //calc the hypoteneuse (as the distance between the cannon and the target) of both closest and e
            float hyp_closest = (float)Math.sqrt(((closest.x - x) * (closest.x - x)) + ((closest.y - y)*(closest.y - y)));
            float hyp_e = (float)Math.sqrt(((e.x - x) * (e.x - x)) + ((e.y - y)*(e.y - y)));
            //figure out which is closer
            if(hyp_e < hyp_closest) closest = e;
        }
        //return the calc'd angle between cannon and zombie
        float toa = (float)(y - (closest.y + closest.h / 2)) / (float)(x - (closest.x + closest.w / 2));
        float rev = x >= closest.x ? (float)Math.PI : 0;
        return (float)(Math.atan(toa) + rev);
    }
}
