package ka2.project.TealMod.tileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ka2.project.TealMod.TealMod;
import ka2.project.TealMod.TileEntityTypeRegistry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
public class FallGeneratorTileEntity extends TileEntity{

	protected EnergyStorage ES=new EnergyStorage(100000, 100000, 100000);
	

    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
	
    
 //   @CapabilityInject(IEnergyStorage.class)
//    static Capability<IEnergyStorage> ENERGY_STORAGE_CAPABILITY = null;
    
 
    
	public FallGeneratorTileEntity() {	
		super(TileEntityTypeRegistry.fallGeneratorTileEntity.get());

		energy.ifPresent(e->{
		//ES=(EnergyStorage) e;			
			
		});
			
		
		//ES= 
		// TODO Auto-generated constructor stub
	}
	
	private IEnergyStorage createEnergy() {
		return ES;
	}
	
	   @Override
	    public void remove() {
	        super.remove();
	        energy.invalidate();
	    }

	   
	public int generate(float distance, int type) {
		world.getClosestPlayer(pos.getX(),pos.getY(),pos.getZ(), 100,null).sendStatusMessage(ITextComponent.getTextComponentOrEmpty("GENERATING?"), false);;
		
		int inc=(int)(distance*type);
		
		world.getClosestPlayer(pos.getX(),pos.getY(),pos.getZ(), 100,null).sendStatusMessage(ITextComponent.getTextComponentOrEmpty(inc+""), false);;
		
		
		TealMod.logger.debug("GENERATED "+inc+"\n(or less if that exceds capacity)\n");
		
		return ES.receiveEnergy(inc, false);
	}

	public int getEnergyStored() {
	
		// TODO Auto-generated method stub
		return ES.getEnergyStored();
	}

	public int getMaxEnergyStored() {
		// TODO Auto-generated method stub
		return ES.getMaxEnergyStored();
	}
	

	

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
       /* if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }*/
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

}
