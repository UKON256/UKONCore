package jp.ukon.ukon_core.world.wrapper;

/*@ParametersAreNonnullByDefault
public class WrappedWorld extends Level implements ServerLevelAccessor {
    protected Level world;
    protected ChunkSource chunkSource;
    //protected LevelEntityGetter<Entity> entityGetter = new DummyLevelEntityGetter<>();

    public WrappedWorld(Level world) {
        super((WritableLevelData) world.getLevelData(), world.dimension(), world.dimensionTypeRegistration(), world::getProfiler, world.isClientSide, world.isDebug(), 0, 0);

        this.world = world;
    }

    public void setChunkSource(ChunkSource chunkSource) {
        this.chunkSource = chunkSource;
    }*/

    /*public void setEntityGetter(LevelEntityGetter<Entity> entityGetter) {
        this.entityGetter = entityGetter;
    }*/

    /*@NotNull
    @Override
    public LevelLightEngine getLightEngine() {
        return world.getLightEngine();
    }

    @NotNull
    @Override
    public BlockState getBlockState(BlockPos pPos) {
        return world.getBlockState(pPos);
    }

    @Override
    public boolean isStateAtPosition(BlockPos pPos, Predicate<BlockState> pState) {
        return world.isStateAtPosition(pPos, pState);
    }

    @Nullable
    @Override
    public BlockEntity getBlockEntity(BlockPos pPos) {
        return world.getBlockEntity(pPos);
    }

    @Override
    public boolean setBlock(BlockPos pPos, BlockState pNewState, int pFlags) {
        return world.setBlock(pPos, pNewState, pFlags);
    }

    @Override
    public int getMaxLocalRawBrightness(BlockPos pPos) {
        return 15;
    }

    @Override
    public void sendBlockUpdated(BlockPos pPos, BlockState pOldState, BlockState pNewState, int pFlags) {
        world.sendBlockUpdated(pPos, pOldState, pNewState, pFlags);
    }

    @Override
    public void playSeededSound(@Nullable Player pPlayer, double pX, double pY, double pZ, SoundEvent pSoundEvent, SoundSource pSoundSource, float pVolume, float pPitch, long pSeed) {

    }

    @Override
    public void playSeededSound(@Nullable Player pPlayer, Entity pEntity, SoundEvent pSoundEvent, SoundSource pSoundSource, float pVolume, float pPitch, long pSeed) {

    }

    @Override
    public void playSound(@Nullable Player pPlayer, Entity pEntity, SoundEvent pEvent, SoundSource pCategory, float pVolume, float pPitch) {

    }

    @Override
    public void playSound(@Nullable Player pPlayer, double pX, double pY, double pZ, SoundEvent pSound, SoundSource pCategory, float pVolume, float pPitch) {

    }

    @NotNull
    @Override
    public String gatherChunkSourceStats() {
        return world.gatherChunkSourceStats();
    }

    @Nullable
    @Override
    public Entity getEntity(int pId) {
        return null;
    }

    @Override
    public boolean addFreshEntity(Entity pEntity) {
        //pEntity.setLevel() = world;
        return world.addFreshEntity(pEntity);
    }

    @Nullable
    @Override
    public MapItemSavedData getMapData(String pMapName) {
        return null;
    }

    @Override
    public void setMapData(String pMapId, MapItemSavedData pData) {

    }

    @Override
    public int getFreeMapId() {
        return world.getFreeMapId();
    }

    @Override
    public void destroyBlockProgress(int pBreakerId, BlockPos pPos, int pProgress) {

    }

    @NotNull
    @Override
    public Scoreboard getScoreboard() {
        return world.getScoreboard();
    }

    @NotNull
    @Override
    public RecipeManager getRecipeManager() {
        return world.getRecipeManager();
    }*/

    /*@NotNull
    @Override
    protected LevelEntityGetter<Entity> getEntities() {
        return entityGetter;
    }*/

    /*@NotNull
    @Override
    public LevelTickAccess<Block> getBlockTicks() {
        return world.getBlockTicks();
    }

    @NotNull
    @Override
    public LevelTickAccess<Fluid> getFluidTicks() {
        return world.getFluidTicks();
    }

    @NotNull
    @Override
    public ChunkSource getChunkSource() {
        return chunkSource != null ? chunkSource : world.getChunkSource();
    }

    @Override
    public void levelEvent(@Nullable Player pPlayer, int pType, BlockPos pPos, int pData) {

    }

    @Override
    public void gameEvent(GameEvent pEvent, Vec3 pPosition, GameEvent.Context pContext) {

    }

    @Override
    public void gameEvent(@Nullable Entity pEntity, GameEvent pEvent, BlockPos pPos) {

    }

    @NotNull
    @Override
    public RegistryAccess registryAccess() {
        return world.registryAccess();
    }

    @Override
    public float getShade(Direction pDirection, boolean pShade) {
        return world.getShade(pDirection, pShade);
    }

    @Override
    public void updateNeighbourForOutputSignal(BlockPos pPos, Block pBlock) {

    }

    @NotNull
    @Override
    public List<? extends Player> players() {
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public Holder<Biome> getUncachedNoiseBiome(int pX, int pY, int pZ) {
        return world.getUncachedNoiseBiome(pX, pY, pZ);
    }

    @Override
    public int getMaxBuildHeight() {
        return this.getMinBuildHeight() + this.getHeight();
    }

    @Override
    public int getSectionsCount() {
        return this.getMaxSection() - this.getMinSection();
    }

    @Override
    public int getMinSection() {
        return SectionPos.blockToSectionCoord(this.getMinBuildHeight());
    }

    @Override
    public int getMaxSection() {
        return SectionPos.blockToSectionCoord(this.getMaxBuildHeight() - 1) + 1;
    }

    @Override
    public boolean isOutsideBuildHeight(BlockPos pPos) {
        return this.isOutsideBuildHeight(pPos.getY());
    }

    @Override
    public boolean isOutsideBuildHeight(int pY) {
        return pY < this.getMinBuildHeight() || pY >= this.getMaxBuildHeight();
    }

    @Override
    public int getSectionIndex(int pY) {
        return this.getSectionIndexFromSectionY(SectionPos.blockToSectionCoord(pY));
    }

    @Override
    public int getSectionIndexFromSectionY(int sectionY) {
        return sectionY - this.getMinSection();
    }

    @Override
    public int getSectionYFromSectionIndex(int sectionIndex) {
        return sectionIndex + this.getMinSection();
    }

    @NotNull
    @Override
    public ServerLevel getLevel() {
        if (this.world instanceof ServerLevel) {
            return (ServerLevel) this.world;
        }
        throw new IllegalStateException("Failed get ServerLevel in a client environment");
    }
}*/
