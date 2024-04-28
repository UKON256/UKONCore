package jp.ukon.ukon_core.foundations.guide;

import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;

public class GuideCategory {
    public static HashSet<GuideCategory> AllCategories = new HashSet<>();

    private final ResourceLocation location;

    public ResourceLocation getLocation() {
        return location;
    }


    public GuideCategory setTitle(String titleKey)
    {
        return this;
    }
    public GuideCategory setDescription(String descriptionKey)
    {
        return this;
    }

    public GuideCategory(ResourceLocation location)
    {
        this.location = location;
        AllCategories.add(this);
    }
}
