package jp.ukon.ukon_core.client.guide;

import jp.ukon.postengine.util.UUIDUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;

public class GuideRegistry {
    public static Map<UUID, List<SceneBuilder.ISceneEntry>> AllEntries = new HashMap<>();
    public static Map<UUID, CompiledGuideData> AllCompiledData = new HashMap<>();
    final String namespace;

    public GuideRegistry(String namespace) {
        this.namespace = namespace;
    }

    public GuideRegistryBuilder of(RegistryObject<?> parent) {
        return new GuideRegistryBuilder(UUIDUtil.generateUUIDFromString(parent.getId().toString()));
    }
    public GuideRegistryBuilder of(String path) {
        return new GuideRegistryBuilder(UUIDUtil.generateUUIDFromString(namespace + ":" + path));
    }

    /**
     * 指定されたアイテムがガイドを持っているか判定する
     * @param item 指定アイテム
     * @return true: ガイドがある, false: ガイドがない
     */
    public static boolean hasGuide(Item item) {
        return hasGuide(UUIDUtil.generateUUIDFromString(ForgeRegistries.ITEMS.getKey(item).toString()));
    }
    public static boolean hasGuide(UUID id) {
        return AllEntries.containsKey(id);
    }

    public static CompiledGuideData copyCompiledData(Item item) {
        return copyCompiledData(UUIDUtil.generateUUIDFromString(ForgeRegistries.ITEMS.getKey(item).toString()));
    }
    public static CompiledGuideData copyCompiledData(UUID id) {
        List<SceneBuilder.ISceneEntry> entries = AllEntries.get(id);
        if (!AllCompiledData.containsKey(id))
            AllCompiledData.put(id, new SceneBuilder(entries).compile());
        return AllCompiledData.get(id).copy();
    }

    /**
     * スマートにガイドの登録するためのクラス
     */
    public class GuideRegistryBuilder {
        UUID id;

        protected GuideRegistryBuilder(UUID id) {
            this.id = id;
        }

        /**
         * チャプターの追加
         * @param entry コンパイル用のISceneEntry
         */
        public GuideRegistryBuilder addChapter(SceneBuilder.ISceneEntry entry) {
            synchronized (AllEntries) {
                List<SceneBuilder.ISceneEntry> list = AllEntries.computeIfAbsent(id, x -> new ArrayList<>());
                synchronized (list) {
                    list.add(entry);
                }
            }
            return this;
        }
    }
}
