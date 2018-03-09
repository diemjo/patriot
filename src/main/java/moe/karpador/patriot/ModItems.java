package moe.karpador.patriot;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static ItemPatriot itemPatriot;
    public static ItemMeguminStaff itemMeguminStaff;

    public static ItemArmor.ArmorMaterial meguminHatMaterial = EnumHelper.addArmorMaterial("megumin_hat_material",Patriot.MODID +":megumin_hat",4,new int[] {2,6,5,2}, 9, SoundEvents.ENTITY_ZOMBIE_AMBIENT,2.0F);
    public static ItemArmor.ArmorMaterial meguminClothsMaterial = EnumHelper.addArmorMaterial("megumin_cloths_material",Patriot.MODID +":megumin_cloths",4,new int[] {2,6,5,2}, 9, SoundEvents.ENTITY_ZOMBIE_AMBIENT,2.0F);
    public static ItemMeguminCloths itemMeguminHat;
    public static ItemMeguminCloths itemMeguminChest;
    public static ItemMeguminCloths itemMeguminSkirt;
    public static ItemMeguminCloths itemMeguminShoes;
    public static MeguminHatModel meguminHatModel;

    private static List<Item> items = new ArrayList<>();

    public static void init() {
        itemPatriot = new ItemPatriot(Item.ToolMaterial.DIAMOND);
        itemPatriot.setRegistryName(new ResourceLocation(Patriot.MODID, ItemPatriot.NAME));
        items.add(itemPatriot);

        itemMeguminStaff = new ItemMeguminStaff();
        itemMeguminStaff.setRegistryName(new ResourceLocation(Patriot.MODID, ItemMeguminStaff.NAME));
        items.add(itemMeguminStaff);

        itemMeguminHat = new ItemMeguminCloths(meguminHatMaterial, 1, EntityEquipmentSlot.HEAD, "armor_megumin_hat");
        itemMeguminChest = new ItemMeguminCloths(meguminClothsMaterial, 1, EntityEquipmentSlot.CHEST, "armor_megumin_chest");
        itemMeguminSkirt = new ItemMeguminCloths(meguminClothsMaterial, 2, EntityEquipmentSlot.LEGS, "armor_megumin_skirt");
        itemMeguminShoes = new ItemMeguminCloths(meguminClothsMaterial, 1, EntityEquipmentSlot.FEET, "armor_megumin_shoes");
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
        meguminHatModel = new MeguminHatModel();
    }

    private static void registerArmor(ItemMeguminCloths item, ItemModelMesher mesher) {
        ModelResourceLocation modelItem = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, item.name), "inventory");
        ModelLoader.registerItemVariants(item, modelItem);
        mesher.register(item, 0, modelItem);
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        items.forEach(item -> event.getRegistry().register(item));
    }

}