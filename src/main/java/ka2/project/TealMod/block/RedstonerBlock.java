package ka2.project.TealMod.block;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;

import ka2.project.TealMod.ItemRegistry;
import ka2.project.TealMod.TealMod;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RedstonerBlock extends Block {

	public RedstonerBlock() {
		super(Properties.create(Material.ROCK).hardnessAndResistance(3).tickRandomly());

		// TODO Auto-generated constructor stub
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		// worldIn.addBlockEvent(pos.add(0, 1, 0), state.getBlock(), 0, 0);

		if (worldIn.isAirBlock(pos.add(0, 1, 0))) {
			worldIn.setBlockState(pos.add(0, 1, 0), Blocks.REDSTONE_WIRE.getDefaultState());
			if (random.nextFloat() > 0.999) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}

		// worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 32,
		// true).attemptTeleport(pos.getX(), pos.getY()+1, pos.getZ(),true);

	}


		

}
