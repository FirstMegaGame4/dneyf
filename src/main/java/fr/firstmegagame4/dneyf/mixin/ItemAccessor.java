package fr.firstmegagame4.dneyf.mixin;

import net.minecraft.component.ComponentMap;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureSet;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Item.class)
public interface ItemAccessor {

	@Accessor("components")
	void dneyf$setComponents(ComponentMap components);

	@Accessor("recipeRemainder")
	void dneyf$setRecipeRemainder(@Nullable Item recipeRemainder);

	@Accessor("requiredFeatures")
	void dneyf$setRequiredFeatures(FeatureSet requiredFeatures);

	@Mixin(Item.Settings.class)
	interface SettingsAccessor {

		@Invoker("getValidatedComponents")
		ComponentMap dneyf$getValidatedComponents();

		@Accessor("recipeRemainder")
		Item dneyf$getRecipeRemainder();

		@Accessor("requiredFeatures")
		FeatureSet dneyf$getRequiredFeatures();
	}
}
