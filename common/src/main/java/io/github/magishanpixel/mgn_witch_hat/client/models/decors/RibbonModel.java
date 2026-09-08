package io.github.magishanpixel.mgn_witch_hat.client.models.decors;// Made with Blockbench 5.1.6
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class RibbonModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart basebody;
	private final ModelPart bone;
	private final ModelPart bone6;
	private final ModelPart bone7;
	private final ModelPart bone8;

	public RibbonModel(ModelPart root) {
		this.basebody = root.getChild("basebody");
		this.bone = this.basebody.getChild("bone");
		this.bone6 = this.bone.getChild("bone6");
		this.bone7 = this.bone6.getChild("bone7");
		this.bone8 = this.bone6.getChild("bone8");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition basebody = partdefinition.addOrReplaceChild("basebody", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 22.765F, 0.2284F, -0.1309F, 0.0F, 0.0F));

		PartDefinition bone = basebody.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition bone6 = bone.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(6, 4).addBox(-1.0F, -1.2779F, -0.5238F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.4069F, 4.7781F));

		PartDefinition cube_r1 = bone6.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 4).addBox(-2.4144F, -0.41F, -0.937F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5802F, 0.2219F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r2 = bone6.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 7).addBox(0.4144F, -0.41F, -0.937F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5802F, 0.2219F, 0.0F, 0.0F, -0.3927F));

		PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offsetAndRotation(0.1757F, 0.7153F, 0.227F, 1.5553F, 0.7409F, -0.0399F));

		PartDefinition cube_r3 = bone7.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 3.5F, 0.0F, -0.3927F, 0.0F, -3.1416F));

		PartDefinition cube_r4 = bone7.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(6, 7).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition bone8 = bone6.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.1757F, 0.7153F, 0.227F, 1.5553F, -0.7409F, 0.0399F));

		PartDefinition cube_r5 = bone8.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 2).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.8827F, -0.9239F, -0.3927F, 0.0F, -3.1416F));

		PartDefinition cube_r6 = bone8.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(8, 0).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int col) {
		basebody.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	}
}