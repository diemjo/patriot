package moe.karpador.patriot.items;

import moe.karpador.patriot.Patriot;
import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.Mana;
import moe.karpador.patriot.mana.ManaProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemPantsu extends ItemFood {
    public static final String NAME = "item_pantsu";

    /**
     * @param amount how much the food bar will be increased (10 means half of the bar)nn
     */
    public ItemPantsu(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setMaxStackSize(1);
        setCreativeTab(Patriot.PATRIOT_TAB);
        setRegistryName(new ResourceLocation(Patriot.MODID, NAME));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:item_pantsu
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:item_pantsu
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        IMana mana = entityLiving.getCapability(ManaProvider.MANA_CAP, null);
        if(mana != null) {
            mana.setMana(Mana.maxMana);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
