package moe.karpador.patriot.items;

import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.Mana;
import moe.karpador.patriot.mana.ManaProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGenericPantsu extends ItemPantsu {

    public ItemGenericPantsu(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn, "armor_generic_pantsu");
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        IMana mana = entityLiving.getCapability(ManaProvider.MANA_CAP, null);
        if(mana != null) {
            mana.setMana(Mana.maxMana, false);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
