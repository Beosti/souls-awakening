package com.yuanno.soulsawakening.blocks;

import com.yuanno.soulsawakening.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class HollowSandStoneStairsBlock extends StairsBlock {

    public HollowSandStoneStairsBlock() {
        super(() -> ModBlocks.HOLLOW_SAND.get().defaultBlockState(), Properties.of(Material.STONE).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F));
    }
}
