package ka2.project.TealMod.stuff;

import ka2.project.TealMod.TealMod;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullFunction;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class GenEner implements IEnergyStorage {

	protected int energy;
	protected int capacity;
	private int maxReceive;
	protected int maxExtract;

	public GenEner(int capacity) {
		this(capacity, capacity);
	}

	public GenEner(int capacity, int maxTransfer) {
		this(capacity, maxTransfer, 0);
	}

	public GenEner(int capacity, int maxExtract, int energy) {
		this.capacity = capacity;
		this.maxReceive=0;
		this.maxExtract = maxExtract;
		this.energy = Math.max(0, Math.min(capacity, energy));
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if (!canReceive())
			return 0;

		int energyReceived = Math.min(capacity - energy, Math.min(this.getMaxReceive(), maxReceive));
		if (!simulate)
			energy += energyReceived;
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (!canExtract())
			return 0;

		int energyExtracted = Math.min(energy, Math.min(this.getMaxExtract(), maxExtract));
		if (!simulate)
			energy -= energyExtracted;
		return energyExtracted;
	}

	@Override
	public int getEnergyStored() {
		return energy;
	}

	@Override
	public int getMaxEnergyStored() {
		return capacity;
	}

	@Override
	public boolean canExtract() {
		return this.getMaxExtract() > 0;
	}

	@Override
	public boolean canReceive() {
		return false;
	}

	public void generate(int ienergy) {

		this.energy = Math.min(this.capacity, this.energy + ienergy);
		return;
	}

	public void spreadEnergy(BlockPos pos,World world, int amount) {
		TealMod.logger.info("spreading "+amount);
		if(energy<=0) {
			TealMod.logger.info("noenergy");
			return;}
		//int out=amount;
    	for (Direction direction : Direction.values()) {
            TileEntity te = world.getTileEntity(pos.offset(direction));
            if (te != null) {
            	TealMod.logger.info("D:"+direction+" "+te.toString());
         boolean tmp=te.getCapability(CapabilityEnergy.ENERGY, direction).lazyMap( target->{
        	 	if(target.canReceive()) {
        	 		target.receiveEnergy(this.extractEnergy(amount,false),false);
        	 	}
        	 	return true;
            	}      
            	) != null;
            	
            }
            
    	
    	
    }return;}

	public int getMaxReceive() {
		return maxReceive;
	}

	public int getMaxExtract() {
		return maxExtract;
	}

	
    
}
