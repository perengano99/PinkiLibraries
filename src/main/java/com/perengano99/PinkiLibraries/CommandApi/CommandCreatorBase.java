package com.perengano99.PinkiLibraries.CommandApi;

import java.lang.reflect.Method;

import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.Plugin;

import com.perengano99.PinkiLibraries.CommandApi.executors.PLIBCommand;
import com.perengano99.PinkiLibraries.CommandApi.executors.PLIBSubCommand;
import com.perengano99.PinkiLibraries.CommandApi.executors.PLIBSubCommandRoot;
import com.perengano99.PinkiLibraries.NMSApi.ReflectionUtil.ReflectUtil;

public class CommandCreatorBase extends BukkitCommand {
    
    private PLIBCommand executor;
    private PLIBSubCommand[] subcommands;
    private PLIBSubCommandRoot rootexecutor;
    
    private boolean hasSubs;
    
    public CommandCreatorBase(Plugin plugin, String name, PLIBCommand executor) {
	super(name);
	
	hasSubs = false;
	this.executor = executor;
	
	if (this.executor.getDescription() != null) {
	    setDescription(this.executor.getDescription());
	}
	
	if (this.executor.getAliases() != null) {
	    setAliases(this.executor.getAliases());
	}
	try {
	    Class<?> craftServer = ReflectUtil.getCBClass("CraftServer").getOrThrow();
	    Method method = ReflectUtil.getMethod(craftServer, "getCommandMap").getOrThrow();
	    SimpleCommandMap s = (SimpleCommandMap) ReflectUtil.invokeMethod(plugin.getServer(), method).getOrThrow();
	    s.register(name, this);
	} catch (SecurityException e) {
	    e.printStackTrace();
	}
    }
    
    public CommandCreatorBase(Plugin plugin, String name, PLIBSubCommandRoot executor, PLIBSubCommand[] subexecutors) {
	super(name);
	
	hasSubs = true;
	rootexecutor = executor;
	subcommands = subexecutors;
	
	if (rootexecutor.getAliases() != null) {
	    setAliases(this.rootexecutor.getAliases());
	}
	try {
	    Class<?> craftServer = ReflectUtil.getCBClass("CraftServer").getOrThrow();
	    Method method = ReflectUtil.getMethod(craftServer, "getCommandMap").getOrThrow();
	    SimpleCommandMap s = (SimpleCommandMap) ReflectUtil.invokeMethod(plugin.getServer(), method).getOrThrow();
	    s.register(name, this);
	} catch (SecurityException e) {
	    e.printStackTrace();
	}
    }
    
    @Override
    public boolean execute(CommandSender arg0, String arg1, String[] arg2) {
	
	if (!hasSubs) {
	    if (!executor.isSender(arg0)) {
		executor.onlyForMessage(arg0);
		return false;
	    }
	    if (!executor.getPermission().equalsIgnoreCase("")) {
		if (!arg0.hasPermission(executor.getPermission())) {
		    if (!executor.getNotPermissionMessage().equalsIgnoreCase("")) {
			arg0.sendMessage(executor.getNotPermissionMessage());
		    }
		    return false;
		}
	    }
	    if (arg2.length > getmaxargs()) {
		if (executor.getTooManyArgumentsMessage().equalsIgnoreCase("")) {
		    arg0.sendMessage(executor.getTooManyArgumentsMessage());
		}
		return false;
	    }
	    if (arg2.length < getminargs()) {
		if (!executor.getFewArgumentsMessage().equalsIgnoreCase("")) {
		    arg0.sendMessage(executor.getFewArgumentsMessage());
		}
		return false;
	    }
	    executor.execute(arg0, arg1, arg2);
	    return true;
	} else {
	    if (!rootexecutor.isSender(arg0)) {
		rootexecutor.onlyForMessage(arg0);
		return false;
	    }
	    if (!rootexecutor.getPermission().equalsIgnoreCase("")) {
		if (!arg0.hasPermission(rootexecutor.getPermission())) {
		    if (!rootexecutor.getNotPermissionMessage().equalsIgnoreCase("")) {
			arg0.sendMessage(rootexecutor.getNotPermissionMessage());
		    }
		    return false;
		}
	    }
	    if (arg2.length == 0) {
		rootexecutor.execute(arg0, arg1);
		return true;
	    }
	    if (matchsubcmd(arg2[0])) {
		if (!checksubsender(arg2[0], arg0)) {
		    getsubcmd(arg2[0]).onlyForMessage(arg0);
		    return false;
		}
		if (!getsubcmd(arg2[0]).getPermission().equalsIgnoreCase("")) {
		    if (!arg0.hasPermission(getsubcmd(arg2[0]).getPermission())) {
			if (!rootexecutor.getNotPermissionMessage().equalsIgnoreCase("")) {
			    arg0.sendMessage(getsubcmd(arg2[0]).getNotPermissionMessage());
			}
			return false;
		    }
		}
		if (arg2.length > getsubmaxargs(arg2[0])) {
		    if (!getsubcmd(arg2[0]).getTooManyArgumentsMessage().equalsIgnoreCase("")) {
			arg0.sendMessage(getsubcmd(arg2[0]).getTooManyArgumentsMessage());
		    }
		    return false;
		}
		if (arg2.length < getsubminargs(arg2[0])) {
		    if (!getsubcmd(arg2[0]).getFewArgumentsMessage().equalsIgnoreCase("")) {
			arg0.sendMessage(getsubcmd(arg2[0]).getFewArgumentsMessage());
		    }
		    return false;
		}
		getsubcmd(arg2[0]).execute(arg0, arg1, arg2);
		return true;
	    } else {
		if (!this.rootexecutor.getNoSubcommandExistMessage().equalsIgnoreCase("")) {
		    arg0.sendMessage(this.rootexecutor.getNoSubcommandExistMessage());
		}
		return false;
	    }
	}
    }
    
    public int getmaxargs() {
	return this.executor.getMaxArgs();
    }
    
    public int getminargs() {
	return this.executor.getMinArgs();
    }
    
    public boolean checksubsender(String name, CommandSender sender) {
	for (PLIBSubCommand sub : this.subcommands) {
	    if (sub.isSender(sender)) {
		return true;
	    }
	}
	return false;
	
    }
    
    public int getsubmaxargs(String name) {
	for (PLIBSubCommand sub : this.subcommands) {
	    if (sub.subcmds.containsKey(name)) {
		PLIBSubCommand cmd = sub.subcmds.get(name);
		return cmd.getMaxArgs();
	    }
	}
	return 0;
    }
    
    public int getsubminargs(String name) {
	for (PLIBSubCommand sub : this.subcommands) {
	    if (sub.subcmds.containsKey(name)) {
		PLIBSubCommand cmd = sub.subcmds.get(name);
		return cmd.getMinArgs();
	    }
	}
	return 0;
    }
    
    public PLIBSubCommand getsubcmd(String name) {
	for (PLIBSubCommand sub : this.subcommands) {
	    if (sub.subcmds.containsKey(name)) {
		PLIBSubCommand cmd = sub.subcmds.get(name);
		return cmd;
	    }
	}
	return null;
    }
    
    public boolean matchsubcmd(String name) {
	for (PLIBSubCommand sub : this.subcommands) {
	    if (sub.subcmds.containsKey(name)) {
		return true;
	    }
	}
	return false;
    }
}
