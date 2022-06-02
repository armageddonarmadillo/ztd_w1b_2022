package com.main;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class Maps {
    static HashMap<String, Texture> resources = new HashMap<>();
    static HashMap<String, Integer> values = new HashMap<>();

    static void init_maps(){
        //TODO: RESOURCES
        //enemies
        resources.put("enemies_dif", Resources.dif_zombie);
        resources.put("enemies_fast", Resources.fast_zombie);
        resources.put("enemies_sriot", Resources.riot_zombie);
        resources.put("enemies_riot", Resources.bigriot_zombie);
        resources.put("enemies_penguin", Resources.penguin);
        resources.put("enemies_speedy", Resources.speedy_zombie);
        resources.put("enemies_blob_toxic", Resources.blob_toxic);
        resources.put("enemies_blob_pink", Resources.blob_pink);
        resources.put("enemies_blob_molten", Resources.blob_molten);
        resources.put("enemies_wizard_red", Resources.wizard_red);
        resources.put("enemies_wizard_blue", Resources.wizard_blue);
        resources.put("enemies_wizard_green", Resources.wizard_green);
        //cannons
        resources.put("cannons_fire", Resources.fire_cannon);
        resources.put("cannons_super", Resources.super_cannon);
        resources.put("cannons_crossbow", Resources.crossbow);
        resources.put("cannons_treb", Resources.treb);
        //bullets
        resources.put("bullets_fire", Resources.fire_bullet);
        resources.put("bullets_super", Resources.super_bullet);
        resources.put("bullets_treb", Resources.rock);
        //buttons
        resources.put("buttons_cannon", Resources.button_cannon);
        resources.put("buttons_fire", Resources.button_cannon_fire);
        resources.put("buttons_super", Resources.button_cannon_super);
        resources.put("buttons_treb", Resources.button_cannon_treb);
        //tiles
        resources.put("tiles_lava", Resources.tile_lava);
        resources.put("tiles_lava_path", Resources.tile_lava_path);
        resources.put("tiles_wall_lava", Resources.tile_wall_lava);


        //TODO: VALUES
        values.put("speed_zombie", 3);
        values.put("speed_speedy", 6);
        values.put("cols_speedy", 6);
        values.put("cols_blob_molten", 2);
        values.put("cols_blob_pink", 2);
        values.put("cols_blob_toxic", 2);
        values.put("rows_blob_molten", 2);
        values.put("rows_blob_pink", 2);
        values.put("rows_blob_toxic", 2);
        values.put("rows_tiles_lava", 2);
        values.put("cols_tiles_lava", 2);
        values.put("cols_wizard_red", 2);
        values.put("cols_wizard_green", 2);
        values.put("cols_wizard_blue", 2);
        values.put("rows_tiles_lava_path", 2);
        values.put("cols_tiles_lava_path", 2);
        values.put("rows_tiles_wall_lava", 2);
        values.put("cols_tiles_wall_lava", 2);
    }
}
