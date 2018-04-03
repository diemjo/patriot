package moe.karpador.patriot.items;

import moe.karpador.patriot.Patriot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemMeguminStaffCore extends Item {
    public static final String NAME = "item_megumin_staff_core";

    public ItemMeguminStaffCore() {
        setMaxStackSize(1);
        setCreativeTab(Patriot.PATRIOT_TAB);
        setRegistryName(new ResourceLocation(Patriot.MODID, NAME));
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:item_megumin_staff_core
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:item_megumin_staff_core
    }
}
