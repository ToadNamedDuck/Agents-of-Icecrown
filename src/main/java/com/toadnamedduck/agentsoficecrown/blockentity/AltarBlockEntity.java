package com.toadnamedduck.agentsoficecrown.blockentity;

import com.toadnamedduck.agentsoficecrown.ModTags;
import com.toadnamedduck.agentsoficecrown.ModTranslatableComponents;
import com.toadnamedduck.agentsoficecrown.block.AltarBlock;
import com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.common.util.FakePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static com.toadnamedduck.agentsoficecrown.datacomponent.ModDataComponentHelpers.SOUL_BINDING.findBinding;

public class AltarBlockEntity extends BlockEntity {
    public AltarBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ALTAR_BLOCK_ENTITY.get(), pos, blockState);
    }
    private ItemStack heldItem = ItemStack.EMPTY;
    //ritual prep
    private static final int RITUAL_LENGTH_IN_TICKS = 101;
    private static final int RITUAL_HEALTH_PER_OPERATION = 2;
    private final int RITUAL_HEALTH_START_MINIMUM = 11;
    private static final int RITUAL_HEALTH_FLOOR = 1;//Will not take you below 1 hp, like say if you get attacked.
    private final int RITUAL_RADIUS = 3;
    private final int REQUIRED_SOUL_LANTERNS = 4;
    private final int REQUIRED_CANDLES = 4;
    private UUID ritualPlayerID = null;
    private long ritualStartTime = -1;

    private static void tell(Player player, MutableComponent key){
        player.displayClientMessage(key, true);
    }

    public boolean tryStartRitual(Player player){
        Level level = this.getLevel();
        if(level == null){
            return false;
        }
        if(!(player instanceof ServerPlayer) || player instanceof FakePlayer){
            return false;
        }
        if(ritualPlayerID != null){
            tell(player, ModTranslatableComponents.RITUAL_IN_PROGRESS.withStyle(ChatFormatting.RED));
            return false;
        }
        if(heldItem.isEmpty()){
            tell(player, ModTranslatableComponents.RITUAL_ALTAR_EMPTY.withStyle(ChatFormatting.RED));
            return false;
        }
        if(!heldItem.is(ModTags.ITEMS.SARONITE_WEAPONS)){
            tell(player, ModTranslatableComponents.RITUAL_WRONG_ITEM.withStyle(ChatFormatting.RED));
            return false;
        }
        if(findBinding(heldItem) != null){
            tell(player, ModTranslatableComponents.RITUAL_ALREADY_BOUND.withStyle(ChatFormatting.RED));
            return false;
        }
        if(!(player.getHealth() > RITUAL_HEALTH_START_MINIMUM)){
            tell(player, ModTranslatableComponents.RITUAL_HEALTH_TOO_LOW.withStyle(ChatFormatting.RED));
            return false;
        }
        if(!isStructureSet(level, this.getBlockPos())){
            tell(player, ModTranslatableComponents.RITUAL_NOT_PREPARED.withStyle(ChatFormatting.RED));
            return false;
        }

        this.ritualPlayerID = player.getUUID();
        this.ritualStartTime = level.getGameTime();
        this.setChanged();
        level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), AltarBlock.UPDATE_ALL);
        return true;
    }

    private void endRitual(){
        if(this.level != null){
            this.ritualPlayerID = null;
            this.ritualStartTime = -1;
            this.setChanged();
            level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), AltarBlock.UPDATE_ALL);
        }
    }

    public void cancelRitual(Player player){
        endRitual();
        tell(player, ModTranslatableComponents.RITUAL_CANCELLED.withStyle(ChatFormatting.RED));

    }

    private void completeRitual(ServerPlayer player){
        if(!(this.heldItem).isEmpty() && findBinding(this.heldItem) == null){
            ModDataComponentHelpers.SOUL_BINDING.awaken(this.heldItem, player);
            //todo: mark player with the attribute here
            //todo: burst of soul particles and some kind of sound.
            tell(player, ModTranslatableComponents.RITUAL_SUCCESS.withStyle(ChatFormatting.GREEN));
        }
        endRitual();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AltarBlockEntity altarBlockEntity){
        if(altarBlockEntity.ritualPlayerID == null ||  !(level instanceof ServerLevel serverLevel)) return;
        if(!(level.getPlayerByUUID(altarBlockEntity.ritualPlayerID) instanceof ServerPlayer player) || !player.isAlive() || altarBlockEntity.heldItem.isEmpty()) {
            if(level.getPlayerByUUID(altarBlockEntity.ritualPlayerID) != null){
                altarBlockEntity.cancelRitual(level.getPlayerByUUID(altarBlockEntity.ritualPlayerID));
            }
            return;
        }

        long elapsed = level.getGameTime() - altarBlockEntity.ritualStartTime;
        if(elapsed >= RITUAL_LENGTH_IN_TICKS){
            altarBlockEntity.completeRitual(player);
            return;
        }

        if(elapsed % 20 == 0){
            if(!altarBlockEntity.isStructureSet(level, altarBlockEntity.getBlockPos())){
                altarBlockEntity.cancelRitual(player);
                return;
            }
            player.hurt(level.damageSources().magic(), Math.min(RITUAL_HEALTH_PER_OPERATION, player.getHealth() - RITUAL_HEALTH_FLOOR));
            serverLevel.playSound(null, pos.getX() +0.5, pos.getY() +0.5, pos.getZ()+0.5, SoundEvents.SOUL_ESCAPE, SoundSource.BLOCKS, 1, (0.9f + 0.8f * elapsed / RITUAL_LENGTH_IN_TICKS));
        }
        serverLevel.sendParticles(ParticleTypes.SOUL, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, 4, 0.25, 0.25, 0.25, 0.02);
    }

    public boolean isStructureSet(Level level, BlockPos center){
        int lanterns = 0;
        int candles = 0;

        for(BlockPos pos : BlockPos.betweenClosed(center.offset(-RITUAL_RADIUS, -1, -RITUAL_RADIUS), center.offset(RITUAL_RADIUS, 2, RITUAL_RADIUS))){
            int dx = pos.getX() - center.getX();
            int dz = pos.getZ() - center.getZ();
            if(dx*dx + dz*dz > RITUAL_RADIUS * RITUAL_RADIUS) continue;

            BlockState state = level.getBlockState(pos);
            if(state.is(Blocks.SOUL_LANTERN)) lanterns++;
            if(state.is(BlockTags.CANDLES) && state.hasProperty(BlockStateProperties.LIT) && state.getValue(BlockStateProperties.LIT)) candles++;

            if(candles >= REQUIRED_CANDLES && lanterns >= REQUIRED_SOUL_LANTERNS) return true;
        }
        return false;
    }

    public ItemStack getItemStack(){
        return this.heldItem;
    }

    public void dropContents(Level level, BlockPos pos){
        if(!level.isClientSide() && !this.getItemStack().isEmpty()){
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), this.heldItem);
        }
    }

    public void setItemStack(ItemStack itemStack, Level level){
            heldItem = itemStack;
            this.setChanged();
            if(!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), AltarBlock.UPDATE_CLIENTS);
            }
    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider registries){
        super.saveAdditional(compoundTag, registries);
        compoundTag.put("Item", this.heldItem.saveOptional(registries));
        if(this.ritualPlayerID != null){
            compoundTag.putUUID("RitualPlayer", ritualPlayerID);
            compoundTag.putLong("StartTime", ritualStartTime);
        }
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider registries){
        super.loadAdditional(compoundTag, registries);
        if(compoundTag.contains("Item")){
            this.heldItem = ItemStack.parseOptional(registries, compoundTag.getCompound("Item"));
        }
        else{
            this.heldItem = ItemStack.EMPTY;
        }
        if(compoundTag.contains("RitualPlayer")){
            this.ritualPlayerID = compoundTag.getUUID("RitualPlayer");
            this.ritualStartTime = compoundTag.getLong("StartTime");
        }
        else{
            this.ritualPlayerID = null;
            this.ritualStartTime = -1;
        }
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public void handleUpdateTag(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.handleUpdateTag(tag, registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider registries) {
        //Super forwards to loadAdditional
        super.onDataPacket(connection, packet, registries);
    }


}
