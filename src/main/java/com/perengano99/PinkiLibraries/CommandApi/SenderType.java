package com.perengano99.PinkiLibraries.CommandApi;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.perengano99.PinkiLibraries.PLIB;

public enum SenderType {

	PLAYER,
	CONSOLE,
	ALL;
	
	public boolean getType(CommandSender sender) {
		
		switch(this) {
		case PLAYER:
			if (sender instanceof Player) {
				return true;
			} else {return false;}
		
		case CONSOLE:
			if (sender instanceof PLIB) {
				return true;
			} else {return false;}
			
		case ALL:
			return true;
		default:
			return true;
		}

	}
	
}
