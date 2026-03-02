// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports
package com.allthecolonists.core.client.model;

import com.minecolonies.api.client.render.modeltype.CitizenModel;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.HumanoidModel;
import org.jetbrains.annotations.NotNull;

public class FemaleMekanistModel extends CitizenModel<AbstractEntityCitizen>
{

    public FemaleMekanistModel(final ModelPart part)
    {
        super(part);
        hat.visible = false;
    }

    public static LayerDefinition createMesh()
    {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bipedHead = partdefinition.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        PartDefinition glassesOff = bipedHead.addOrReplaceChild(
                "glassesOff",
                CubeListBuilder.create()
                        .texOffs(98, 16).addBox(-4.0F, -4.0F, -4.01F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.05F))
                        .texOffs(108, 16).addBox(3.0F, -4.0F, -4.01F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.05F))
                        .texOffs(104, 10).addBox(4.01F, -4.0F, -4.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.05F))
                        .texOffs(88, 10).addBox(-4.01F, -4.0F, -4.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.05F))
                        .texOffs(96, 19).addBox(-4.0F, -4.0F, 4.01F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.05F))
                        .texOffs(101, 12).addBox(-1.0F, -4.1F, -4.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-0.21F)),
                PartPose.offset(0.0F, -2.6F, 0.0F)
        );

        PartDefinition glassesOn = bipedHead.addOrReplaceChild(
                "glassesOn",
                CubeListBuilder.create()
                        .texOffs(98, 6).addBox(-4.0F, -4.0F, -4.01F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.05F))
                        .texOffs(108, 6).addBox(3.0F, -4.0F, -4.01F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.05F))
                        .texOffs(104, 0).addBox(4.01F, -4.0F, -4.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.05F))
                        .texOffs(88, 0).addBox(-4.01F, -4.0F, -4.0F, 0.0F, 1.0F, 8.0F, new CubeDeformation(0.05F))
                        .texOffs(96, 9).addBox(-4.0F, -4.0F, 4.01F, 8.0F, 1.0F, 0.0F, new CubeDeformation(0.05F))
                        .texOffs(101, 2).addBox(-1.0F, -4.1F, -4.8F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-0.21F)),
                PartPose.offset(0.0F, -0.2F, 0.0F)
        );

        // Restliches Modell unverändert (Kristalle, Haare, Körper, Arme, Beine etc.)

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void setupAnim(@NotNull final AbstractEntityCitizen entity,
                          float limbSwing,
                          float limbSwingAmount,
                          float ageInTicks,
                          float netHeadYaw,
                          float headPitch)
    {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        final boolean working = isWorking(entity);
        head.getChild("glassesOn").visible = working;
        head.getChild("glassesOff").visible = !working;
    }
}
