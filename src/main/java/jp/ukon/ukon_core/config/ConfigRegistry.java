package jp.ukon.ukon_core.config;

public class ConfigRegistry {
    /*public String namespace;
    public Map<ModConfig.Type, UConfigGroup> Parents;

    public ConfigRegistry(String namespace) {
        Parents = new HashMap<>();
        this.namespace = namespace;
    }

    public void registerAll(ModLoadingContext context) {

    }

    @FunctionalInterface
    public interface IValueProvider<T, V extends ForgeConfigSpec.ConfigValue<T>> extends Function<ForgeConfigSpec.Builder, T> {
    }

    public class UConfigValue<T, V extends ForgeConfigSpec.ConfigValue<T>> {
        public String Name;
        public ForgeConfigSpec.ConfigValue<T> Value;
        public UConfigGroup Parent;
        protected IValueProvider<T, V> provider;

        public UConfigValue(String name, T defaultValue, IValueProvider<T, V> provider, UConfigGroup parent, String... commentsLangKey) {
            this.Name = name;
            this.Parent = parent;
            this.provider = provider;
        }

        public void register() {

        }*/

        /**
         * ネストの深さを取得します(基準:-1)
         * @return ネストの深さ
         */
        /*public int getDepth() {
            return -1;
        }
    }

    public class UConfigGroup extends UConfigValue<Boolean, ForgeConfigSpec.BooleanValue> {
        public List<UConfigValue<?, ?>> Children;

        public UConfigGroup(String name) {
            super(name, builder -> null, );
            Parent = parent;
            Children = new ArrayList<>();
        }

        public void registerChildren(ForgeConfigSpec.Builder builder) {
            if (!Children.isEmpty())
                Children.forEach(x -> x.register(builder));
        }
    }

    public class UBooleanConfig extends UConfigValue<Boolean, ForgeConfigSpec.BooleanValue> {

    }*/
}
