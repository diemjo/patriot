package moe.karpador.patriot.items;

import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.Mana;
import moe.karpador.patriot.mana.ManaProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMeguminPantsu extends ItemPantsu {
    public ItemMeguminPantsu(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood, "item_megumin_pantsu");
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
