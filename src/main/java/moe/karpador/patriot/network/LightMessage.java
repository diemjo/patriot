package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class LightMessage implements IMessage {
    public int x, y, z;
    public boolean on;

    public LightMessage() {}

    public LightMessage(int x, int y, int z, boolean on) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.on = on;
    }
    @Override
    public void fromBytes(ByteBuf byteBuf) {
        x = byteBuf.readInt();
        y = byteBuf.readInt();
        z = byteBuf.readInt();
        on = byteBuf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(x);
        byteBuf.writeInt(y);
        byteBuf.writeInt(z);
        byteBuf.writeBoolean(on);
    }
}
