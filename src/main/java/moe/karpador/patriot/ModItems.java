package moe.karpador.patriot;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static ItemPatriot itemPatriot;
    public static ItemMeguminStaff itemMeguminStaff;

    public static ItemArmor.ArmorMaterial meguminMaterial = ItemArmor.ArmorMaterial.LEATHER; // EnumHelper.addArmorMaterial()
    public static ItemMeguminCloths itemMeguminHat;
    public static ItemMeguminCloths itemMeguminChest;
    public static ItemMeguminCloths itemMeguminSkirt;
    public static ItemMeguminCloths itemMeguminShoes;

    public static List<Item> items = new ArrayList<>();

    public static void init() {
        itemPatriot = new ItemPatriot();
        itemPatriot.setRegistryName(new ResourceLocation(Patriot.MODID, ItemPatriot.NAME));
        items.add(itemPatriot);

        itemMeguminStaff = new ItemMeguminStaff();
        itemMeguminStaff.setRegistryName(new ResourceLocation(Patriot.MODID, ItemMeguminStaff.NAME));
        items.add(itemMeguminStaff);

        itemMeguminHat = new ItemMeguminCloths(meguminMaterial, 1, EntityEquipmentSlot.HEAD, "armor_megumin_hat");
        itemMeguminChest = new ItemMeguminCloths(meguminMaterial, 1, EntityEquipmentSlot.CHEST, "armor_megumin_chest");
        itemMeguminSkirt = new ItemMeguminCloths(meguminMaterial, 2, EntityEquipmentSlot.LEGS, "armor_megumin_skirt");
        itemMeguminShoes = new ItemMeguminCloths(meguminMaterial, 1, EntityEquipmentSlot.FEET, "armor_megumin_shoes");
        items.add(itemMeguminHat);
        items.add(itemMeguminChest);
        items.add(itemMeguminSkirt);
        items.add(itemMeguminShoes);
    }

    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher) {
        ModelResourceLocation modelItemPatriot = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemPatriot.NAME), "inventory");
        ModelLoader.registerItemVariants(itemPatriot, modelItemPatriot);
        mesher.register(itemPatriot, 0, modelItemPatriot);

        ModelResourceLocation modelItemMeguminStaff = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemMeguminStaff.NAME), "inventory");
        ModelLoader.registerItemVariants(itemMeguminStaff, modelItemMeguminStaff);
        mesher.register(itemMeguminStaff, 0, modelItemMeguminStaff);

        registerArmor(itemMeguminHat,mesher);
        registerArmor(itemMeguminChest,mesher);
        registerArmor(itemMeguminSkirt,mesher);
        registerArmor(itemMeguminShoes,mesher);
    }

    private static void registerArmor(ItemMeguminCloths item, ItemModelMesher mesher) {
        //ModelResourceLocation modelItem = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, item.name), "inventory");
        //ModelLoader.registerItemVariants(item, modelItem);
        //ModelResourceLocation modelItem = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, item.name), "inventory");
        ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(Patriot.MODID,item.name));
        //mesher.register(item, 0, modelItem);
    }


}
