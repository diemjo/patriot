package moe.karpador.patriot.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Pantsu - wacra
 * Created using Tabula 7.0.0
 */
public class PantsuModel extends ModelBiped {
    public ModelRenderer shape16;
    public ModelRenderer shape17;
    public ModelRenderer shape17_1;
    public ModelRenderer shape17_2;
    public ModelRenderer shape17_3;
    public ModelRenderer shape21;
    public ModelRenderer shape21_1;

    public PantsuModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape16 = new ModelRenderer(this, 0, 0);
        this.shape16.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.shape16.addBox(-1.0F, 0.0F, -4.5F, 2, 1, 9, 0.0F);
        this.shape17_1 = new ModelRenderer(this, 0, 12);
        this.shape17_1.setRotationPoint(0.0F, -7.0F, -5.0F);
        this.shape17_1.addBox(-3.0F, 0.0F, 0.0F, 6, 1, 10, 0.0F);
        this.shape17_3 = new ModelRenderer(this, 42, 0);
        this.shape17_3.setRotationPoint(0.0F, -8.0F, 4.0F);
        this.shape17_3.addBox(-2.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.shape21 = new ModelRenderer(this, 42, 2);
        this.shape21.setRotationPoint(0.0F, -9.0F, -4.5F);
        this.shape21.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
        this.shape21_1 = new ModelRenderer(this, 52, 2);
        this.shape21_1.setRotationPoint(0.0F, -9.0F, 2.5F);
        this.shape21_1.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
        this.shape17 = new ModelRenderer(this, 12, 0);
        this.shape17.setRotationPoint(0.0F, -6.0F, -5.0F);
        this.shape17.addBox(-5.0F, 0.0F, 0.0F, 10, 2, 10, 0.0F);
        this.shape17_2 = new ModelRenderer(this, 52, 0);
        this.shape17_2.setRotationPoint(0.0F, -8.0F, -5.0F);
        this.shape17_2.addBox(-2.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);

        bipedHead.cubeList.clear();
        bipedHeadwear.cubeList.clear();

        bipedHead.addChild(shape16);
        bipedHead.addChild(shape17);
        bipedHead.addChild(shape17_1);
        bipedHead.addChild(shape17_2);
        bipedHead.addChild(shape17_3);
        bipedHead.addChild(shape21);
        bipedHead.addChild(shape21_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        /*
        this.shape16.render(f5);
        this.shape17_1.render(f5);
        this.shape17_3.render(f5);
        this.shape21.render(f5);
        this.shape21_1.render(f5);
        this.shape17.render(f5);
        this.shape17_2.render(f5);
        */
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
