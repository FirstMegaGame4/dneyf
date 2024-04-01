package fr.firstmegagame4.dneyf.mixin;

import net.minecraft.class_9538;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_9538.class)
public class class_9538Mixin {

	@Inject(method = "getMaxUseTime", at = @At("HEAD"), cancellable = true)
	private void cancelMaxUseTime(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(0);
	}

	@Inject(method = "use", at = @At("HEAD"), cancellable = true)
	private void cancelUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
		user.setCurrentHand(hand);
		cir.setReturnValue(TypedActionResult.fail(user.getStackInHand(hand)));
	}

	@Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
	private void cancelUseAction(ItemStack stack, CallbackInfoReturnable<UseAction> cir) {
		cir.setReturnValue(UseAction.NONE);
	}

	@Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
	private void cancelFinishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
		cir.setReturnValue(stack);
	}
}
