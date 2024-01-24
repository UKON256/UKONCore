package jp.ukon.ukon_core.foundations;

import java.util.ArrayList;
import java.util.List;

public abstract class ModBase {
    public static List<ModBase> Mods = new ArrayList<ModBase>();

    public abstract String getModID();
    public abstract String getModName();
    public abstract String getModVersion();

    public static void register(ModBase target) {
        Mods.add(target);
    }
}
