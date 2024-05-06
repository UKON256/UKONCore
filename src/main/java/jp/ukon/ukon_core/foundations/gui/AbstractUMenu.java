package jp.ukon.ukon_core.foundations.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractUMenu<T extends BlockEntity> extends AbstractContainerMenu {
    public Player player;
    public Inventory playerInventory;
    public T content;

    protected AbstractUMenu(MenuType<?> type, int id) {
        super(type, id);
    }
    protected AbstractUMenu(MenuType<?> type, int id, Inventory inventory, T content) {
        super(type, id);
        init(inventory, content);
    }

    protected void init(Inventory inventory, T content) {
        player = inventory.player;
        playerInventory = inventory;
        this.content = content;
        initAndReadInventory(content);
        addSlots();
        broadcastChanges();
    }

    @OnlyIn(Dist.CLIENT)
    protected abstract T createOnClient(FriendlyByteBuf data);
    protected abstract void initAndReadInventory(T content);
    protected abstract void addSlots();
    protected abstract void saveData(T content);

    protected void addPlayerSlots(int x, int y) {
        for (int hotbarSlot = 0; hotbarSlot < 9; ++hotbarSlot)
            this.addSlot(new Slot(playerInventory, hotbarSlot, x + hotbarSlot * 18, y + 58));
        for (int row = 0; row < 3; ++row)
            for (int col = 0; col < 9; ++col)
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, x + col * 18, y + row * 18));
    }

    @Override
    public void removed(Player playerIn) {
        super.removed(playerIn);
        saveData(content);
    }

    @Override
    public boolean stillValid(Player player) {
        if (content == null)
            return false;
        return true;
    }
}
