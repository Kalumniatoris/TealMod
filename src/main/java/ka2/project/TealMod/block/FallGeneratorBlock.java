package ka2.project.TealMod.block;

import javax.annotation.Nullable;

import ka2.project.TealMod.TealMod;
import ka2.project.TealMod.tileEntity.FallGeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;



public class FallGeneratorBlock extends Block {

	
	public FallGeneratorBlock() {
		super(Properties.create(Material.IRON).hardnessAndResistance(0.6f));

		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasTileEntity(BlockState state) {

		return true;

	}
	
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FallGeneratorTileEntity();}

	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		// TODO Auto-generated method stub
		if(!worldIn.isRemote()) {
		if (entityIn instanceof FallingBlockEntity) {
			TealMod.logger.debug("fbe falled on: ");
			TealMod.logger.debug("what? : "+((FallingBlockEntity) entityIn).getBlockState().toString());
			FallGeneratorTileEntity fgte = (FallGeneratorTileEntity) worldIn.getTileEntity(pos);
			fgte.generate(fallDistance, 100);
			
		}}
		
		//super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		
		if(!worldIn.isRemote()&&handIn==Hand.MAIN_HAND) {
			FallGeneratorTileEntity fgte = (FallGeneratorTileEntity) worldIn.getTileEntity(pos);
			TealMod.logger.debug("Current energy: "+fgte.getEnergyStored());
			
			player.sendStatusMessage(ITextComponent.getTextComponentOrEmpty(""+fgte.getEnergyStored()+"/"+fgte.getMaxEnergyStored()), false);
			
		}
		
		// TODO Auto-generated method stub
		return ActionResultType.PASS;
	}
	/*
	@Override
	public void onLanded(IBlockReader worldIn, Entity entityIn) {
		// TODO Auto-generated method stub

		if (entityIn instanceof FallingBlockEntity) {
		
			// FallGeneratorTileEntity fgte = worldIn.getTileEntity()

		}

		// super.onLanded(worldIn, entityIn);
	}
	*/

}
