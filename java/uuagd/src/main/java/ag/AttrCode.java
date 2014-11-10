package ag;

import java.util.List;

import util.Value;

public class AttrCode extends Value {
	final public List<AttrId> attrIds;
	final public List<AttrId> attrUsed;
	final public String code;
	
	public AttrCode(List<AttrId> attrIds, List<AttrId> attrUsed, String code) {
		this.attrIds = attrIds;
		this.attrUsed = attrUsed;
		this.code = code;
	}
}
