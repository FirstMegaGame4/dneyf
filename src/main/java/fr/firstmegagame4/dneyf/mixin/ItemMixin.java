package fr.firstmegagame4.dneyf.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemMixin {

	@Shadow
	public abstract Item getItem();

	@Inject(method = "use", at = @At("HEAD"))
	private void doNotEatMe(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
		if (!world.isClient() && (
			this.getItem().equals(Items.POTATO)
			|| this.getItem().equals(Items.BAKED_POTATO)
			|| this.getItem().equals(Items.POISONOUS_POTATO_FRIES)
			|| this.getItem().equals(Items.POISONOUS_POTATO_STICKS)
			|| this.getItem().equals(Items.POISONOUS_POTATO_SLICES)
			|| this.getItem().equals(Items.POISONOUS_POTATO_CHIPS)
			|| this.getItem().equals(Items.HOT_POTATO)
			|| this.getItem().equals(Items.POISONOUS_POTATO)
			|| this.getItem().equals(Items.GOLDEN_POISONOUS_POTATO)
			|| this.getItem().equals(Items.SNEKTATO)
		))
		{
			user.sendMessage(Text.translatableWithFallback("message.dneyf.do_not_eat_me", "Please, do not eat me, I am your friend! ;("));
		}
	}
}
