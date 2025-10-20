package com.joaquintown.mod.item;

import com.joaquintown.mod.JoaquintownMod;
import com.joaquintown.mod.init.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

/**
 * Registers custom items for the Joaquintown mod.
 */
public class ModItems {

    /**
     * Banana item - replaces emeralds as the villager trading currency.
     */
    public static final Item BANANA = registerItem("banana",
            new Item(new Item.Settings().food(ModFoodComponents.BANANA)));

    /**
     * Banana Bread - hearty baked good made from bananas, wheat, and sugar.
     */
    public static final Item BANANA_BREAD = registerItem("banana_bread",
            new Item(new Item.Settings().food(ModFoodComponents.BANANA_BREAD)));

    /**
     * Fried Banana - cooked banana with honey.
     */
    public static final Item FRIED_BANANA = registerItem("fried_banana",
            new Item(new Item.Settings().food(ModFoodComponents.FRIED_BANANA)));

    /**
     * Chocolate Banana - sweet treat combining banana and cocoa.
     */
    public static final Item CHOCOLATE_BANANA = registerItem("chocolate_banana",
            new Item(new Item.Settings().food(ModFoodComponents.CHOCOLATE_BANANA)));

    /**
     * Banana Smoothie - refreshing drink made from bananas and milk.
     */
    public static final Item BANANA_SMOOTHIE = registerItem("banana_smoothie",
            new Item(new Item.Settings().food(ModFoodComponents.BANANA_SMOOTHIE).maxCount(16)));

    /**
     * Seaweed Wrap - nutritious ocean-based food item.
     */
    public static final Item SEAWEED_WRAP = registerItem("seaweed_wrap",
            new Item(new Item.Settings().food(ModFoodComponents.SEAWEED_WRAP)));

    /**
     * Godzilla spawn egg - spawns Godzilla when used.
     * Colors: Dark gray (0x2d3e2d) with charcoal spots (0x1a1a1a)
     */
    public static final Item GODZILLA_SPAWN_EGG = registerItem("godzilla_spawn_egg",
            new SpawnEggItem(ModEntities.GODZILLA, 0x2d3e2d, 0x1a1a1a, new Item.Settings()));

    /**
     * King Kong spawn egg - spawns King Kong when used.
     * Colors: Dark brown (0x3d2817) with lighter brown spots (0x6b4423)
     */
    public static final Item KING_KONG_SPAWN_EGG = registerItem("king_kong_spawn_egg",
            new SpawnEggItem(ModEntities.KING_KONG, 0x3d2817, 0x6b4423, new Item.Settings()));

    /**
     * Kong's Axe - a legendary axe dropped by King Kong.
     * Netherite axe with catastrophic power - one-shots most mobs.
     * Base damage: 35 (17.5 hearts) - absolutely devastating!
     */
    public static final Item KONGS_AXE = registerItem("kongs_axe",
            new AxeItem(ToolMaterials.NETHERITE,
                new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 35.0f, -2.0f))));

    /**
     * Godzilla's Blade - an ultra-rare legendary sword dropped by Godzilla.
     * Netherite sword with catastrophic power befitting the King of Monsters.
     * Base damage: 30 (15 hearts) + lightning-fast attack speed - one-shots most mobs!
     */
    public static final Item GODZILLAS_BLADE = registerItem("godzillas_blade",
            new SwordItem(ToolMaterials.NETHERITE,
                new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 30, -1.5f))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(JoaquintownMod.MOD_ID, name), item);
    }

    /**
     * Register all custom items and add them to creative inventory.
     */
    public static void registerModItems() {
        JoaquintownMod.LOGGER.info("Registering mod items for " + JoaquintownMod.MOD_ID);

        // Add banana to the Ingredients creative tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(BANANA);
        });

        // Add banana to the Food & Drinks creative tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(BANANA);
            entries.add(BANANA_BREAD);
            entries.add(FRIED_BANANA);
            entries.add(CHOCOLATE_BANANA);
            entries.add(BANANA_SMOOTHIE);
            entries.add(SEAWEED_WRAP);
        });

        // Add spawn eggs to the Spawn Eggs creative tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(GODZILLA_SPAWN_EGG);
            entries.add(KING_KONG_SPAWN_EGG);
        });

        // Add Kong's Axe and Godzilla's Blade to the Combat creative tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(KONGS_AXE);
            entries.add(GODZILLAS_BLADE);
        });
    }
}
