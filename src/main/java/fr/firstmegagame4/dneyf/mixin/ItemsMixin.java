package fr.firstmegagame4.dneyf.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import fr.firstmegagame4.dneyf.DoNotEatYourFriends;
import net.minecraft.class_9539;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Items.class)
public class ItemsMixin {

	@WrapOperation(method = "register(Ljava/lang/String;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Lnet/minecraft/util/Identifier;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;"))
	private static Item dneyf(Identifier id, Item item, Operation<Item> original) {
		return switch (id.getPath()) {
			case "potato", "baked_potato", "poisonous_potato_fries", "poisonous_potato_sticks", "poisonous_potato_slices", "poisonous_potato_chips" -> original.call(id, DoNotEatYourFriends.resetSettings(item, new Item.Settings()));
			case "hot_potato" -> original.call(id, DoNotEatYourFriends.resetSettings(item, new Item.Settings().maxCount(1).fireproof().component(DataComponentTypes.HEAT, null)));
			case "poisonous_potato" -> original.call(id, DoNotEatYourFriends.resetSettings(item, new Item.Settings().maxCount(99)));
			case "golden_poisonous_potato" -> original.call(id, DoNotEatYourFriends.resetSettings(item, new Item.Settings().rarity(Rarity.RARE)));
			case "snektato" -> original.call(id, DoNotEatYourFriends.resetSettings(item, new Item.Settings().maxCount(16).component(DataComponentTypes.SNEK, new class_9539(false))));
			default -> original.call(id, item);
		};
	}
}
