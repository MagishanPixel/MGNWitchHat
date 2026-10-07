package io.github.magishanpixel.mgn_witch_hat.client.models.decors;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class AntlerModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart basebody;

	public AntlerModel(ModelPart root) {
		this.basebody = root.getChild("basebody");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition basebody = partdefinition.addOrReplaceChild("basebody", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 23.5F, -0.5F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r1 = basebody.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 12).addBox(-10.75F, -9.0F, 0.0F, 11.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -4.5F, -2.0F, -0.0873F, 0.5236F, 0.0F));

		PartDefinition cube_r2 = basebody.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.25F, -9.0F, 0.0F, 11.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -4.5F, -2.0F, -0.0873F, -0.5236F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int col) {
		basebody.render(poseStack, vertexConsumer, packedLight, packedOverlay, col);
	}
}