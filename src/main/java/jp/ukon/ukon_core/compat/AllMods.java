package jp.ukon.ukon_core.compat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

public enum AllMods {
    ComputerCraft,
    Create,
    Curios,
    ;

    private final String id;

    AllMods() {
        id = name().toLowerCase(Locale.ROOT);
    }

    public String getId() {
        return id;
    }
    public ResourceLocation getRL(String path) {
        return new ResourceLocation(id, path);
    }
    public Block getBlock(String path) {
        return ForgeRegistries.BLOCKS.getValue(getRL(path));
    }
    public Item getItem(String path) {
        return ForgeRegistries.ITEMS.getValue(getRL(path));
    }

    public boolean isLoaded() {
        return ModList.get().isLoaded(id);
    }
}
