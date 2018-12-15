package com.perengano99.PinkiLibraries.CommandApi.executors;

import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Maps;
import com.perengano99.PinkiLibraries.CommandApi.SenderType;
import com.perengano99.PinkiLibraries.CommandApi.interfaces.subcommandExecutorIterface;

public abstract class PLIBSubCommand implements subcommandExecutorIterface {

	private String name;

	public HashMap<String, PLIBSubCommand> subcmds = Maps.newHashMap();

	public PLIBSubCommand(String name) {
		this.name = name;

		this.subcmds.put(name, this);
	}

	public String getname() {
		return this.name;
	}

	public abstract int setMaxArgs();

	public abstract int setMinArgs();

	public int getMaxArgs() {
		return this.setMaxArgs() + 1;

	}

	public int getMinArgs() {
		return this.setMinArgs() + 1;
	}

	public abstract String setPermission();

	public abstract String setNotPermissionMessage();

	public String getPermission() {
		if (!(this.setPermission() == null)) {
			return this.setPermission();
		}
		return "";
	}

	public String getNotPermissionMessage() {
		if (!(this.setNotPermissionMessage() == null)) {
			return this.setNotPermissionMessage();
		}
		return "";
	}

	public abstract String setFewArgumentsMessage();

	public abstract String setTooManyArgumentsMessage();

	public String getFewArgumentsMessage() {
		if (!(this.setFewArgumentsMessage() == null)) {
			return this.setFewArgumentsMessage();
		}
		return "";
	}

	public String getTooManyArgumentsMessage() {
		if (!(this.setTooManyArgumentsMessage() == null)) {
			return this.setTooManyArgumentsMessage();
		}
		return "";
	}

	public abstract SenderType onlyFor();

	public abstract String setOnlyForMessage();

	public void onlyForMessage(CommandSender sender) {
		if (!(this.setOnlyForMessage() == null)) {
			sender.sendMessage(this.setOnlyForMessage());

		}

	}

	public boolean isSender(CommandSender sender) {

		if (this.onlyFor() == null) {
			return true;
		}

		SenderType type = this.onlyFor();

		switch (type) {
		case PLAYER:
			if (sender instanceof Player) {
				return true;
			} else {
				return false;
			}
		case CONSOLE:
			if (!(sender instanceof Player)) {
				return true;
			} else {
				return false;
			}
		case ALL:
			return true;
		default:
			return true;

		}
	}
}
