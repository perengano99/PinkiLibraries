package com.perengano99.PinkiLibraries.NMSApi;

import org.bukkit.Bukkit;

public class NMSAPI {
    
    public Object Server;
    
    public String VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    
    public Class<?> getNMSClass(String name) {
	String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	try {
	    return Class.forName("net.minecraft.server." + version + "." + name);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    return null;
	}
    }
}