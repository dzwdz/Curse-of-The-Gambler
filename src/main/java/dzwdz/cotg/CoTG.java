package dzwdz.cotg;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CoTG implements ModInitializer {

	public static Enchantment GAMBLERS_CURSE = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("gamblers_curse", "gamblers_curse"),
			new GamblersCurse()
	);

	@Override
	public void onInitialize() {
	}
}
