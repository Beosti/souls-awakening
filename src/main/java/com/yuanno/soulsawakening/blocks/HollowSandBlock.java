package com.yuanno.soulsawakening.blocks;

import net.minecraft.block.FallingBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class HollowSandBlock extends FallingBlock {

    public HollowSandBlock()
    {
        super(Properties.of(Material.SAND).harvestTool(ToolType.SHOVEL).strength(4));
    }
}
