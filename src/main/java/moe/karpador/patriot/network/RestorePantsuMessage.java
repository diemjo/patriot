package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import moe.karpador.patriot.mana.IMana;
import moe.karpador.patriot.mana.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.UUID;

public class RestorePantsuMessage implements IMessage {
    private boolean hasPantsu;
    private int pantsuCooldown;
    private UUID playerId;

    public RestorePantsuMessage() {}

    public RestorePantsuMessage(IMana manaToRestore, EntityPlayer restorePlayer) {
        hasPantsu = manaToRestore.hasPantsu();
        pantsuCooldown = manaToRestore.getPantsuCooldownCounter();
        playerId = restorePlayer.getPersistentID();
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        hasPantsu = byteBuf.readBoolean();
        pantsuCooldown = byteBuf.readInt();
        playerId = UUID.fromString(byteBuf.getCharSequence(byteBuf.readerIndex(), byteBuf.capacity()-byteBuf.readerIndex(), Charset.defaultCharset()).toString());

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(hasPantsu);
        buf.writeInt(pantsuCooldown);
        buf.writeCharSequence(playerId.toString().subSequence(0, playerId.toString().length()), Charset.defaultCharset());
    }

    public static class RestorePantsuMessageHandler implements IMessageHandler<RestorePantsuMessage, RestorePantsuMessage> {
        @Override
        public RestorePantsuMessage onMessage(RestorePantsuMessage message, MessageContext context) {
            if(context.side.isClient()) {
                handleMessageClient(message);
            }
            return null;
        }

        @SideOnly(Side.CLIENT)
        private void handleMessageClient(RestorePantsuMessage message) {
            findPlayerFromUUID(message.playerId, Minecraft.getMinecraft().world)
                    .ifPresent(p -> {
                        IMana playerMana = p.getCapability(ManaProvider.MANA_CAP, null);
                        if(playerMana == null) {
                            return;
                        }
                        playerMana.setPantsu(message.hasPantsu);
                        playerMana.setPantsuCooldownCounter(message.pantsuCooldown);
                    });
        }

        private Optional<EntityPlayer> findPlayerFromUUID(UUID playerID, World world) {
            return world.playerEntities.parallelStream()
                    .filter(p -> p.getPersistentID().equals(playerID))
                    .findAny();
        }
    }
}
