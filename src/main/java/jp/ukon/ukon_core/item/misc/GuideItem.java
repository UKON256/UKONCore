package jp.ukon.ukon_core.item.misc;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GuideItem extends Item {
    public GuideItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        //ScreenOpener.open(new TestScreen());

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    /*public class TestScreen extends AbstractUScreen {

        @Override
        protected void renderWindow(PoseStack ms, int mouseX, int mouseY, float partialTicks) {

        }

        @Override
        protected void init() {
            super.init();
            addRenderableWidget(new CursorArea(0, 0, 1000, 1000, GLFW.GLFW_HAND_CURSOR));
        }
    }*/
}
