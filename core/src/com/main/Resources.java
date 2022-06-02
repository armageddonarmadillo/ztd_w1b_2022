package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Resources {
    //enemies
    static final Texture zombie = new Texture(Gdx.files.internal("Zombies.png"));
    static final Texture dif_zombie = new Texture(Gdx.files.internal("DifZombies.png"));
    static final Texture fast_zombie = new Texture(Gdx.files.internal("Fastzombies.png"));
    static final Texture riot_zombie = new Texture(Gdx.files.internal("riotzombie.png"));
    static final Texture bigriot_zombie = new Texture(Gdx.files.internal("riotzombieBIG.png"));
    static final Texture speedy_zombie = new Texture(Gdx.files.internal("speedy_zombie.png"));
    static final Texture penguin = new Texture(Gdx.files.internal("penguin_cool.png"));
    static final Texture blob_molten = new Texture(Gdx.files.internal("blob_molten.png"));
    static final Texture blob_pink = new Texture(Gdx.files.internal("blob_pink.png"));
    static final Texture blob_toxic = new Texture(Gdx.files.internal("blob_toxic.png"));
    static final Texture wizard_red = new Texture(Gdx.files.internal("wizard_red.png"));
    static final Texture wizard_green = new Texture(Gdx.files.internal("wizard_green.png"));
    static final Texture wizard_blue = new Texture(Gdx.files.internal("wizard_blue.png"));

    //cannons
    static final Texture cannon = new Texture(Gdx.files.internal("Cannon.png"));
    static final Texture crossbow = new Texture(Gdx.files.internal("crossbow.png"));
    static final Texture treb = new Texture(Gdx.files.internal("treb.png"));
    static final Texture fire_cannon = new Texture(Gdx.files.internal("Firecannon.png"));
    static final Texture super_cannon = new Texture(Gdx.files.internal("SuperCannon.png"));

    //bullet
    static final Texture bullet = new Texture(Gdx.files.internal("Bullet.png"));
    static final Texture fire_bullet = new Texture(Gdx.files.internal("firebullet.png"));
    static final Texture super_bullet = new Texture(Gdx.files.internal("superbullet.png"));
    static final Texture rock = new Texture(Gdx.files.internal("rock.png"));

    //backgrounds
    static final Texture bg_template = new Texture(Gdx.files.internal("bg_template.png"));
    static final Texture bg_lab = new Texture(Gdx.files.internal("bg_lab.png"));
    static final Texture bg_snow = new Texture(Gdx.files.internal("bg_snow.png"));

    //buttons
    static final Texture button_cannon = new Texture(Gdx.files.internal("CannonIcon.png"));
    static final Texture button_cannon_fire = new Texture(Gdx.files.internal("FireCannonIcon.png"));
    static final Texture button_cannon_super = new Texture(Gdx.files.internal("SuperCannonIcon.png"));
    static final Texture button_cannon_treb = new Texture(Gdx.files.internal("treb_icon.png"));

    //misc
    static final Texture border = new Texture(Gdx.files.internal("border.png"));
    static final Texture tile = new Texture(Gdx.files.internal("tile.png"));
    static final Texture tile_lava = new Texture(Gdx.files.internal("tile_lava.png"));
    static final Texture tile_lava_occupied = new Texture(Gdx.files.internal("tile_lava_occupied.png"));
    static final Texture tile_lava_path = new Texture(Gdx.files.internal("tile_lava_path.png"));
    static final Texture tile_wall_lava = new Texture(Gdx.files.internal("tile_wall_lava.png"));

    //functions
    static Texture create_texture(Color color){
        Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGB888);
        p.setColor(color);
        p.fillRectangle(0, 0, 1, 1);
        return new Texture(p);
    }

    static Color inverse_color(Color color){
        return new Color(1.0f - color.r, 1.0f - color.g, 1.0f - color.b, 1.0f);
    }
}
