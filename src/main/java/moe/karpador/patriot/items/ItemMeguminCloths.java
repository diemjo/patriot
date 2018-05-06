package moe.karpador.patriot.items;

import moe.karpador.patriot.Patriot;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraft.inventory.EntityEquipmentSlot.HEAD;

public class ItemMeguminCloths extends ItemArmor {
    public String name;

    public ItemMeguminCloths(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String itemName) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        name = itemName;
        setCreativeTab(Patriot.PATRIOT_TAB);
        setRegistryName(new ResourceLocation(Patriot.MODID, name));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + Patriot.RESOURCE_PREFIX + name; //item.patriot:armor_megumin_hat
    }

    @Override
    public String getUnlocalizedName() {
        return "item." + Patriot.RESOURCE_PREFIX + name; //item.patriot:armor_megumin_hat
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped defaultModel) {
        if (itemStack != null) {
            if (itemStack.getItem() instanceof ItemArmor) {
                EntityEquipmentSlot type = ((ItemArmor) itemStack.getItem()).armorType;
                switch (type) {
                    case HEAD:
                        break;
                    default:
                        return super.getArmorModel(entityLiving, itemStack, armorSlot, defaultModel);
                }
                ModelBiped armorModel = ModItems.meguminHatModel;
                armorModel.bipedHead.showModel = armorSlot == HEAD;
                armorModel.bipedHeadwear.showModel = armorSlot == HEAD;
                /*armorModel.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST)
                        || (armorSlot == EntityEquipmentSlot.CHEST);
                armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                armorModel.bipedRightLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
                        || (armorSlot == EntityEquipmentSlot.FEET);
                armorModel.bipedLeftLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
                        || (armorSlot == EntityEquipmentSlot.FEET);*/

                armorModel.isSneak = defaultModel.isSneak;
                armorModel.isRiding = defaultModel.isRiding;
                armorModel.isChild = defaultModel.isChild;
                armorModel.rightArmPose = defaultModel.rightArmPose;
                armorModel.leftArmPose = defaultModel.leftArmPose;

                return armorModel;
            }
        }
        return null;

    }
}
