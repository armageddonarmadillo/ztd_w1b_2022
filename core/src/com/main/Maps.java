package com.main;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class Maps {
    static HashMap<String, Texture> resources = new HashMap<>();
    static HashMap<String, Integer> values = new HashMap<>();
    static HashMap<String, String> descriptions = new HashMap<>();

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
        resources.put("bullets_sweeper_firework", Resources.sweeper_firework);
        //buttons
        resources.put("buttons_play", Resources.button_play);
        resources.put("buttons_pause", Resources.button_pause);
        resources.put("buttons_close", Resources.button_close);
        resources.put("buttons_cannon", Resources.button_cannon);
        resources.put("buttons_fire", Resources.button_cannon_fire);
        resources.put("buttons_super", Resources.button_cannon_super);
        resources.put("buttons_treb", Resources.button_cannon_treb);
        resources.put("buttons_wall", Resources.violet); //THIS IS A 1x1 ASSET
        resources.put("buttons_firework", Resources.button_firework); //THIS IS A 1x1 ASSET
        //tiles
        resources.put("tiles_lava", Resources.tile_lava);
        resources.put("tiles_lava_path", Resources.tile_lava_path);
        resources.put("tiles_wall_lava", Resources.tile_wall_lava);
        //effects
        resources.put("effects_click", Resources.effect_click);
        resources.put("effects_boom", Resources.effect_boom);
        resources.put("effects_build", Resources.effect_build);
        resources.put("effects_burst_rainbow", Resources.effect_burst_rainbow);
        resources.put("effects_burst_toxic", Resources.effect_burst_toxic);
        resources.put("effects_shock_purple", Resources.effect_shock_purple);
        resources.put("effects_shield_shatter", Resources.effect_shield_shatter);


        //TODO: VALUES

        //rows & cols
        values.put("cols_boom", 7);
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
        values.put("cols_build", 3);
        values.put("cols_burst_rainbow", 3);
        values.put("rows_burst_rainbow", 3);
        values.put("cols_burst_toxic", 3);
        values.put("rows_burst_toxic", 3);
        values.put("cols_shock_purple", 2);
        values.put("rows_shock_purple", 3);
        values.put("cols_shield_shatter", 3);
        values.put("rows_shield_shatter", 2);

        //anim delays (take 1 / value in the code)
        values.put("ft_boom", 10);
        values.put("ft_click", 20);
        values.put("ft_build", 7);
        values.put("ft_burst_rainbow", 10);
        values.put("ft_burst_toxic", 10);
        values.put("ft_shock_purple", 10);
        values.put("ft_shield_shatter", 10);

        //cannon data
        values.put("delay_fire", 15);
        values.put("delay_crossbow", 45);
        values.put("delay_treb", 100);
        values.put("health_fire", 50);
        values.put("health_crossbow", 250);
        values.put("health_treb", 500);
        //bullets
        values.put("speed_fire", 15);
        values.put("speed_crossbow", 25);
        values.put("speed_treb", 50);
        values.put("damage_fire", 2);
        values.put("damage_crossbow", 3);
        values.put("damage_treb", 5);

        //enemy data
        //zombies
        values.put("speed_zombie", 3);
        values.put("speed_speedy", 6);
        values.put("health_riot", 25);
        values.put("health_sriot", 10);
        values.put("health_speedy", 1);
        //blobs
        values.put("health_blob_toxic", 5);
        values.put("health_blob_pink", 5);
        values.put("health_blob_molten", 5);
        //wizards
        values.put("health_wizard_red", 2);
        values.put("health_wizard_blue", 2);
        values.put("health_wizard_green", 2);

        //costs
        values.put("unlock_super", 500);
        values.put("unlock_treb", 350);
        values.put("unlock_wall", 1000);
        values.put("unlock_firework", 333);

        values.put("cost_fire", 25);
        values.put("cost_super", 20);
        values.put("cost_treb", 50);
        values.put("cost_wall", 100);
        values.put("cost_firework", 333);


        //TODO: DESCRIPTIONS
        descriptions.put("tooltips_fire", "A low durability cannon, that fires low damage bullets at a high rate of fire.");
        descriptions.put("tooltips_super", "A very high durability cannon, that fires low damage bullets at a low rate of fire.");
        descriptions.put("tooltips_treb", "A medium durability cannon, that fires high damages bullets at a low rate of fire.");
        descriptions.put("tooltips_wall", "Occupies a tile and absorbs 5 damage from zombies. Walls can stack.");
    }
}
