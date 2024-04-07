package jp.ukon.ukon_core.foundations.guide;

import java.util.HashSet;

public class GuideCategory {
    public static HashSet<GuideCategory> AllCategories = new HashSet<>();

    public String titleKey;
    public String descriptionKey;

    public GuideCategory(String titleKey, String descriptionKey)
    {
        this.titleKey = titleKey;
        this.descriptionKey = descriptionKey;
        AllCategories.add(this);
    }
}
