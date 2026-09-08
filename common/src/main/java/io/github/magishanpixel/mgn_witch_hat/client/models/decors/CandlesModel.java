package io.github.magishanpixel.mgn_witch_hat.client.models.decors;// Made with Blockbench 5.1.6
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.magishanpixel.mgn_witch_hat.client.models.SplittedParts;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class CandlesModel<T extends Entity> extends EntityModel<T> implements SplittedParts {
	private final ModelPart tip;
	private final ModelPart candle1;
	private final ModelPart candle2;
	private final ModelPart candle3;

	public CandlesModel(ModelPart root) {
		this.tip = root.getChild("tip");
		this.candle1 = root.getChild("candle1");
		this.candle2 = root.getChild("candle2");
		this.candle3 = root.getChild("candle3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tip = partdefinition.addOrReplaceChild("tip", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = tip.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 16).addBox(-1.5F, -5.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r2 = tip.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 16).addBox(-1.5F, -5.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition candle1 = partdefinition.addOrReplaceChild("candle1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -3.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 5).addBox(-0.5F, -7.0F, -2.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 24.0F, 1.0F));

		PartDefinition candle2 = partdefinition.addOrReplaceChild("candle2", CubeListBuilder.create().texOffs(12, 5).addBox(-0.5F, -1.0F, -2.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(0.0F, -5.0F, -2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 24.0F, 1.0F));

		PartDefinition candle3 = partdefinition.addOrReplaceChild("candle3", CubeListBuilder.create().texOffs(12, 9).addBox(-0.5F, -1.0F, -2.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 0).addBox(0.0F, -4.0F, -2.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 24.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int col) {
		this.candle1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	}


	@Override
	public void renderSpecificBone(int num, PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
		if (num == 1) {
			this.candle1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		} else if (num == 3) {
			this.candle2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		} else if (num == 2) {
			this.candle3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		} else if (num == 4) {
			this.tip.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		}

	}
}