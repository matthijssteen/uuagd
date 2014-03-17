package ag;

import util.Value;

public class AttrDef extends Value {
	public final String dataType;
	public final AttrKind kind;
	public final String name;
	public final String type;
	
	public AttrDef(String dataType, AttrKind kind, String name, String type) {
		this.dataType = dataType;
		this.kind = kind;
		this.name = name;
		this.type = type;
	}
}
