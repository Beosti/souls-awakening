package com.yuanno.soulsawakening.blocks;

import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ReishiOreBlock extends OreBlock {

    public ReishiOreBlock()
    {
        super(Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(8));
    }
}
