package io.github.magishanpixel.mgn_witch_hat.client.models.decors;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class PumpkinDecorModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart basebody;
	private final ModelPart tip1;
	private final ModelPart tip2;

	public PumpkinDecorModel(ModelPart root) {
		this.basebody = root.getChild("basebody");
		this.tip1 = this.basebody.getChild("tip1");
		this.tip2 = this.tip1.getChild("tip2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition basebody = partdefinition.addOrReplaceChild("basebody", CubeListBuilder.create().texOffs(0, 60).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.5F, 0.0F, -8.5F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 8.0F, 0.5F));

		PartDefinition tip1 = basebody.addOrReplaceChild("tip1", CubeListBuilder.create().texOffs(0, 41).addBox(-0.9739F, -2.2011F, -1.0011F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.5F, -0.5F, -0.0279F, -0.3478F, 0.0928F));

		PartDefinition tip2 = tip1.addOrReplaceChild("tip2", CubeListBuilder.create().texOffs(21, 43).addBox(-0.8378F, -3.7319F, -0.0254F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -0.3385F, -0.4797F, 0.2582F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int col) {
		basebody.render(poseStack, vertexConsumer, packedLight, packedOverlay, col);
	}
}