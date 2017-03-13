package com.creativemd.igcm.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import com.creativemd.creativecore.client.avatar.AvatarItemStack;
import com.creativemd.creativecore.gui.ContainerControl;
import com.creativemd.creativecore.gui.GuiControl;
import com.creativemd.creativecore.gui.container.SubContainer;
import com.creativemd.creativecore.gui.container.SubGui;
import com.creativemd.creativecore.gui.controls.gui.GuiAvatarButton;
import com.creativemd.igcm.IGCM;
import com.creativemd.igcm.IGCMGuiManager;
import com.creativemd.igcm.utils.SearchUtils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ConfigGroupElement extends ConfigSegment{
	
public ItemStack avatar;
	
	public ConfigGroupElement(String title, ItemStack avatar) {
		super(title);
		this.avatar = avatar;
	}
	
	public ArrayList<ContainerControl> createContainerControls(SubContainer container, int x, int y, int maxWidth)
	{
		return new ArrayList<>();
	}
	
	@SideOnly(Side.CLIENT)
	public ArrayList<GuiControl> createGuiControls(SubGui gui, int x, int y, int maxWidth)
	{
		ArrayList<GuiControl> controls = new ArrayList<>();
		controls.add(new GuiAvatarButton(getPath(), title, x+5, y, 155, 16, new AvatarItemStack(avatar)) {
			@Override
			public void onClicked(int x, int y, int button)
			{
				IGCMGuiManager.openConfigGui(gui.getPlayer(), this.name);
			}
		});
		return controls;
	}
	
	@Override
	public boolean contains(String search)
	{
		return title.toLowerCase().contains(search) || key.toLowerCase().contains(search) || SearchUtils.canStackBeFound(avatar, search);
	}
	
	@Override
	public void saveFromControls()
	{
		
	}
}