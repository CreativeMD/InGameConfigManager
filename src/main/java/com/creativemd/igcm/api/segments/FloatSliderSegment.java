package com.creativemd.igcm.api.segments;

import java.util.ArrayList;

import com.creativemd.creativecore.gui.GuiControl;
import com.creativemd.creativecore.gui.container.SubGui;
import com.creativemd.creativecore.gui.controls.gui.GuiAnalogeSlider;
import com.creativemd.creativecore.gui.controls.gui.GuiTextfield;

import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FloatSliderSegment extends FloatSegment{
	
	public FloatSliderSegment(String title, Float defaultValue, float min, float max) {
		super(title, defaultValue, min, max);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ArrayList<GuiControl> createGuiControls(SubGui gui, int x, int y,
			int maxWidth) {
		ArrayList<GuiControl> controls = super.createGuiControls(gui, x, y, maxWidth);
		controls.remove(1);
		controls.add(new GuiAnalogeSlider(getKey(), x+maxWidth-80, y, 70, 14, value, min, max));
		return controls;
	}
	
	@Override
	public void saveFromControls() {
		float valueBefore = value;
		try{
			value = MathHelper.clamp(((GuiAnalogeSlider) getGuiControl(getKey())).value, min, max);
		}catch(Exception e){
			value = valueBefore;
		}
	}
	

}