package ag;

import util.Value;

public class AttrDef extends Value {
	final public String dataType;
	final public AttrKind kind;
	final public String name;
	final public String type;
	
	public AttrDef(String dataType, AttrKind kind, String name, String type) {
		this.dataType = dataType;
		this.kind = kind;
		this.name = name;
		this.type = type;
	}
}
