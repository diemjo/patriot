package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import moe.karpador.patriot.PatriotSoundHandler;
import moe.karpador.patriot.items.ModItems;
import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.UUID;

public class PantsuMessage implements IMessage{

    // true if pantsu is megumin pantsu
    boolean meguminPantsu;
    UUID targetPlayerId;
    public PantsuMessage() {}

    public PantsuMessage(boolean meguminPantsu, EntityPlayer stealPantsuFrom) {
        this.meguminPantsu = meguminPantsu;
        targetPlayerId = stealPantsuFrom.getPersistentID();
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        meguminPantsu = byteBuf.readBoolean();
        targetPlayerId = UUID.fromString(byteBuf.getCharSequence(byteBuf.readerIndex(), byteBuf.capacity()-byteBuf.readerIndex(), Charset.defaultCharset()).toString());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(meguminPantsu);
        buf.writeCharSequence(targetPlayerId.toString().subSequence(0, targetPlayerId.toString().length()), Charset.defaultCharset());
    }

    public static class PantsuMessageHandler implements IMessageHandler<PantsuMessage, PantsuMessage> {

        @Override
        public PantsuMessage onMessage(PantsuMessage message, MessageContext context) {
            if (context.side.isServer()) {
                ItemStack pantsu = null;
                if (message.meguminPantsu) {
                    pantsu = new ItemStack(ModItems.itemMeguminPantsu);

                }
                else {
                    pantsu = new ItemStack(ModItems.itemGenericPantsu);
                }
                pantsu.setTagCompound(new NBTTagCompound());
                pantsu.getTagCompound().setString("owner", findPlayerFromUUID(message.targetPlayerId, context.getServerHandler().player.getServerWorld()).get().getName());
                context.getServerHandler().player.inventory.addItemStackToInventory(pantsu);
                context.getServerHandler().player.playSound(PatriotSoundHandler.kyaa, 1, 1);
                context.getServerHandler().player.sendMessage(new TextComponentString("stole pantsu from player:  " + message.targetPlayerId));
                findPlayerFromUUID(message.targetPlayerId, context.getServerHandler().player.getServerWorld())
                        .ifPresent(p -> handlePantsu(p));
            }
            else {
                handleMessageClient(message);
            }
            return null;
        }

        @SideOnly(Side.CLIENT)
        private void handleMessageClient(PantsuMessage message) {
            findPlayerFromUUID(message.targetPlayerId, Minecraft.getMinecraft().world)
                    .ifPresent(p -> removePantsu(p));
        }

        private Optional<EntityPlayer> findPlayerFromUUID(UUID playerID, World world) {
            return world.playerEntities.parallelStream()
                    .filter(p -> p.getPersistentID().equals(playerID))
                    .findAny();
        }

        /**
         * removes pantsu in the corresponding capability of server and sends a Message to all clients to remove the capability as well
         * should only be called from the server
          */
        private void handlePantsu(EntityPlayer targetPlayer) {
            PatriotPacketHandler.wrapper.sendToAll(new PantsuMessage(true, targetPlayer)); // boolean meguminPantsu doesn't matter for clients
            removePantsu(targetPlayer);
        }

        private void removePantsu(EntityPlayer targetPlayer) {
            IMana mana = targetPlayer.getCapability(ManaProvider.MANA_CAP, null);
            if(mana == null)
                return;
            mana.setPantsu(false);
        }
    }
}
