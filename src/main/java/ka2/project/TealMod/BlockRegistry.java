package ka2.project.TealMod;

import ka2.project.TealMod.block.FallGeneratorBlock;
import ka2.project.TealMod.block.PlatformerBlock;
import ka2.project.TealMod.block.RedstonerBlock;
import ka2.project.TealMod.block.ReturnerBlock;
import ka2.project.TealMod.block.UselessiumBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
	

public class BlockRegistry {
public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ka2.project.TealMod.TealMod.MODID);

public static void init() {
    // attach DeferredRegister to the event bus
    BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    
}
public static final RegistryObject<Block> redstoner = BLOCKS.register("redstoner",RedstonerBlock::new);
public static final RegistryObject<Block> returner = BLOCKS.register("returner", ReturnerBlock::new);
public static final RegistryObject<Block> uselessium_block=BLOCKS.register("uselessium_block", UselessiumBlock::new);

public static final RegistryObject<Block> platformer=BLOCKS.register("platformer",PlatformerBlock::new);


public static final RegistryObject<Block> fall_generator=BLOCKS.register("fallgenerator", FallGeneratorBlock::new);
}
