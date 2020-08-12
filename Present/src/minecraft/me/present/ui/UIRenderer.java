package me.present.ui;

import java.util.Collections;
import java.util.Comparator;

import me.present.Present;
import me.present.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class UIRenderer extends Gui {
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public static class ModuleComparator implements Comparator<Module> {

		@Override
		public int compare(Module arg0, Module arg1) {
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.name) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.name)) {
				return -1;
			}
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.name) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.name)) {
				return 1;
			}
			return 0;
		}
		
	}

	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		
		Collections.sort(Present.modules, new ModuleComparator()); 
		
		fr.drawStringWithShadow(Present.clientName + " " + Present.clientVersion, 1, 1, 0xffffff);
		
		int count = 0;
		for(Module module : Present.modules) {
			if(!module.isEnabled())
				continue;
			
			double offset1 = count*(fr.FONT_HEIGHT + 1);
			double offset2 = count*(fr.FONT_HEIGHT +2);
			
			Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(module.name) - 5, offset1, sr.getScaledWidth() - fr.getStringWidth(module.name) -3, 1 + fr.FONT_HEIGHT + offset2, -0x8B0000);
			Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(module.name) - 3, offset1, sr.getScaledWidth(), 1 + fr.FONT_HEIGHT + offset2, 0x90000000);
			fr.drawStringWithShadow(module.name, sr.getScaledWidth() - fr.getStringWidth(module.name) - 1, 1 + offset2, 0xffffff);
			
			count++;
			
		}
	}
	
}