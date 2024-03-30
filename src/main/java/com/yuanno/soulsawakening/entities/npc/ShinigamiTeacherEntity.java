package com.yuanno.soulsawakening.entities.npc;

import com.yuanno.soulsawakening.api.SequencedString;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.screens.TexturedIconButton;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ShinigamiTeacherEntity extends SoulSocietyTeacherEntity {

    public ShinigamiTeacherEntity(EntityType type, World world)
    {
        super(type, world);
    }

}
