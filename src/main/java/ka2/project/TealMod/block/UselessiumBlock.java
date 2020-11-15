package ka2.project.TealMod.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ka2.project.TealMod.BlockRegistry;
import ka2.project.TealMod.ItemRegistry;
import ka2.project.TealMod.TealMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class UselessiumBlock extends Block {
	public UselessiumBlock() {
		super(Properties.create(Material.ROCK).hardnessAndResistance(1.1f).setRequiresTool());
	}


	
@Override
public List<ItemStack> getDrops(BlockState state, Builder builder) {
	List<ItemStack> drops=new ArrayList<ItemStack>();
	drops.add(ItemRegistry.uselessium.get().getDefaultInstance());
	return drops;
		// TODO Auto-generated method stub
//	return super.getDrops(state, builder);
}
	
	/*@Override
	
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!worldIn.isRemote()) {
			Random rnd = new Random();
			
			
			int tc = 3;
			for (int i = 0; i < tc; i += 1) {
				int nx = rnd.nextInt(10) - 5;
				int ny = rnd.nextInt(10) - 5;
				int nz = rnd.nextInt(10) - 5;

				if (worldIn.isAirBlock(pos.add(nx, ny, nz))) {
					worldIn.setBlockState(pos.add(nx, ny, nz), state);
				}

			}
		
		}

		// TODO Auto-generated method stub
		// super.onBlockHarvested(worldIn, pos, state, player);
	}
	*/
	
	@Override
	public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
		
		if (!worldIn.isRemote()) {
		//	TealMod.logger.debug(explosionIn.getExplosivePlacedBy().getEntityString());
			int tc = 3;
			if(explosionIn.getExplosivePlacedBy() instanceof CreeperEntity) {
				TealMod.logger.info("IT WAS A CREEPER");
				tc=10;
			}
			
			if(explosionIn.getExplosivePlacedBy() instanceof WitherEntity) {
				TealMod.logger.info("Wither did it");
				
			//	(WitherEntity)(explosionIn.getExplosivePlacedBy()).
			//	explosionIn.getExplosivePlacedBy().heal(-1);
				tc=20;
			}
			
		//	TealMod.logger.debug(explosionIn.getExplosivePlacedBy().getEntityString().);
			Random rnd = new Random();
			
			for (int i = 0; i < tc; i += 1) {
				int nx = rnd.nextInt(10) - 5;
				int ny = rnd.nextInt(10) - 5;
				int nz = rnd.nextInt(10) - 5;

				if (worldIn.isAirBlock(pos.add(nx, ny, nz))) {
				
					worldIn.setBlockState(pos.add(nx, ny, nz), BlockRegistry.uselessium_block.get().getDefaultState());
				}
				else {
				//	explosionIn.getExplosivePlacedBy().heal(-1);
				//	worldIn.createExplosion(null, pos.getX()+nx, pos.getY()+ny, pos.getZ()+nz, 15, Mode.NONE);
				}
				
			}
		}
		
	}

}
