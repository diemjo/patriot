package moe.karpador.patriot.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ExplosionMessage implements IMessage {

    public float x, y, z, s;

    public ExplosionMessage() {}

    public ExplosionMessage(float x, float y, float z, float s) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.s = s;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        x = byteBuf.readFloat();
        y = byteBuf.readFloat();
        z = byteBuf.readFloat();
        s = byteBuf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeFloat(x);
        byteBuf.writeFloat(y);
        byteBuf.writeFloat(z);
        byteBuf.writeFloat(s);
    }
}