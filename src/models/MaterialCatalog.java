package models;

import java.util.TreeMap;

public class MaterialCatalog {

	private TreeMap<String, Material> materialMap;
	
	public MaterialCatalog () {
		materialMap=new TreeMap<String, Material>();
		
	}
	
	public TreeMap<String, Material> getMaterialMap() {
		return this.materialMap;
	}
	
	public void addMaterial(Material material ) {
		materialMap.put(material.getID(), material);
	}
	
	public Material findMaterial(String title) throws MaterialNotFoundException {
		for (Material next : materialMap.values()) {
			if (next.getTitle().equalsIgnoreCase(title.trim())) {
				return next;
			}
		}
		throw new MaterialNotFoundException();
	}
	
}
