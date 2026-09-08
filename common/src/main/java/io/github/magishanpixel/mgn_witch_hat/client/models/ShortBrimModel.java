package io.github.magishanpixel.mgn_witch_hat.client.models;// Made with Blockbench 5.1.6
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class ShortBrimModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart basebody;

	public ShortBrimModel(ModelPart root) {
		this.basebody = root.getChild("basebody");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition basebody = partdefinition.addOrReplaceChild("basebody", CubeListBuilder.create().texOffs(-20, 0).addBox(-10.0F, 0.0F, -10.0F, 20.0F, 0.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, -0.1309F, 0.0F, 0.0F));

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