package com.perengano99.PinkiLibraries.CommandApi.executors;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.perengano99.PinkiLibraries.CommandApi.SenderType;
import com.perengano99.PinkiLibraries.CommandApi.interfaces.commandExecutorInterface;

public abstract class PLIBCommand implements commandExecutorInterface {

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
	
	public abstract int setMaxArgs();

	public abstract int setMinArgs();

	public int getMaxArgs() {
		return this.setMaxArgs();

	}

	public int getMinArgs() {
		return this.setMinArgs();
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
		if (this.onlyFor() == SenderType.PLAYER) {
			if(!(this.setOnlyForMessage() == null)) {
				sender.sendMessage(this.setOnlyForMessage());
			}
		}
		
	}
	
	public boolean isSender(CommandSender sender) {
		
		if (this.onlyFor() == null) {
			return true;
		}
		
		SenderType type = this.onlyFor();
		
		switch(type) {
		case PLAYER:
			if (sender instanceof Player) {
				return true;
			} else {return false;}
		case CONSOLE:
			if (!(sender instanceof Player)) {
				return true;
			} else {return false;}
		case ALL:
			return true;
		default:
			return true;
			
		}
		
	}
	

}
