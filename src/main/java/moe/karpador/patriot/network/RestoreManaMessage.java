package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class RestoreManaMessage implements IMessage {

    private int mana;
    private boolean ultimateExplosion;
    private boolean hasPantsu;
    private int pantsuCooldown;


    public RestoreManaMessage() {}

    public RestoreManaMessage(IMana manaToRestore) {
        mana = manaToRestore.getMana();
        ultimateExplosion = manaToRestore.hasUltimateExplosion();
        hasPantsu = manaToRestore.hasPantsu();
        pantsuCooldown = manaToRestore.getPantsuCooldownCounter();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        mana = buf.readInt();
        ultimateExplosion = buf.readBoolean();
        hasPantsu = buf.readBoolean();
        pantsuCooldown = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(mana);
        buf.writeBoolean(ultimateExplosion);
        buf.writeBoolean(hasPantsu);
        buf.writeInt(pantsuCooldown);
    }

    public static class RestoreManaMessageHandler implements IMessageHandler<RestoreManaMessage, RestoreManaMessage> {
        @Override
        public RestoreManaMessage onMessage(RestoreManaMessage message, MessageContext context) {
            if(context.side.isClient()) {
                handleMessageClient(message);
            }
            else {
                handleMessageServer(message, context.getServerHandler().player);

            }
            return null;
        }

        @SideOnly(Side.CLIENT)
        private void handleMessageClient(RestoreManaMessage message) {
            IMana mana = Minecraft.getMinecraft().player.getCapability(ManaProvider.MANA_CAP, null);
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("received manarestoremessage:\nmana: "+ message.mana + " ultimate explosion "+message.ultimateExplosion));
            if(mana == null) {
                return;
            }
            restoreMana(message, mana);
        }

        private void handleMessageServer(RestoreManaMessage message, EntityPlayer player) {
            IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
            if(mana==null) {
                return;
            }
            restoreMana(message, mana);
        }

        private void restoreMana(RestoreManaMessage message, IMana manaToUpdate) {
            manaToUpdate.setMana(message.mana, false); // isRemote false because we don't want to send a RestoreManaMessage to the server
            manaToUpdate.setUltimateExplosion(message.ultimateExplosion);
            manaToUpdate.setPantsu(message.hasPantsu);
            manaToUpdate.setPantsuCooldownCounter(message.pantsuCooldown);
        }
    }
}
