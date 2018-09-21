package moe.karpador.patriot.items;

import moe.karpador.patriot.Patriot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class ItemPantsu extends ItemFood {
    public final String NAME;

    /**
     * @param amount how much the food bar will be increased (10 means half of the bar)
     */
    public ItemPantsu(int amount, float saturation, boolean isWolfFood, String pantsuName) {
        super(amount, saturation, isWolfFood);
        this.NAME = pantsuName;
        setMaxStackSize(1);
        setCreativeTab(Patriot.PATRIOT_TAB);
        setRegistryName(new ResourceLocation(Patriot.MODID, NAME));
        setAlwaysEdible();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:NAME
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:NAME
    }
}
