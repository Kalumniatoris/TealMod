package ka2.project.TealMod.block;

import java.util.Random;

import javax.annotation.Nullable;

import ka2.project.TealMod.TealMod;
import ka2.project.TealMod.tileEntity.ReturnerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ReturnerBlock extends Block {
	// private static IntegerProperty STATE = IntegerProperty.create("powered", 0,
	// 1);
	 public static final BooleanProperty TRIGGERED = BooleanProperty.create("triggered");

	public ReturnerBlock() {
		super(Properties.create(Material.IRON));
		this.setDefaultState(this.stateContainer.getBaseState().with(TRIGGERED, false));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(TRIGGERED);
		super.fillStateContainer(builder);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, Direction side) {
		// TODO Auto-generated method stub
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ReturnerTileEntity();
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		// TODO Auto-generated method stub
		if (!worldIn.isRemote() && handIn == Hand.MAIN_HAND) {
			ReturnerTileEntity rte = (ReturnerTileEntity) worldIn.getTileEntity(pos);
			rte.clickedOn(player);
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		//boolean powered = worldIn.isBlockPowered(pos);
		
		// boolean flag1 = state.get(TRIGGERED);

		// if(this.getStateContainer().getProperty("powered")==1) {}
		
		 boolean powered = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
	      boolean flag1 = state.get(TRIGGERED);
	      if (powered && !flag1) {
	    	  
	    	 this.summon(worldIn,pos);
	    	  
	        // worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
	         worldIn.setBlockState(pos, state.with(TRIGGERED, Boolean.valueOf(true)), 1);
	         
	         
	      } else if (!powered && flag1) {
	         worldIn.setBlockState(pos, state.with(TRIGGERED, Boolean.valueOf(false)), 1);
	      }
	      else {
	    	  //worldIn.setBlockState(pos, state.with(TRIGGERED, Boolean.valueOf(false)), 1);
		      
	    	  
	      }
	      
		
		
	
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		//summon(worldIn, pos);
		// TODO Auto-generated method stub
		//super.tick(state, worldIn, pos, rand);
	}
	
	private void summon(World worldIn,BlockPos pos) {
	if (!worldIn.isRemote()) {
			
			
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
			ReturnerTileEntity rte = (ReturnerTileEntity) worldIn.getTileEntity(pos);

			TealMod.logger.debug("attempting teleport");

			rte.getPlayers().forEach((PlayerEntity p) -> {

				if (p.getEntityWorld() != worldIn) {

				}
				if (!p.isSneaking()) {
					p.teleportKeepLoaded(pos.getX() + 0.5, pos.getY() + 2, pos.getZ() + 0.5);
				}
			});

		} 
		
	}
}
