package ka2.project.TealMod.tileEntity;

import java.util.HashSet;
import java.util.UUID;

import ka2.project.TealMod.TealMod;
import ka2.project.TealMod.TileEntityTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;

public class ReturnerTileEntity extends TileEntity {

	//private HashSet<PlayerEntity> connectedPlayers = new HashSet<PlayerEntity>();;
	
	private HashSet<UUID> connectedUUIDs = new HashSet<UUID>();

	public ReturnerTileEntity() {

		super(TileEntityTypeRegistry.returnerTileEntity.get());
		// TODO Auto-generated constructor stub
	}

	public void clickedOn(PlayerEntity player) {
		if(!player.isSneaking()) {
		this.addPlayer(player);}
		else {
		this.removePlayer(player);}
		markDirty();
	}

	public void addPlayer(PlayerEntity player) {
		//markDirty();
	//	connectedPlayers.add(player);
		connectedUUIDs.add(player.getUniqueID());

		// TODO to language
		player.sendStatusMessage(ITextComponent.getTextComponentOrEmpty("Registered in returner \n Click again while sneaking to unregister yourself"), false);
		TealMod.logger.info("added " + player.getName() + " to returner");

	}
	public void removePlayer(PlayerEntity player) {
		
		connectedUUIDs.remove(player.getUniqueID());
		
		player.sendStatusMessage(ITextComponent.getTextComponentOrEmpty("Unregistered from returner"), false);
		TealMod.logger.info("removed " + player.getName() + " to returner");
	}
	public HashSet<PlayerEntity> getPlayers() {
		HashSet<PlayerEntity> connectedPlayers = new HashSet<PlayerEntity>();
		
		this.connectedUUIDs.forEach((u)->{
			
			if(world.getPlayerByUuid(u)!=null) {connectedPlayers.add(world.getPlayerByUuid(u));}
			});
		
		return connectedPlayers;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		//if(!world.isRemote()) {
		
			//nbt.getList("uids",0);
			TealMod.logger.debug("\n\n\n\n=========\n\nQWERTY\\n\n\n");
			TealMod.logger.debug(nbt.toString());
		
			//ListNBT uids=nbt.getList("uids",0);
			
			ListNBT uids=(ListNBT) nbt.get("uids");
			TealMod.logger.debug("ABAKUS  "+uids.toString());
			
			uids.forEach((e)->{
				CompoundNBT tmp=(CompoundNBT) e;
				TealMod.logger.debug("><><><><"+tmp.getUniqueId("player").toString());
				this.connectedUUIDs.add(tmp.getUniqueId("player"));
				
			});
	//	}		
		
		super.read(state, nbt);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
	/*	if(world.isRemote()) {
			return super.write(compound);
		}*/
		
		TealMod.logger.debug("saving returner");
	
		
		ListNBT uids = new ListNBT();

		connectedUUIDs.forEach((UUID u) -> {
			CompoundNBT tmp = new CompoundNBT();
			tmp.putUniqueId("player", u);
			uids.add(tmp);
			TealMod.logger.debug(u.toString() + " saved?\n\n");
			
		});
			
	
		// TODO Auto-generated method stub
		TealMod.logger.debug(uids.toString());
		TealMod.logger.debug("^^^^^\n\n\n\n\n");
		compound.put("uids", uids);
		//compound.put
		compound.putString("test", "TEKST");
		
		TealMod.logger.debug(compound.toString());
		return super.write(compound);
	}
}
