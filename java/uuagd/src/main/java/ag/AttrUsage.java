package ag;

import java.util.Map;
import java.util.Set;

import util.Value;

public class AttrUsage extends Value {
	final public String scope;
	final public String name;
	final public Map<String, Map<String, Set<AttrDef>>> defs;
	
	public AttrUsage(String scope, String name, Map<String, Map<String, Set<AttrDef>>> defs) {
		this.scope = scope;
		this.name = name;
		this.defs = defs;
	}
	
	public String getScopedName() {
		return scope + '.' + name;
	}
	
	public String getAliasScopedName() {
		return needsAlias() ? "loc." + scope + "'" + name : getScopedName();
	}
	
	public boolean needsAlias() {
		return defs != null && !scope.equals("loc");
	}
	
	public String getType() {
		if (defs == null) {
			return null;
		}
		for (Map<String, Set<AttrDef>> defsByAlt: defs.values()) {
			for (Set<AttrDef> defs: defsByAlt.values()) {
				for (AttrDef def: defs) {
					return def.type;
				}
			}
		}
		return null;
	}
}
