package ag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrammarInfo {
	final public Map<String, Map<AttrKind, Map<String, AttrDef>>> defsByDataType =
			new HashMap<String, Map<AttrKind, Map<String, AttrDef>>>();

	final public Map<String, Map<String, Map<String, String>>> kidsByDataType =
			new HashMap<String, Map<String, Map<String, String>>>();
	
	final public Map<String, Map<String, List<AttrUsage>>> attrUsages =
			new HashMap<String, Map<String, List<AttrUsage>>>();
	
	final public Map<String, Map<String, List<AttrCode>>> attrCodes =
			new HashMap<String, Map<String, List<AttrCode>>>();
}
