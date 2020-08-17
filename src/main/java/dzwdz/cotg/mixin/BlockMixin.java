package dzwdz.cotg.mixin;

import javax.annotation.Nullable;

import dzwdz.cotg.CoTG;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.List;

/*public static List<ItemStack> getDroppedStacks(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack) {
      LootContext.Builder builder = (new LootContext.Builder(world)).random(world.random).parameter(LootContextParameters.POSITION, pos).parameter(LootContextParameters.TOOL, stack).optionalParameter(LootContextParameters.THIS_ENTITY, entity).optionalParameter(LootContextParameters.BLOCK_ENTITY, blockEntity);
      return state.getDroppedStacks(builder);
   }*/

@Mixin(Block.class)
public class BlockMixin {
    @Inject(at = @At("TAIL"), method="Lnet/minecraft/block/Block;getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", cancellable = true)
    private static void getDroppedStacks(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> callbackInfo) {
        if (entity != null && EnchantmentHelper.getLevel(CoTG.GAMBLERS_CURSE, stack) != 0) {
            List<ItemStack> drops = callbackInfo.getReturnValue();
            if (Math.random() >= 0.5) {
                drops.addAll(drops);
            } else {
                drops = Collections.emptyList();
            }
            callbackInfo.setReturnValue(drops);
        }
    }
}
