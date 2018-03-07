package moe.karpador.patriot;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static ItemPatriot itemPatriot;
    public static ItemMeguminStaff itemMeguminStaff;

    private static List<Item> items = new ArrayList<>();

    public static void init() {
        itemPatriot = new ItemPatriot(Item.ToolMaterial.DIAMOND);
        itemPatriot.setRegistryName(new ResourceLocation(Patriot.MODID, ItemPatriot.NAME));
        items.add(itemPatriot);

        itemMeguminStaff = new ItemMeguminStaff();
        itemMeguminStaff.setRegistryName(new ResourceLocation(Patriot.MODID, ItemMeguminStaff.NAME));
        items.add(itemMeguminStaff);
    }

    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher) {
        ModelResourceLocation modelItemPatriot = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemPatriot.NAME), "inventory");
        ModelLoader.registerItemVariants(itemPatriot, modelItemPatriot);
        mesher.register(itemPatriot, 0, modelItemPatriot);

        ModelResourceLocation modelItemMeguminStaff = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemMeguminStaff.NAME), "inventory");
        ModelLoader.registerItemVariants(itemMeguminStaff, modelItemMeguminStaff);
        mesher.register(itemMeguminStaff, 0, modelItemMeguminStaff);
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        items.forEach(item -> event.getRegistry().register(item));
    }

}
