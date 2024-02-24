package com.yuanno.soulsawakening.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ReishiBricksBlock extends Block {

    public ReishiBricksBlock()
    {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F));
    }
}
