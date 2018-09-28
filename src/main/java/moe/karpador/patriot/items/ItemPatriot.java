package moe.karpador.patriot.items;

import com.google.common.collect.Multimap;
import moe.karpador.patriot.Patriot;
import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.ManaProvider;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemPatriot extends ItemSword {

    public static final String NAME = "item_patriot";
    private long lastUsageTime = 0;
    private static final float ATTACK_DAMAGE = 12f;


    public ItemPatriot(ToolMaterial material) {
        super(material);
        setMaxStackSize(1);
        setMaxDamage(200);
        setCreativeTab(Patriot.PATRIOT_TAB);
        setRegistryName(new ResourceLocation(Patriot.MODID, NAME));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + Patriot.RESOURCE_PREFIX +NAME; //item.patriot:item_patriot
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.isSneaking()) {
            long systemTime = System.currentTimeMillis();
            if (systemTime - lastUsageTime > 200 && !worldIn.isRemote) {
                IMana mana = playerIn.getCapability(ManaProvider.MANA_CAP, null);
                int manaCount = mana == null ? -1 : mana.getMana();
                playerIn.sendMessage(new TextComponentString("Patriot best girl! Mana: " + manaCount));
                lastUsageTime = systemTime;
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);
        stack.addAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", ATTACK_DAMAGE, 0), EntityEquipmentSlot.MAINHAND);
        return modifiers;
    }
}
