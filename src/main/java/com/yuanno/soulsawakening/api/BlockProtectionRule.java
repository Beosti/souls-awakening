package com.yuanno.soulsawakening.api;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BlockProtectionRule
{	
	private boolean bypassGriefRule = false;
	private Set<IReplaceBlockRule> replaceRules = new HashSet<>();
	
	private Set<ResourceLocation> approvedBlocks = new HashSet<>();
	private Set<Material> approvedMaterials = new HashSet<>();
	private Set<ITag<Block>> approvedBlockTags = new HashSet<>();

	private Set<ResourceLocation> bannedBlocks = new HashSet<>();
	private Set<Material> bannedMaterials = new HashSet<>();
	private Set<ITag<Block>> bannedBlockTags = new HashSet<>();
	
	public BlockProtectionRule() 
	{
		
	}
	
	public Set<ResourceLocation> getApprovedBlocks()
	{
		return this.approvedBlocks;
	}

	public Set<Material> getApprovedMaterials()
	{
		return this.approvedMaterials;
	}

	public Set<ITag<Block>> getApprovedTags()
	{
		return this.approvedBlockTags;
	}

	public Set<ResourceLocation> getBannedBlocks()
	{
		return this.bannedBlocks;
	}
	
	public Set<IReplaceBlockRule> getReplaceRules()
	{
		return this.replaceRules;
	}
	
	private void addReplaceRules(Set<IReplaceBlockRule> fns)
	{
		this.replaceRules = fns;
	}
	
	private void addApprovedBlocks(Set<ResourceLocation> blocks)
	{
		this.approvedBlocks = blocks;
	}

	private void addApprovedMaterials(Set<Material> mats)
	{
		this.approvedMaterials = mats;
	}
	
	private void addApprovedTags(Set<ITag<Block>> tags)
	{
		this.approvedBlockTags = tags;
	}

	private void addBannedBlocks(Set<ResourceLocation> blocks)
	{
		this.bannedBlocks = blocks;
	}
	
	public boolean getBypassGriefRule()
	{
		return this.bypassGriefRule;
	}
	
	private void setBypassGriefRule()
	{
		this.bypassGriefRule = true;
	}
	
	public boolean check(World world, BlockPos pos, BlockState state)
	{
		if(this.isBanned(state))
			return false;

		for(IReplaceBlockRule fn : this.replaceRules)
			fn.replace(world, pos, state);

		return this.isApproved(state);
	}
	
	public boolean isApproved(BlockState state)
	{
		if(this.approvedBlocks.contains(state.getBlock().getRegistryName()))
			return true;

		if(this.approvedBlockTags.stream().anyMatch(tag -> tag.contains(state.getBlock())))
			return true;
	
		if(this.approvedMaterials.stream().anyMatch(mat -> state.getMaterial() == mat))
			return true;
		
		return false;
	}
	
	public boolean isBanned(BlockState state)
	{
		if(this.bannedBlocks.contains(state.getBlock().getRegistryName()))
			return true;
		
		if(this.bannedBlockTags.stream().anyMatch(tag -> tag.contains(state.getBlock())))
			return true;
	
		if(this.bannedMaterials.stream().anyMatch(mat -> state.getMaterial() == mat))
			return true;
		
		return false;
	}
	
	public static class Builder
	{
		private Set<IReplaceBlockRule> replaceRules = new HashSet<>();
		
		private Set<ResourceLocation> approvedBlocks = new HashSet<>();
		private Set<Material> approvedMaterials = new HashSet<>();
		private Set<ITag<Block>> approvedBlockTags = new HashSet<>();

		private Set<ResourceLocation> bannedBlocks = new HashSet<>();
		private Set<Material> bannedMaterials = new HashSet<>();
		private Set<ITag<Block>> bannedBlockTags = new HashSet<>();

		private boolean bypassGriefFlag = false;
		
		public Builder(BlockProtectionRule... rules)
		{
			for(BlockProtectionRule rule : rules)
			{
				this.replaceRules.addAll(rule.getReplaceRules());
				
				this.approvedBlocks.addAll(rule.getApprovedBlocks());
				this.approvedMaterials.addAll(rule.getApprovedMaterials());
				this.approvedBlockTags.addAll(rule.getApprovedTags());
				
				this.bannedBlocks.addAll(rule.getBannedBlocks());
			}
		}
		
		public Builder addReplaceRules(IReplaceBlockRule fn)
		{
			this.replaceRules.add(fn);
			return this;
		}
		
		public Builder addApprovedBlocks(Block... blocks)
		{
			Arrays.stream(blocks).map(b -> b.getRegistryName()).forEach(this.approvedBlocks::add);
			return this;
		}
		
		public Builder addApprovedBlocks(RegistryObject<Block>... blocks)
		{
			Arrays.stream(blocks).map(b -> b.getId()).forEach(this.approvedBlocks::add);
			return this;
		}
		
		public Builder addApprovedMaterials(Material... mats)
		{
			this.approvedMaterials.addAll(Lists.newArrayList(mats));
			return this;
		}
		
		public Builder addApprovedTags(ITag<Block>... tags)
		{
			this.approvedBlockTags.addAll(Lists.newArrayList(tags));
			return this;
		}
		
		public Builder addBannedBlocks(BlockProtectionRule rule)
		{
			this.bannedBlocks.addAll(rule.getBannedBlocks());
			return this;
		}
		
		public Builder addBannedBlocks(Block... blocks)
		{
			Arrays.stream(blocks).map(b -> b.getRegistryName()).forEach(this.bannedBlocks::add);
			return this;
		}
		
		public Builder addBannedBlocks(RegistryObject<Block>... blocks)
		{
			Arrays.stream(blocks).map(b -> b.getId()).forEach(this.bannedBlocks::add);
			return this;
		}
		
		public Builder setBypassGriefRule()
		{
			this.bypassGriefFlag = true;
			return this;
		}
		
		public BlockProtectionRule build()
		{
			BlockProtectionRule rule = new BlockProtectionRule();
			
			rule.addReplaceRules(this.replaceRules);
			
			rule.addApprovedBlocks(this.approvedBlocks);
			rule.addApprovedMaterials(this.approvedMaterials);
			rule.addApprovedTags(this.approvedBlockTags);
			
			rule.addBannedBlocks(this.bannedBlocks);
			
			if(this.bypassGriefFlag)
				rule.setBypassGriefRule();
			
			return rule;
		}
	}
	
	@FunctionalInterface
	public interface IReplaceBlockRule
	{
		boolean replace(World world, BlockPos pos, BlockState oldState);
	}
}
