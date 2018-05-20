package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import moe.karpador.patriot.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PantsuMessage implements IMessage{

    boolean meguminPantsu;

    public PantsuMessage() {}

    public PantsuMessage(boolean b) {
        meguminPantsu = b;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        meguminPantsu = byteBuf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(meguminPantsu);
    }

    public static class PantsuMessageHandler implements IMessageHandler<PantsuMessage, PantsuMessage> {

        @Override
        public PantsuMessage onMessage(PantsuMessage message, MessageContext context) {
            if (context.side.isServer()) {
                if (message.meguminPantsu)
                    context.getServerHandler().player.inventory.addItemStackToInventory(new ItemStack(ModItems.itemMeguminPantsu));
                else
                    context.getServerHandler().player.inventory.addItemStackToInventory(new ItemStack(ModItems.itemGenericPantsu));
            }
            return null;
        }
    }

}
