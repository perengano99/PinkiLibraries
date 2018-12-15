package com.perengano99.PinkiLibraries.CommandApi;

import org.bukkit.plugin.Plugin;

import com.perengano99.PinkiLibraries.CommandApi.executors.PLIBCommand;
import com.perengano99.PinkiLibraries.CommandApi.executors.PLIBSubCommand;
import com.perengano99.PinkiLibraries.CommandApi.executors.PLIBSubCommandRoot;

public class CommandCreatorAPI {
	
	public void newCommand(Plugin plugin, String commandName, PLIBCommand executor) {
		new CommandCreatorBase(plugin, commandName, executor);
	}
	
	public void newRootCommand(Plugin plugin, String commandName, PLIBSubCommandRoot executor, PLIBSubCommand[] subcommand) {
		new CommandCreatorBase(plugin, commandName, executor, subcommand);
	}

}
