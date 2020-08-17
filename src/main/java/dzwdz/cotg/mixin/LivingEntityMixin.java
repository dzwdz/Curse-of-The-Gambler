package dzwdz.cotg.mixin;

import dzwdz.cotg.CoTG;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collections;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Shadow
    protected void drop(DamageSource source) {};

    @Redirect(method = "onDeath",
              at = @At(value = "INVOKE", target="Lnet/minecraft/entity/LivingEntity;drop(Lnet/minecraft/entity/damage/DamageSource;)V"))
    private void drop(LivingEntity entity, DamageSource source) {
        Entity attacker = source.getAttacker();
        if (attacker instanceof PlayerEntity && EnchantmentHelper.getLevel(CoTG.GAMBLERS_CURSE, ((PlayerEntity) attacker).getMainHandStack()) != 0) {
            if (Math.random() >= 0.5) {
                drop(source);
                drop(source);
            }
        } else {
            drop(source);
        }
    }
}
