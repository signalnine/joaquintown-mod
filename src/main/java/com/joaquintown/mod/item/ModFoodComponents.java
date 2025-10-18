package com.joaquintown.mod.item;

import net.minecraft.component.type.FoodComponent;

/**
 * Defines food properties for custom items.
 */
public class ModFoodComponents {

    /**
     * Banana food component - restores 4 hunger (2 drumsticks) and 4.8 saturation.
     * Similar to apples but slightly more filling.
     */
    public static final FoodComponent BANANA = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.6f)
            .build();

    /**
     * Banana Bread - hearty baked good.
     * Restores 8 hunger (4 drumsticks) and 9.6 saturation.
     */
    public static final FoodComponent BANANA_BREAD = new FoodComponent.Builder()
            .nutrition(8)
            .saturationModifier(0.6f)
            .build();

    /**
     * Fried Banana - cooked banana with honey.
     * Restores 6 hunger (3 drumsticks) and 7.2 saturation.
     */
    public static final FoodComponent FRIED_BANANA = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.6f)
            .build();

    /**
     * Chocolate Banana - sweet treat.
     * Restores 5 hunger (2.5 drumsticks) and 7.5 saturation.
     */
    public static final FoodComponent CHOCOLATE_BANANA = new FoodComponent.Builder()
            .nutrition(5)
            .saturationModifier(0.75f)
            .build();

    /**
     * Banana Smoothie - refreshing drink.
     * Restores 6 hunger (3 drumsticks) and 9.0 saturation.
     * Fast eating speed.
     */
    public static final FoodComponent BANANA_SMOOTHIE = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.75f)
            .snack() // Can be eaten faster
            .build();
}
