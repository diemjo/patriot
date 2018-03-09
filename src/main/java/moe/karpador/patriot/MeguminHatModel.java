package moe.karpador.patriot;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class MeguminHatModel extends ModelBiped {
    public ModelRenderer Groundplate;
    public ModelRenderer Sideplate;
    public ModelRenderer Frontplate;
    public ModelRenderer shape19;
    public ModelRenderer shape19_1;
    public ModelRenderer shape21;
    public ModelRenderer shape21_1;
    public ModelRenderer lower_rim_edge_right_front;
    public ModelRenderer lower_rim_edge_left_front;
    public ModelRenderer lower_rim_edge_left_back;
    public ModelRenderer lower_rim_edge_right_back;
    public ModelRenderer lowest_meg;
    public ModelRenderer lower_meg;
    public ModelRenderer middle_meg;
    public ModelRenderer high_meg;

    public MeguminHatModel() {
        this.textureWidth = 100;
        this.textureHeight = 100;
        this.Sideplate = new ModelRenderer(this, 0, 50);
        this.Sideplate.setRotationPoint(0.0F, 0.5F, 0.0F);
        this.Sideplate.addBox(-10.0F, -7.59999999999999F, -8.0F, 20, 1, 16, 0.0F);
        this.lower_rim_edge_left_back = new ModelRenderer(this, 74, 0);
        this.lower_rim_edge_left_back.setRotationPoint(0.0F, -1.2F, 0.0F);
        this.lower_rim_edge_left_back.addBox(7.0F, -4.899999999999999F, -10.0F, 3, 1, 3, 0.0F);
        this.lower_meg = new ModelRenderer(this, 62, 8);
        this.lower_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_meg.addBox(-4.0F, -15.099999999999985F, -5.0F, 8, 7, 8, 0.0F);
        this.setRotateAngle(lower_meg, -0.18622663118779495F, 0.0F, 0.0F);
        this.Frontplate = new ModelRenderer(this, 0, 67);
        this.Frontplate.setRotationPoint(0.0F, 0.5F, 0.0F);
        this.Frontplate.addBox(-8.0F, -7.59999999999999F, -10.0F, 16, 1, 20, 0.0F);
        this.lower_rim_edge_right_front = new ModelRenderer(this, 30, 0);
        this.lower_rim_edge_right_front.setRotationPoint(0.0F, -1.2F, 0.0F);
        this.lower_rim_edge_right_front.addBox(-10.0F, -4.899999999999999F, 7.0F, 3, 1, 3, 0.0F);
        this.lower_rim_edge_right_back = new ModelRenderer(this, 30, 4);
        this.lower_rim_edge_right_back.setRotationPoint(0.0F, -1.2F, 0.0F);
        this.lower_rim_edge_right_back.addBox(-10.0F, -4.899999999999999F, -10.0F, 3, 1, 3, 0.0F);
        this.middle_meg = new ModelRenderer(this, 40, 17);
        this.middle_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.middle_meg.addBox(-3.0F, -21.100000000000048F, -6.0F, 6, 8, 6, 0.0F);
        this.setRotateAngle(middle_meg, -0.3829252378875559F, 0.0F, 0.04956735075663895F);
        this.shape19 = new ModelRenderer(this, 61, 28);
        this.shape19.setRotationPoint(0.0F, -1.2F, 0.0F);
        this.shape19.addBox(-8.0F, -4.899999999999999F, 8.0F, 16, 1, 3, 0.0F);
        this.setRotateAngle(shape19, 0.0F, -0.008028514559173916F, 0.0F);
        this.lowest_meg = new ModelRenderer(this, 0, 16);
        this.lowest_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lowest_meg.addBox(-5.0F, -10.099999999999987F, -5.0F, 10, 4, 10, 0.0F);
        this.shape21 = new ModelRenderer(this, 56, 36);
        this.shape21.setRotationPoint(0.0F, -1.2F, 0.0F);
        this.shape21.addBox(8.0F, -4.899999999999999F, -8.0F, 3, 1, 16, 0.0F);
        this.Groundplate = new ModelRenderer(this, 0, 31);
        this.Groundplate.setRotationPoint(-1.1F, -8.500000000000002F, 0.0F);
        this.Groundplate.addBox(-8.0F, 1.4000000000000066F, -9.0F, 18, 1, 18, 0.0F);
        this.shape19_1 = new ModelRenderer(this, 54, 32);
        this.shape19_1.setRotationPoint(0.0F, -1.2F, 0.0F);
        this.shape19_1.addBox(-8.0F, -4.899999999999999F, -11.0F, 16, 1, 3, 0.0F);
        this.shape21_1 = new ModelRenderer(this, 56, 53);
        this.shape21_1.setRotationPoint(0.0F, -1.2F, 0.0F);
        this.shape21_1.addBox(-11.0F, -4.899999999999999F, -8.0F, 3, 1, 16, 0.0F);
        this.high_meg = new ModelRenderer(this, 86, 0);
        this.high_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.high_meg.addBox(-2.0F, -26.00000000000005F, -10.0F, 3, 9, 3, 0.0F);
        this.setRotateAngle(high_meg, -0.7693411342791004F, 0.0F, 0.11030480872604163F);
        this.lower_rim_edge_left_front = new ModelRenderer(this, 62, 0);
        this.lower_rim_edge_left_front.setRotationPoint(0.0F, -1.2F, 0.0F);
        this.lower_rim_edge_left_front.addBox(7.0F, -4.899999999999999F, 7.0F, 3, 1, 3, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Sideplate.render(f5);
        this.lower_rim_edge_left_back.render(f5);
        this.lower_meg.render(f5);
        this.Frontplate.render(f5);
        this.lower_rim_edge_right_front.render(f5);
        this.lower_rim_edge_right_back.render(f5);
        this.middle_meg.render(f5);
        this.shape19.render(f5);
        this.lowest_meg.render(f5);
        this.shape21.render(f5);
        this.Groundplate.render(f5);
        this.shape19_1.render(f5);
        this.shape21_1.render(f5);
        this.high_meg.render(f5);
        this.lower_rim_edge_left_front.render(f5);
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
