package com.perengano99.PinkiLibraries;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.perengano99.PinkiLibraries.CommandApi.CommandCreatorAPI;
import com.perengano99.PinkiLibraries.FileManagerApi.FileManagerAPI;
import com.perengano99.PinkiLibraries.NMSApi.PacketsApi.PacketsAPI;
import com.perengano99.PinkiLibraries.NMSApi.ReflectionUtil.ReflectUtil;
import com.perengano99.PinkiLibraries.NMSApi.NMSAPI;

public class PLIB extends JavaPlugin implements Listener {
    
    public static PLIB pl;
    private static Plugin plug;
    
    public static CommandCreatorAPI CommandCreatorAPI = new CommandCreatorAPI();
    public static FileManagerAPI FileManagerAPI = new FileManagerAPI();
    public static PacketsAPI PacketsAPI = new PacketsAPI();
    public static NMSAPI NMSAPI = new NMSAPI();
    
    public PLIB() {
	pl = this;
    }
    
    @Override
    public void onEnable() {
	log("####################");
	log("##");
	log("## " + getName());
	log("##");
	log("####################");
	new ReflectUtil();

	super.onEnable();
    }
    
    @Override
    public void onDisable() {
	
    }
    
    public static Plugin setPlugin(Plugin plugin) {
	return plug = plugin;
    }
    
    public static Plugin getPlugin() {
	return plug;
    }
    
    public void log(Level level, String message) {
	Bukkit.getLogger().log(level, message);
    }
    
    public void log(String message) {
	Bukkit.getLogger().log(Level.INFO, message);
    }
    
    public static boolean prbobalityOf(int max, int min) {
	min = (int) Math.ceil(min);
	max = (int) Math.floor(max);
	int rnd = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	return rnd <= min;
    }
}
