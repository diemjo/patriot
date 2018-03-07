package moe.karpador.patriot;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemMeguminCloths extends ItemArmor {
    public String name;

    public ItemMeguminCloths(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String itemName) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        name = itemName;
        setCreativeTab(Patriot.PATRIOT_TAB);
        //this.setUnlocalizedName(ItemMeguminCloths.name);
        setRegistryName(new ResourceLocation(Patriot.MODID, name));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + Patriot.RESOURCE_PREFIX + name; //item.patriot:armor_megumin_hat
    }
}
