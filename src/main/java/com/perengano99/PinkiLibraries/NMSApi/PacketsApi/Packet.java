package com.perengano99.PinkiLibraries.NMSApi.PacketsApi;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.perengano99.PinkiLibraries.PLIB;
import com.perengano99.PinkiLibraries.NMSApi.NMSAPI;

public class Packet {
    
    private NMSAPI nmsapi = PLIB.NMSAPI;
    
    String name;
    Constructor constructor;
    Object packet;
    
    public Packet(String packet) {
	name = packet;
	try {
	    this.constructor = nmsapi.getNMSClass(packet).getConstructor(int[].class);
	} catch (NoSuchMethodException | SecurityException e) {
	    e.printStackTrace();
	}
    };
    
    public void setValue(int value) {
	try {
	    packet = nmsapi.getNMSClass("PacketPlayOutEntityDestroy").getConstructor(int[].class).newInstance(getArray(int.class, value));
	} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
		| SecurityException e) {
	    e.printStackTrace();
	}
    }
    
    public Object getPacket() {
	return packet;
    }
    
    private Object getArray(Class<?> type, Object... objects) {
	Object array = Array.newInstance(type, objects.length);
	try {
	    Integer contador = 0;
	    for (Object o : objects) {
		Array.set(array, contador, o);
		contador++;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return array;
    }
    
}
