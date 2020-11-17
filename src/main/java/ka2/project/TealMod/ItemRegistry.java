package ka2.project.TealMod;

import ka2.project.TealMod.group.ModGroup;
import ka2.project.TealMod.item.UselessiumItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
	

public class ItemRegistry {
public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,ka2.project.TealMod.TealMod.MODID );

public static void init() {
    ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    
}

public static final RegistryObject<Item> uselessium = ITEMS.register("uselessium", UselessiumItem::new);
public static final RegistryObject<Item> redstoner = ITEMS.register("redstoner", ()->new BlockItem(BlockRegistry.redstoner.get(), new Item.Properties().group(ModGroup.ITEM_GROUP)));
public static final RegistryObject<Item> returner=ITEMS.register("returner", ()->new BlockItem(BlockRegistry.returner.get(),new Item.Properties().group(ModGroup.ITEM_GROUP)));
public static final RegistryObject<Item> uselessium_block=ITEMS.register("uselessium_block", ()->new BlockItem(BlockRegistry.uselessium_block.get(),new Item.Properties().group(ModGroup.ITEM_GROUP)));


public static final RegistryObject<Item> platformer=ITEMS.register("platformer", ()->new BlockItem(BlockRegistry.platformer.get(),new Item.Properties().group(ModGroup.ITEM_GROUP)));

public static final RegistryObject<Item> fallgenerator=ITEMS.register("fallgenerator", ()->new BlockItem(BlockRegistry.fall_generator.get(),new Item.Properties().group(ModGroup.ITEM_GROUP)));
}
