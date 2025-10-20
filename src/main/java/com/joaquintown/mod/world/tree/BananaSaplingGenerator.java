package com.joaquintown.mod.world.tree;

import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

/**
 * Sapling generator for banana trees.
 * Uses vanilla jungle tree generation for now.
 */
public class BananaSaplingGenerator {

    public static final SaplingGenerator INSTANCE = new SaplingGenerator(
            "banana",
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
    );
}
