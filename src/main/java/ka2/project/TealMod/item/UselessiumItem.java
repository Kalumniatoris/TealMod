package ka2.project.TealMod.item;

import ka2.project.TealMod.group.ModGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UselessiumItem extends Item {

	public UselessiumItem() {
		super(new Properties().group(ModGroup.ITEM_GROUP).maxStackSize(3).isImmuneToFire());

		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
		// TODO Auto-generated method stub
		player.addItemStackToInventory(item);
		return false;
		// return super.onDroppedByPlayer(item, player);
	}
	
	
}
