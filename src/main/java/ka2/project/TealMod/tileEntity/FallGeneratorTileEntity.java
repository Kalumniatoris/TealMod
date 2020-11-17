package ka2.project.TealMod.tileEntity;

import ka2.project.TealMod.TealMod;
import ka2.project.TealMod.TileEntityTypeRegistry;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.energy.IEnergyStorage;

public class FallGeneratorTileEntity extends TileEntity implements IEnergyStorage {
	protected int capacity=10000;
	protected int currentEnergy;
	public FallGeneratorTileEntity() {
		super(TileEntityTypeRegistry.fallGeneratorTileEntity.get());
		// TODO Auto-generated constructor stub
	}

	public int generate(float distance, int type) {
		int inc=(int)(distance*type);
		TealMod.logger.debug("GENERATED "+inc+"\n(or less if that exceds capacity)\n");
		this.currentEnergy=Math.min(this.capacity, this.currentEnergy+inc);
		return (int) (distance*type);
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnergyStored() {
		// TODO Auto-generated method stub
		return this.currentEnergy;
	}

	@Override
	public int getMaxEnergyStored() {
		// TODO Auto-generated method stub
		return this.capacity;
	}

	@Override
	public boolean canExtract() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canReceive() {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
