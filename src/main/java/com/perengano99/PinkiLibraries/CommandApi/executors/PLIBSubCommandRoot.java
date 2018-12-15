package com.perengano99.PinkiLibraries.CommandApi.executors;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.perengano99.PinkiLibraries.CommandApi.SenderType;
import com.perengano99.PinkiLibraries.CommandApi.interfaces.subcommandRootExecutorInterface;

public abstract class PLIBSubCommandRoot implements subcommandRootExecutorInterface {

	public abstract String[] setAliases();

	public abstract String setDescription();

	public List<String> getAliases() {
		if (!(this.setAliases() == null)) {
			return Arrays.asList(this.setAliases());
		}
		return null;
	}

	public String getDescription() {
		if (!(this.setDescription() == null)) {
			return this.setDescription();
		}
		return null;
	}
	
	public abstract String setNoSubcommandExistMessage();
	
	public String getNoSubcommandExistMessage() {
		if (!(this.setNoSubcommandExistMessage() == null)) {
			return this.setNoSubcommandExistMessage();
		}
		return "";
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
