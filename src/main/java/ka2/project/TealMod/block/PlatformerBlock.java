package ka2.project.TealMod.block;

import javax.annotation.Nullable;

import ka2.project.TealMod.tileEntity.PlatformerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class PlatformerBlock extends Block {
	public PlatformerBlock() {
		super(Properties.create(Material.WOOD).hardnessAndResistance(1));
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new PlatformerTileEntity();
	}
	@Override
	public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
		// TODO Auto-generated method stub
		if(!world.isRemote()) {
		PlatformerTileEntity pte = (PlatformerTileEntity) world.getTileEntity(pos);
		pte.updateAte();
		}
		super.onNeighborChange(state, world, pos, neighbor);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isRemote()&& handIn == Hand.MAIN_HAND) {
		PlatformerTileEntity pte = (PlatformerTileEntity) worldIn.getTileEntity(pos);
		pte.platformerActivated();
		}
		
		return ActionResultType.PASS;
		// return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}

}
