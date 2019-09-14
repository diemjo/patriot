package moe.karpador.patriot.items;

import moe.karpador.patriot.Patriot;
import moe.karpador.patriot.mana.IPantsuStack;
import moe.karpador.patriot.mana.PantsuStackProvider;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.inventory.EntityEquipmentSlot.HEAD;

public abstract class ItemPantsu extends ItemArmor {
    public final String NAME;

    public ItemPantsu(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String pantsuName) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        NAME = pantsuName;
        setCreativeTab(Patriot.PATRIOT_TAB);
        setRegistryName(new ResourceLocation(Patriot.MODID, NAME));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:NAME
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Patriot.RESOURCE_PREFIX, NAME); //item.patriot:NAME
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if(entityLiving instanceof  EntityPlayerMP) {
            CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityLiving, stack); // not sure what consumeItemTrigger does, but ItemFood does this. thus it might be necessary
        }

        stack.shrink(1);
        return stack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new PantsuStackProvider();
        //return super.initCapabilities(stack, nbt);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        /*IPantsuStack pantsuStack = stack.getCapability(PantsuStackProvider.PANTSU_STACK_CAP, null);
        if(pantsuStack != null) {
            tooltip.add(pantsuStack.getOwnerName());
        }*/
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("owner")) {
            tooltip.add("§bBelongs to §l"+stack.getTagCompound().getString("owner")+"§r");
        }
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
                ModelBiped armorModel = ModItems.pantsuModel;
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
