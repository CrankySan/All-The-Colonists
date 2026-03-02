package com.allthecolonists.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Configuration for All The Colonists mod.
 */
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_MEKANIST_HUT = BUILDER
            .comment("Enable or disable the Mekanist Hut building.")
            .define("enableMekanistHut", true);

    public static final ModConfigSpec.IntValue MEKANIST_MAX_RECIPES = BUILDER
            .comment("Maximum number of concurrent recipes a Mekanist worker can handle.")
            .defineInRange("mekanistMaxRecipes", 2, 1, 16);

    public static final ModConfigSpec.BooleanValue REQUIRE_METALLURGIC_INFUSER = BUILDER
            .comment("Whether the Mekanist requires a Metallurgic Infuser as intermediate block for recipes.")
            .define("requireMetallurgicInfuser", true);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
