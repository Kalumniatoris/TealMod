package ka2.project.TealMod;

import ka2.project.TealMod.tileEntity.FallGeneratorTileEntity;
import ka2.project.TealMod.tileEntity.PlatformerTileEntity;
import ka2.project.TealMod.tileEntity.ReturnerTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeRegistry {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ka2.project.TealMod.TealMod.MODID);


public static void init() {
    TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    
}


public static final RegistryObject<TileEntityType<ReturnerTileEntity>> returnerTileEntity = TILE_ENTITIES.register("returner_tileentity", ()->TileEntityType.Builder.create(ReturnerTileEntity::new, BlockRegistry.returner.get()).build(null));
public static final RegistryObject<TileEntityType<PlatformerTileEntity>> platformerTileEntity = TILE_ENTITIES.register("platformer_tileentity", ()->TileEntityType.Builder.create(PlatformerTileEntity::new, BlockRegistry.platformer.get()).build(null));
//public static final RegistryObject<TileEntityType<AdvancedReturnerTileEntity>> AdvancedreturnerTileEntity = TILE_ENTITIES.register("advanced_returner_tileentity", ()->TileEntityType.Builder.create(AdvancedReturnerTileEntity::new, BlockRegistry.advancedReturner.get()).build(null));

public static final RegistryObject<TileEntityType<FallGeneratorTileEntity>> fallGeneratorTileEntity=TILE_ENTITIES.register("fallgenerator_tileentity", ()->TileEntityType.Builder.create(FallGeneratorTileEntity::new, BlockRegistry.fall_generator.get()).build(null));
}
