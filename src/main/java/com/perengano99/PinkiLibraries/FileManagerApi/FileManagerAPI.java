package com.perengano99.PinkiLibraries.FileManagerApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.collect.Maps;
import com.perengano99.PinkiLibraries.PLIB;

public class FileManagerAPI {

	private static HashMap<String, File> Files = Maps.newHashMap();
	private static HashMap<String, FileConfiguration> Configs = Maps.newHashMap();

	public void loadFile(String path, String file) {
		File F = new File(path + "/" + file);
		if (!F.exists()) {
			try {
				F.getParentFile().mkdirs();
				F.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Files.put(file, F);
		Configs.put(file, Configuration(F.getName()));
	}
	
	public void loadDeafaultFile(String path, String file) {
		File F = new File(path + "/" + file);
		OutputStream out = null;
		InputStream defFStream = PLIB.getPlugin().getResource(file);
		if (!F.exists()) {
			try {
				F.getParentFile().mkdirs();
				F.createNewFile();
				if (defFStream != null) {
					out = new FileOutputStream(F);
					int read;
					byte[] bytes = new byte[1024];

					while ((read = defFStream.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (defFStream != null) {
					try {
						defFStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}
		Files.put(file, F);
		Configs.put(file, Configuration(F.getName()));
	}
	
	public File getFile(String file) {
		return Files.get(file);
	}
	
	public void saveFile(String file) {
		try {
			File F = Files.get(file);
			getConfig(file).save(F);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FileConfiguration getConfig(String file) {
		return Configs.get(file);
	}

	public void reloadFile(String file) {
		Configs.put(file, Configuration(file));
	}
	
	

	private FileConfiguration Configuration(String name) {
		return YamlConfiguration.loadConfiguration(Files.get(name));

	}

}
