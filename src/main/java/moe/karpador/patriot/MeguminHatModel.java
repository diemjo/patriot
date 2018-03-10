package moe.karpador.patriot;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class MeguminHatModel extends ModelBiped {
    private ModelRenderer Groundplate;
    private ModelRenderer Sideplate;
    private ModelRenderer Frontplate;
    private ModelRenderer shape19;
    private ModelRenderer shape19_1;
    private ModelRenderer shape21;
    private ModelRenderer shape21_1;
    private ModelRenderer lower_rim_edge_right_front;
    private ModelRenderer lower_rim_edge_left_front;
    private ModelRenderer lower_rim_edge_left_back;
    private ModelRenderer lower_rim_edge_right_back;
    private ModelRenderer lowest_meg;
    private ModelRenderer lower_meg;
    private ModelRenderer middle_meg;
    private ModelRenderer high_meg;
    private ModelRenderer eyepatch;

    private ArrayList<ModelRenderer> modelList;

    public MeguminHatModel() {
        this.textureWidth = 100;
        this.textureHeight = 100;
        this.lowest_meg = new ModelRenderer(this, 52, 42);
        this.lowest_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lowest_meg.addBox(-5.0F, -10.799999999999981F, -5.0F, 10, 4, 10, 0.0F);
        this.eyepatch = new ModelRenderer(this, 10, 2);
        this.eyepatch.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.eyepatch.addBox(-3.0F, -5.0F, -5.0F, 2, 3, 2, 0.0F);
        this.lower_rim_edge_right_back = new ModelRenderer(this, 54, 8);
        this.lower_rim_edge_right_back.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_rim_edge_right_back.addBox(-10.0F, -6.5999999999999925F, -10.0F, 3, 1, 3, 0.0F);
        this.lower_meg = new ModelRenderer(this, 64, 56);
        this.lower_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_meg.addBox(-4.0F, -14.79999999999998F, -5.0F, 8, 6, 8, 0.0F);
        this.setRotateAngle(lower_meg, -0.1477896824808048F, 0.0F, 0.0F);
        this.Sideplate = new ModelRenderer(this, 0, 19);
        this.Sideplate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Sideplate.addBox(-10.0F, -7.599999999999989F, -8.0F, 20, 1, 16, 0.0F);
        this.middle_meg = new ModelRenderer(this, 0, 57);
        this.middle_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.middle_meg.addBox(-3.0F, -20.800000000000075F, -6.0F, 6, 7, 6, 0.0F);
        this.setRotateAngle(middle_meg, -0.3156122351557273F, 0.0F, 0.04956735075663895F);
        this.shape21_1 = new ModelRenderer(this, 56, 25);
        this.shape21_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape21_1.addBox(-11.0F, -6.5999999999999925F, -8.0F, 3, 1, 16, 0.0F);
        this.shape19_1 = new ModelRenderer(this, 54, 4);
        this.shape19_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape19_1.addBox(-8.0F, -6.5999999999999925F, -11.0F, 16, 1, 3, 0.0F);
        this.lower_rim_edge_left_front = new ModelRenderer(this, 0, 4);
        this.lower_rim_edge_left_front.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_rim_edge_left_front.addBox(7.0F, -6.5999999999999925F, 7.0F, 3, 1, 3, 0.0F);
        this.shape21 = new ModelRenderer(this, 56, 8);
        this.shape21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape21.addBox(8.0F, -6.5999999999999925F, -8.0F, 3, 1, 16, 0.0F);
        this.shape19 = new ModelRenderer(this, 54, 0);
        this.shape19.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape19.addBox(-8.0F, -6.5999999999999925F, 8.0F, 16, 1, 3, 0.0F);
        this.setRotateAngle(shape19, 0.0F, -0.008028514559173916F, 0.0F);
        this.high_meg = new ModelRenderer(this, 78, 8);
        this.high_meg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.high_meg.addBox(-2.0F, -24.800000000000075F, -10.0F, 3, 7, 3, 0.0F);
        this.setRotateAngle(high_meg, -0.6347151288154435F, 0.0F, 0.11030480872604163F);
        this.lower_rim_edge_left_back = new ModelRenderer(this, 0, 8);
        this.lower_rim_edge_left_back.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_rim_edge_left_back.addBox(7.0F, -6.5999999999999925F, -10.0F, 3, 1, 3, 0.0F);
        this.Groundplate = new ModelRenderer(this, 0, 0);
        this.Groundplate.setRotationPoint(-1.1F, -9.0F, 0.0F);
        this.Groundplate.addBox(-8.0F, 1.4000000000000075F, -9.0F, 18, 1, 18, 0.0F);
        this.lower_rim_edge_right_front = new ModelRenderer(this, 0, 0);
        this.lower_rim_edge_right_front.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lower_rim_edge_right_front.addBox(-10.0F, -6.5999999999999925F, 7.0F, 3, 1, 3, 0.0F);
        this.Frontplate = new ModelRenderer(this, 0, 36);
        this.Frontplate.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Frontplate.addBox(-8.0F, -7.599999999999989F, -10.0F, 16, 1, 20, 0.0F);

        modelList=new ArrayList<>();
        modelList.add(Groundplate);
        modelList.add(Sideplate);
        modelList.add(Frontplate);
        modelList.add(shape19);
        modelList.add(shape19_1);
        modelList.add(shape21);
        modelList.add(shape21_1);
        modelList.add(lower_rim_edge_right_front);
        modelList.add(lower_rim_edge_left_front);
        modelList.add(lower_rim_edge_left_back);
        modelList.add(lower_rim_edge_right_back);
        modelList.add(lowest_meg);
        modelList.add(lower_meg);
        modelList.add(middle_meg);
        modelList.add(high_meg);
        modelList.add(eyepatch);
        for(ModelRenderer model: modelList) {
            model.mirror = true;
        }

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
        /*this.lowest_meg.render(f5);
        this.eyepatch.render(f5);
        this.lower_rim_edge_right_back.render(f5);
        this.lower_meg.render(f5);
        this.Sideplate.render(f5);
        this.middle_meg.render(f5);
        this.shape21_1.render(f5);
        this.shape19_1.render(f5);
        this.lower_rim_edge_left_front.render(f5);
        this.shape21.render(f5);
        this.shape19.render(f5);
        this.high_meg.render(f5);
        this.lower_rim_edge_left_back.render(f5);
        this.Groundplate.render(f5);
        this.lower_rim_edge_right_front.render(f5);
        this.Frontplate.render(f5);
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
