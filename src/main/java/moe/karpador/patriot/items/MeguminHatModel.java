package moe.karpador.patriot.items;

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
    public ModelRenderer eyepatch;

    public MeguminHatModel() {
        this.textureWidth = 100;
        this.textureHeight = 100;
        this.middle_meg = new ModelRenderer(this, 40, 17);
        this.middle_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.middle_meg.addBox(-3.0F, -20.800000000000075F, -6.0F, 6, 7, 6, 0.0F);
        this.setRotateAngle(middle_meg, -0.3156122351557273F, 0.0F, 0.04956735075663895F);
        this.lower_rim_edge_left_back = new ModelRenderer(this, 74, 0);
        this.lower_rim_edge_left_back.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_rim_edge_left_back.addBox(7.0F, -6.5999999999999925F, -10.0F, 3, 1, 3, 0.0F);
        this.lower_rim_edge_right_front = new ModelRenderer(this, 30, 0);
        this.lower_rim_edge_right_front.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_rim_edge_right_front.addBox(-10.0F, -6.5999999999999925F, 7.0F, 3, 1, 3, 0.0F);
        this.shape21 = new ModelRenderer(this, 56, 36);
        this.shape21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape21.addBox(8.0F, -6.5999999999999925F, -8.0F, 3, 1, 16, 0.0F);
        this.Frontplate = new ModelRenderer(this, 0, 67);
        this.Frontplate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Frontplate.addBox(-8.0F, -7.599999999999989F, -10.0F, 16, 1, 20, 0.0F);
        this.Groundplate = new ModelRenderer(this, 0, 31);
        this.Groundplate.setRotationPoint(-1.1F, -9.0F, 0.0F);
        this.Groundplate.addBox(-8.0F, 1.4000000000000075F, -9.0F, 18, 1, 18, 0.0F);
        this.shape19 = new ModelRenderer(this, 61, 28);
        this.shape19.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape19.addBox(-8.0F, -6.5999999999999925F, 8.0F, 16, 1, 3, 0.0F);
        this.setRotateAngle(shape19, 0.0F, -0.008028514559173916F, 0.0F);
        this.lower_rim_edge_left_front = new ModelRenderer(this, 62, 0);
        this.lower_rim_edge_left_front.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_rim_edge_left_front.addBox(7.0F, -6.5999999999999925F, 7.0F, 3, 1, 3, 0.0F);
        this.high_meg = new ModelRenderer(this, 86, 0);
        this.high_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.high_meg.addBox(-2.0F, -24.800000000000075F, -10.0F, 3, 7, 3, 0.0F);
        this.setRotateAngle(high_meg, -0.6347151288154435F, 0.0F, 0.11030480872604163F);
        this.lower_rim_edge_right_back = new ModelRenderer(this, 30, 4);
        this.lower_rim_edge_right_back.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_rim_edge_right_back.addBox(-10.0F, -6.5999999999999925F, -10.0F, 3, 1, 3, 0.0F);
        this.lowest_meg = new ModelRenderer(this, 0, 16);
        this.lowest_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lowest_meg.addBox(-5.0F, -10.799999999999981F, -5.0F, 10, 4, 10, 0.0F);
        this.lower_meg = new ModelRenderer(this, 62, 8);
        this.lower_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_meg.addBox(-4.0F, -14.79999999999998F, -5.0F, 8, 6, 8, 0.0F);
        this.setRotateAngle(lower_meg, -0.1477896824808048F, 0.0F, 0.0F);
        this.shape21_1 = new ModelRenderer(this, 56, 53);
        this.shape21_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape21_1.addBox(-11.0F, -6.5999999999999925F, -8.0F, 3, 1, 16, 0.0F);
        this.Sideplate = new ModelRenderer(this, 0, 50);
        this.Sideplate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Sideplate.addBox(-10.0F, -7.599999999999989F, -8.0F, 20, 1, 16, 0.0F);
        this.shape19_1 = new ModelRenderer(this, 54, 32);
        this.shape19_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape19_1.addBox(-8.0F, -6.5999999999999925F, -11.0F, 16, 1, 3, 0.0F);
        this.eyepatch = new ModelRenderer(this, 10, 2);
        this.eyepatch.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.eyepatch.addBox(0.0F, -5.0F, -5.0F, 3, 3, 2, 0.0F);

        bipedHead.cubeList.clear();
        bipedHeadwear.cubeList.clear();

        bipedHead.addChild(Groundplate);
        bipedHead.addChild(Sideplate);
        bipedHead.addChild(Frontplate);
        bipedHead.addChild(shape19);
        bipedHead.addChild(shape19_1);
        bipedHead.addChild(shape21);
        bipedHead.addChild(shape21_1);
        bipedHead.addChild(lower_rim_edge_right_front);
        bipedHead.addChild(lower_rim_edge_left_front);
        bipedHead.addChild(lower_rim_edge_left_back);
        bipedHead.addChild(lower_rim_edge_right_back);
        bipedHead.addChild(lowest_meg);
        bipedHead.addChild(lower_meg);
        bipedHead.addChild(middle_meg);
        bipedHead.addChild(high_meg);
        bipedHead.addChild(eyepatch);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        /*
        this.middle_meg.render(f5);
        this.lower_rim_edge_left_back.render(f5);
        this.lower_rim_edge_right_front.render(f5);
        this.shape21.render(f5);
        this.Frontplate.render(f5);
        this.Groundplate.render(f5);
        this.shape19.render(f5);
        this.lower_rim_edge_left_front.render(f5);
        this.high_meg.render(f5);
        this.lower_rim_edge_right_back.render(f5);
        this.lowest_meg.render(f5);
        this.lower_meg.render(f5);
        this.shape21_1.render(f5);
        this.Sideplate.render(f5);
        this.shape19_1.render(f5);
        this.eyepatch.render(f5);
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
