package moe.karpador.patriot.items;

import moe.karpador.patriot.Patriot;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static ItemPatriot itemPatriot;
    public static ItemToiletPaper itemToiletPaper;
    public static ItemTissueBox itemTissueBox;
    public static ItemMeguminStaff itemMeguminStaff;
    public static ItemMagicCloth itemMagicCloth;
    public static ItemMagicCore itemMagicCore;
    public static ItemMagicCoreOfExplosion  itemMagicCoreOfExplosion;

    public static ItemArmor.ArmorMaterial meguminHatMaterial = EnumHelper.addArmorMaterial("megumin_hat_material", Patriot.RESOURCE_PREFIX +"megumin_hat",4,new int[] {-1,-1,-1,-1}, 9, SoundEvents.ENTITY_ZOMBIE_AMBIENT,2.0F);
    public static ItemArmor.ArmorMaterial meguminClothsMaterial = EnumHelper.addArmorMaterial("megumin_cloths_material",Patriot.RESOURCE_PREFIX +"megumin_cloths",4,new int[] {-1,-1,-1,-1}, 9, SoundEvents.ENTITY_ZOMBIE_AMBIENT,2.0F);
    public static ItemMeguminCloths itemMeguminHat;
    public static ItemMeguminCloths itemMeguminChest;
    public static ItemMeguminCloths itemMeguminSkirt;
    public static ItemMeguminCloths itemMeguminShoes;
    public static MeguminHatModel meguminHatModel;
    public static ItemGenericPantsu itemGenericPantsu;
    public static ItemMeguminPantsu itemMeguminPantsu;
    public static ItemStealMagic itemStealMagic;

    private static List<Item> items = new ArrayList<>();

    public static void init() {
        itemPatriot = new ItemPatriot(Item.ToolMaterial.DIAMOND);
        items.add(itemPatriot);

        itemToiletPaper = new ItemToiletPaper();
        items.add(itemToiletPaper);

        itemTissueBox = new ItemTissueBox();
        items.add(itemTissueBox);

        itemMeguminStaff = new ItemMeguminStaff();
        items.add(itemMeguminStaff);

        itemMagicCloth = new ItemMagicCloth();
        items.add(itemMagicCloth);

        itemMagicCore = new ItemMagicCore();
        items.add(itemMagicCore);

        itemMagicCoreOfExplosion = new ItemMagicCoreOfExplosion();
        items.add(itemMagicCoreOfExplosion);

        itemMeguminHat = new ItemMeguminCloths(meguminHatMaterial, 1, EntityEquipmentSlot.HEAD, "armor_megumin_hat");
        itemMeguminChest = new ItemMeguminCloths(meguminClothsMaterial, 1, EntityEquipmentSlot.CHEST, "armor_megumin_chest");
        itemMeguminSkirt = new ItemMeguminCloths(meguminClothsMaterial, 2, EntityEquipmentSlot.LEGS, "armor_megumin_skirt");
        itemMeguminShoes = new ItemMeguminCloths(meguminClothsMaterial, 1, EntityEquipmentSlot.FEET, "armor_megumin_shoes");
        items.add(itemMeguminHat);
        items.add(itemMeguminChest);
        items.add(itemMeguminSkirt);
        items.add(itemMeguminShoes);

        itemGenericPantsu = new ItemGenericPantsu(0, 0, false);
        items.add(itemGenericPantsu);
        itemMeguminPantsu = new ItemMeguminPantsu(0,0,false);
        items.add(itemMeguminPantsu);

        itemStealMagic = new ItemStealMagic();
        items.add(itemStealMagic);
    }

    @SideOnly(Side.CLIENT)
    public static void initClient(ItemModelMesher mesher) {
        ModelResourceLocation modelItemPatriot = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemPatriot.NAME), "inventory");
        ModelLoader.registerItemVariants(itemPatriot, modelItemPatriot);
        mesher.register(itemPatriot, 0, modelItemPatriot);

        ModelResourceLocation modelItemToiletPaper = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemToiletPaper.NAME), "inventory");
        ModelLoader.registerItemVariants(itemToiletPaper, modelItemToiletPaper);
        mesher.register(itemToiletPaper, 0, modelItemToiletPaper);

        ModelResourceLocation modelItemTissueBox = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemTissueBox.NAME), "inventory");
        ModelLoader.registerItemVariants(itemTissueBox, modelItemTissueBox);
        mesher.register(itemTissueBox, 0, modelItemTissueBox);

        ModelResourceLocation modelItemMeguminStaff = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemMeguminStaff.NAME), "inventory");
        ModelLoader.registerItemVariants(itemMeguminStaff, modelItemMeguminStaff);
        mesher.register(itemMeguminStaff, 0, modelItemMeguminStaff);

        ModelResourceLocation modelItemMagicCloth = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemMagicCloth.NAME), "inventory");
        ModelLoader.registerItemVariants(itemMagicCloth, modelItemMagicCloth);
        mesher.register(itemMagicCloth, 0, modelItemMagicCloth);

        ModelResourceLocation modelItemMagicCore = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemMagicCore.NAME), "inventory");
        ModelLoader.registerItemVariants(itemMagicCore, modelItemMagicCore);
        mesher.register(itemMagicCore, 0, modelItemMagicCore);

        ModelResourceLocation modelItemMagicCoreOfExplosion = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, ItemMagicCoreOfExplosion.NAME), "inventory");
        ModelLoader.registerItemVariants(itemMagicCoreOfExplosion, modelItemMagicCoreOfExplosion);
        mesher.register(itemMagicCoreOfExplosion, 0, modelItemMagicCoreOfExplosion);


        registerArmor(itemMeguminHat,mesher);
        registerArmor(itemMeguminChest,mesher);
        registerArmor(itemMeguminSkirt,mesher);
        registerArmor(itemMeguminShoes,mesher);
        meguminHatModel = new MeguminHatModel();

        initItemClient(itemGenericPantsu, itemGenericPantsu.NAME, mesher);
        initItemClient(itemStealMagic, itemStealMagic.NAME, mesher);
        initItemClient(itemMeguminPantsu, itemMeguminPantsu.NAME, mesher);
    }

    @SideOnly(Side.CLIENT)
    private static void initItemClient(Item item, String itemName, ItemModelMesher mesher) {
        ModelResourceLocation modelItem = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, itemName), "inventory");
        ModelLoader.registerItemVariants(item, modelItem);
        mesher.register(item, 0, modelItem);
    }

    @SideOnly(Side.CLIENT)
    private static void registerArmor(ItemMeguminCloths item, ItemModelMesher mesher) {
        ModelResourceLocation modelItem = new ModelResourceLocation(String.format("%s%s", Patriot.RESOURCE_PREFIX, item.name), "inventory");
        ModelLoader.registerItemVariants(item, modelItem);
        mesher.register(item, 0, modelItem);
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        items.forEach(registry::register);
    }

}
