package com.perengano99.PinkiLibraries.NMSApi.PacketsApi;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.perengano99.PinkiLibraries.PLIB;
import com.perengano99.PinkiLibraries.NMSApi.NMSAPI;

public class PacketsAPI {
    
    private NMSAPI nmsapi = PLIB.NMSAPI;
    
    public void sendPacket(Player player, Packet packet) {
	try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", nmsapi.getNMSClass("Packet")).invoke(playerConnection, packet.getPacket());
            Bukkit.getLogger().log(Level.WARNING, "PEDOOO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
