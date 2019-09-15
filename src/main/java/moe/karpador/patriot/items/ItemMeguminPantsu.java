package moe.karpador.patriot.items;

import moe.karpador.patriot.PatriotSoundHandler;
import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.ManaProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemMeguminPantsu extends ItemPantsu {
    public ItemMeguminPantsu(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn){
        super(materialIn, renderIndexIn, equipmentSlotIn, "armor_megumin_pantsu");
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        IMana mana = entityLiving.getCapability(ManaProvider.MANA_CAP, null);
        if(mana != null) {
            mana.setUltimateExplosion(true);
            worldIn.playSound((EntityPlayer)entityLiving, entityLiving.posX, entityLiving.posY, entityLiving.posZ, PatriotSoundHandler.oimatte, SoundCategory.PLAYERS, 1, 1);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
