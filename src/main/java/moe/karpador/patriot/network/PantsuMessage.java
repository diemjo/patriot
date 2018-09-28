package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import moe.karpador.patriot.PatriotSoundHandler;
import moe.karpador.patriot.items.ModItems;
import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.UUID;

public class PantsuMessage implements IMessage{

    // true if pantsu is megumin pantsu
    boolean meguminPantsu;
    // true when stealing, false when regenerating pantsu
    boolean stealingPantsu;
    UUID targetPlayerId;
    public PantsuMessage() {}

    public PantsuMessage(boolean meguminPantsu, boolean stealingPantsu, EntityPlayer stealPantsuFrom) {
        this.meguminPantsu = meguminPantsu;
        this.stealingPantsu = stealingPantsu;
        targetPlayerId = stealPantsuFrom.getPersistentID();
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        meguminPantsu = byteBuf.readBoolean();
        stealingPantsu = byteBuf.readBoolean();
        targetPlayerId = UUID.fromString(byteBuf.getCharSequence(byteBuf.readerIndex(), byteBuf.capacity()-1, Charset.defaultCharset()).toString());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(meguminPantsu);
        buf.writeBoolean(stealingPantsu);
        buf.writeCharSequence(targetPlayerId.toString().subSequence(0, targetPlayerId.toString().length()), Charset.defaultCharset());
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
                context.getServerHandler().player.sendMessage(new TextComponentString("stole pantsu from player:  " + message.targetPlayerId));
                findPlayerFromUUID(message.targetPlayerId, context.getServerHandler().player.getServerWorld())
                        .ifPresent(p -> handlePantsu(p, message.stealingPantsu));
            }
            else {
                findPlayerFromUUID(message.targetPlayerId, Minecraft.getMinecraft().world)
                        .ifPresent(p -> setPantsu(p, message.stealingPantsu));
            }
            return null;
        }

        private Optional<EntityPlayer> findPlayerFromUUID(UUID playerID, World world) {
            return world.playerEntities.parallelStream()
                    .filter(p -> p.getPersistentID().equals(playerID))
                    .findAny();
        }
        /**
         * removes or adds pantsu in the corresponding capability of server and sends a Message to all clients to remove/add the capability as well
         * should only be called from the server
          */
        private void handlePantsu(EntityPlayer targetPlayer, boolean stealingPantsu) {
            setPantsu(targetPlayer, stealingPantsu);
            PatriotPacketHandler.wrapper.sendToAll(new PantsuMessage(true, stealingPantsu, targetPlayer)); // boolean meguminPantsu doesn't matter for clients
        }

        // removes or adds pantsu in the corresponding capability
        private void setPantsu(EntityPlayer targetPlayer, boolean stealingPantsu) {
            IMana mana = targetPlayer.getCapability(ManaProvider.MANA_CAP, null);
            if(mana == null)
                return;
            mana.setPantsu(!stealingPantsu);
        }

    }

}
