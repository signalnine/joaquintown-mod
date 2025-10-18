package com.joaquintown.mod.mixin;

import com.joaquintown.mod.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Optional;

/**
 * Mixin to replace emeralds with bananas in all villager trades.
 *
 * This intercepts trade offer constructor arguments and swaps any emerald items
 * with banana items before they're assigned to final fields.
 */
@Mixin(TradeOffer.class)
public class TradeOfferMixin {

    /**
     * Replace emeralds with bananas in the first buy item parameter.
     */
    @ModifyVariable(method = "<init>*", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static TradedItem replaceFirstBuyItem(TradedItem firstBuyItem) {
        if (firstBuyItem != null && firstBuyItem.item().value().equals(Items.EMERALD)) {
            return new TradedItem(ModItems.BANANA, firstBuyItem.count());
        }
        return firstBuyItem;
    }

    /**
     * Replace emeralds with bananas in the second buy item parameter.
     */
    @ModifyVariable(method = "<init>*", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static Optional<TradedItem> replaceSecondBuyItem(Optional<TradedItem> secondBuyItem) {
        if (secondBuyItem.isPresent() && secondBuyItem.get().item().value().equals(Items.EMERALD)) {
            return Optional.of(new TradedItem(ModItems.BANANA, secondBuyItem.get().count()));
        }
        return secondBuyItem;
    }

    /**
     * Replace emeralds with bananas in the sell item parameter.
     */
    @ModifyVariable(method = "<init>*", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static ItemStack replaceSellItem(ItemStack sellItem) {
        if (sellItem != null && sellItem.getItem().equals(Items.EMERALD)) {
            return new ItemStack(ModItems.BANANA, sellItem.getCount());
        }
        return sellItem;
    }
}
