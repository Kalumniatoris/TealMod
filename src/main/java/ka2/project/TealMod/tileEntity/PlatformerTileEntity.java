package ka2.project.TealMod.tileEntity;

import javax.annotation.Nullable;

import ka2.project.TealMod.TealMod;
import ka2.project.TealMod.TileEntityTypeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.functions.SetDamage;
import net.minecraft.tileentity.DispenserTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class PlatformerTileEntity extends TileEntity implements ITickableTileEntity {
	private int clim = 3;//10
	private int cou = 0;
	private int cx=0;
	private int cy=0;
	private int lim=1;
	//TODO EVERYTHING HERE
	public PlatformerTileEntity() {
		super(TileEntityTypeRegistry.platformerTileEntity.get());
	}
	
	
	@Nullable
	public TileEntity getTEAbove() {
		return this.world.getTileEntity(this.pos.add(0, 1, 0));
	}

	@SuppressWarnings("unused")
	private void build() {

	}
	private IInventory aTE = null;
	private int slotCount = 0;
	private int currentSlot = 0;
	private boolean isOn = false;
	BlockPos target = new BlockPos(this.getPos());
	public void platformerActivated() {
		lim=1;
		cx=0;
		cy=1;
		
		
		target=this.getPos();
		TealMod.logger.debug("Platformer clicked");
		if(world.isRemote()) {
		}
		if (!world.isRemote()) {
			if (this.getTEAbove() == null) {
				TealMod.logger.debug("getTEAbove returns null");
				return;
			}

			this.setaTE((IInventory) this.getTEAbove());
			this.slotCount = this.getaTE().getSizeInventory();
			currentSlot = 0;

			this.isOn = !this.isOn;
			TealMod.logger.debug("isOn " + this.isOn);
		}
	}

	public void updateAte() {
		// TODO Auto-generated method stub
		if (!world.isRemote()) {
			if ((IInventory) this.getTEAbove() != this.getaTE()) {
				this.setaTE((IInventory) this.getTEAbove());
			}
		}
	}

	@Override
	public void tick() {
		/*if (world.isRemote()) {
			return;
		}*/
		this.cou+=1;
		if (this.cou > this.clim) {
			this.cou = 0;
		}
		if (this.cou == this.clim && this.isOn) {

			TealMod.logger
					.debug((this.getaTE() != null) + " " + this.isOn + " " + (this.cou == this.clim) + "" + this.cou);

			if ((this.getaTE() != null) && this.isOn) {
				TealMod.logger.debug("spam");
				TealMod.logger.debug(this.getaTE().toString());
				TealMod.logger.debug(this.currentSlot);
				TealMod.logger.debug(this.slotCount);
				// this.cou = 0;
				ItemStack stos = aTE.getStackInSlot(currentSlot);
				if (stos == null || aTE.getStackInSlot(currentSlot).getCount() < 1) {
					TealMod.logger.debug("next?");
					this.currentSlot += 1;

					if (currentSlot + 1 > slotCount) {
						this.currentSlot = 0;
						this.isOn = false;

					}
					//this.cou += 1;
					stos = aTE.getStackInSlot(currentSlot);
					return;
				}
				stos = aTE.getStackInSlot(currentSlot);
				if (stos.getItem() instanceof BlockItem) {
					TealMod.logger.debug("block");
					target = target.add(0, 0, 1);
					if((target.getZ()-pos.getZ())>lim) {
						
						target=target.add(1, 0, -target.getZ()+pos.getZ());
						lim+=1;
					}
					if (world.isAirBlock(target)) {
						
						TealMod.logger.debug(stos.toString());
						BlockState tmp = Block.getBlockFromItem(stos.getItem()).getDefaultState();
						TealMod.logger.debug(tmp.toString());
						TealMod.logger.debug(target.toString());

						world.setBlockState(target, tmp);
						markDirty();
						this.getaTE().decrStackSize(currentSlot, 1);

					}
					else {
						TealMod.logger.debug(target.toString());
						TealMod.logger.debug("not air "+world.getBlockState(target).toString());
					}

				} else {
					this.currentSlot += 1;
				}
			}
		}
		// TODO Auto-generated method stub

		//this.cou += 1;
	}

	public IInventory getaTE() {
		return aTE;
	}

	public void setaTE(IInventory aTE) {
		this.aTE = aTE;
	}

}
