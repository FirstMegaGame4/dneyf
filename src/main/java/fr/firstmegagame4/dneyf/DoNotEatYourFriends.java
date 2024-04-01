package fr.firstmegagame4.dneyf;

import fr.firstmegagame4.dneyf.mixin.ItemAccessor;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoNotEatYourFriends implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("dneyf");

	@Override
	public void onInitialize() {
		LOGGER.info("Don't eat potatoes you murderer!");
	}

	public static Item resetSettings(Item item, Item.Settings settings) {
		ItemAccessor itemAccessor = (ItemAccessor) item;
		ItemAccessor.SettingsAccessor settingsAccessor = (ItemAccessor.SettingsAccessor) settings;
		itemAccessor.dneyf$setComponents(settingsAccessor.dneyf$getValidatedComponents());
		itemAccessor.dneyf$setRecipeRemainder(settingsAccessor.dneyf$getRecipeRemainder());
		itemAccessor.dneyf$setRequiredFeatures(settingsAccessor.dneyf$getRequiredFeatures());
		return (Item) itemAccessor;
	}
}
