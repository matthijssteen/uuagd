package ag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Value;

public class Types extends Value {
	final public Set<String> inclTypeSet;
	final public List<Set<String>> exclTypeSets;
	
	public Types(Set<String> inclTypeSet, List<Set<String>> exclTypeSets) {
		this.inclTypeSet = inclTypeSet;
		this.exclTypeSets = exclTypeSets;
	}
	
	public Set<String> getConcreteSet(Set<String> all) {
		Set<String> typeSet;
		if (inclTypeSet.contains("*")) {
			typeSet = new HashSet<String>(all);
		} else {
			typeSet = new HashSet<String>(inclTypeSet);
		}
		for (Set<String> exclTypeSet: exclTypeSets) {
			if (exclTypeSet.contains("*")) {
				return new HashSet<String>();
			}
			typeSet.removeAll(exclTypeSet);
		}
		return typeSet;
	}
	
	public static Types fromString(String text) {
		String[] typeSets = text.split("\\-");
		Set<String> inclTypeSet = parseTypeSet(typeSets[0]);
		List<Set<String>> exclTypeSets = new ArrayList<Set<String>>();
		for (int i = 1, n = typeSets.length; i < n; i++) {
			exclTypeSets.add(parseTypeSet(typeSets[i]));
		}
		return new Types(inclTypeSet, exclTypeSets);
	}
	
	private static Set<String> parseTypeSet(String text) {
		String[] types = text.trim().split("\\s+");
		Set<String> typeSet = new HashSet<String>();
		for (String type: types) {
			typeSet.add(type.trim());
		}
		return typeSet;
	}
}
