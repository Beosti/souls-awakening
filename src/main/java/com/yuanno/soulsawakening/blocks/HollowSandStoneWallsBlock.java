package com.yuanno.soulsawakening.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class HollowSandStoneWallsBlock extends WallBlock {

    public HollowSandStoneWallsBlock()
    {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.5F));
    }
}
