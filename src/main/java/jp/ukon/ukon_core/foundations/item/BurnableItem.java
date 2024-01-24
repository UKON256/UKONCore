package jp.ukon.ukon_core.foundations.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public abstract class BurnableItem extends Item {
    public BurnableItem(Properties properties) {
        super(properties);
    }

    private int burnTime = -1;

    public void setBurnTime(int duration) {
        burnTime = duration;
    }
    @Override
    public int getBurnTime(ItemStack stack, RecipeType<?> recipeType) {
        return burnTime;
    }
}
