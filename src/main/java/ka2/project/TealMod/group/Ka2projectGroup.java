package ka2.project.TealMod.group;


import ka2.project.TealMod.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Ka2projectGroup extends ItemGroup {

	public Ka2projectGroup() {
		super("ka2Project");
	
	}

	@Override
	public ItemStack createIcon() {
		// TODO Auto-generated method stub
		return new ItemStack(ItemRegistry.uselessium.get());
	}

}
