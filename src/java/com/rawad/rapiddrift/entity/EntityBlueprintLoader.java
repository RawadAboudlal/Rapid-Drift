package com.rawad.rapiddrift.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rawad.rapiddrift.entity.component.Component;
import com.rawad.rapiddrift.entity.factory.ComponentFactory;
import com.rawad.rapiddrift.util.Utils;

/**
 * @author Rawad
 *
 */
public class EntityBlueprintLoader {
	
	private static final String ENTITY_BLUEPRINT_EXTENSION = "ent";
	private static final String DATA_SEPARATOR = "=";
	
	private ComponentFactory<?>[] componentFactories;
	
	/**
	 * @param componentFactories
	 */
	public EntityBlueprintLoader(ComponentFactory<?>[] componentFactories) {
		super();
		
		this.componentFactories = componentFactories;
		
	}
	
	public Entity loadEntityBlueprint(String... pathParts) {
		
		Entity e = new Entity();
		HashMap<String, String> data = new HashMap<String, String>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
				Utils.getFilePath(ENTITY_BLUEPRINT_EXTENSION, pathParts))))) {
			
			String line = null;
			String compName = null;
			
			while((line = reader.readLine()) != null) {
				
				if(line.isEmpty()) {
					continue;
				}
				
				int separatorIndex = line.indexOf(DATA_SEPARATOR);
				
				if(separatorIndex == -1) {
					
					if(compName != null) {
						e.addComponent(this.getComponentFactory(compName).createComponent(data));
						data.clear();
					}
					
					compName = line.trim();
					
				} else {
					
					data.put(line.substring(0, separatorIndex).trim(), line.substring(separatorIndex + 1));
					
				}
				
			}
			
			// For the last component.
			e.addComponent(this.getComponentFactory(compName).createComponent(data));
			
		} catch (IOException ex) {
			ex.printStackTrace();
			Logger.getGlobal().log(Level.WARNING, "This entity could not be loaded.", ex);
		}
		
		return e;
		
	}
	
	public void saveEntityBlueprint(Entity e, String... pathParts) {
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		try (PrintWriter writer = new PrintWriter(Utils.getFilePath(ENTITY_BLUEPRINT_EXTENSION, pathParts))) {
			
			for(Component comp: e.getComponents()) {
				
				ComponentFactory<?> compFactory = this.getComponentFactory(comp);
				compFactory.getStringData(data, comp);
				
				writer.write(compFactory.getComponentName() + Utils.NL);
				
				for(String dataKey: data.keySet()) {
					writer.write("\t" + dataKey + DATA_SEPARATOR + data.get(dataKey) + Utils.NL);
				}
				
				data.clear();
				
			}
			
			writer.flush();
			
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		
	}
	
	private ComponentFactory<?> getComponentFactory(Component comp) {
		
		for(ComponentFactory<?> compFactory: componentFactories) {
			if(compFactory.matchesComponent(comp)) {
				return compFactory;
			}
		}
		
		throw new RuntimeException(String.format("The component %s has no factory to modify it.", comp));
		
	}
	
	private ComponentFactory<?> getComponentFactory(String compName) {
		
		for(ComponentFactory<?> compFactory: componentFactories) {
			if(compFactory.getComponentName().equals(compName)) {
				return compFactory;
			}
		}
		
		throw new RuntimeException(String.format("The component %s has no factory to create it.", compName));
		
	}
	
}
