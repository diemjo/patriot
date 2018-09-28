package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import moe.karpador.patriot.PatriotSoundHandler;
import moe.karpador.patriot.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.Charset;

public class PantsuMessage implements IMessage{

    // true if pantsu is megumin pantsu
    boolean meguminPantsu;
    String targetPlayerName;
    public PantsuMessage() {}

    public PantsuMessage(boolean b, EntityPlayer stealPantsuFrom) {
        meguminPantsu = b;
        targetPlayerName = stealPantsuFrom.getName();
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        meguminPantsu = byteBuf.readBoolean();
        targetPlayerName = byteBuf.getCharSequence(byteBuf.readerIndex(), byteBuf.capacity()-1, Charset.defaultCharset()).toString();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(meguminPantsu);
        buf.writeCharSequence(targetPlayerName.subSequence(0, targetPlayerName.length()), Charset.defaultCharset());
    }

    public static class PantsuMessageHandler implements IMessageHandler<PantsuMessage, PantsuMessage> {

        @Override
        public PantsuMessage onMessage(PantsuMessage message, MessageContext context) {
            if (context.side.isServer()) {
                if (message.meguminPantsu)
                    context.getServerHandler().player.inventory.addItemStackToInventory(new ItemStack(ModItems.itemMeguminPantsu));
                else
                    context.getServerHandler().player.inventory.addItemStackToInventory(new ItemStack(ModItems.itemGenericPantsu));
                context.getServerHandler().player.playSound(PatriotSoundHandler.kyaa, 1, 1);
                //context.getServerHandler().player.getServerWorld().playerEntities.get(0).getName()
                context.getServerHandler().player.sendMessage(new TextComponentString("stole pantsu from player:  " + message.targetPlayerName));
            }
            return null;
        }
    }

}
