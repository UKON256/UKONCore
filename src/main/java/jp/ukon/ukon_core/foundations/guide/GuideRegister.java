package jp.ukon.ukon_core.foundations.guide;

import java.util.HashSet;

public class GuideRegister {
    public static HashSet<GuidePage> AllGuide = new HashSet<>();

    public static void register(GuidePage guide)
    {
        AllGuide.add(guide);
    }
}
