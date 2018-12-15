package com.perengano99.PinkiLibraries.CommandApi.interfaces;

import org.bukkit.command.CommandSender;

public abstract interface commandExecutorInterface {

	public abstract boolean execute(CommandSender sender, String label, String[] args);
	
}
