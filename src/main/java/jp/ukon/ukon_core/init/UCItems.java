package jp.ukon.ukon_core.init;

import jp.ukon.ukon_core.UKONCore;
import jp.ukon.ukon_core.item.misc.GuideItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UCItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UKONCore.ID);

    public static RegistryObject<Item> Guide = ITEMS.register("guide",
            () -> new GuideItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
