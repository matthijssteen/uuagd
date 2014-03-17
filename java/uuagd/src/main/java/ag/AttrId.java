package ag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import util.Value;

public class AttrId extends Value {
	public String dataType;
	public String alt;
	public String scope;
	public String name;
	
	public AttrId(String qualName) throws Exception {
		String[] parts = qualName.trim().split("\\.");
		if (parts.length < 1 || parts.length > 4) {
			throw new Exception("A qualified attribute name should be in one of the following forms: name, scope.name, DataType.name, DataType.scope.name, DataType.Constructor.name, DataType.Constructor.scope.name");
		}
		List<String> names = Arrays.asList(parts);
		Collections.reverse(names);
		
		name = names.get(0);
		names = names.subList(1, names.size());
		
		if (!names.isEmpty()) {
			String first = names.get(0).substring(0, 1);
			if (first.equals(first.toLowerCase())) {
				scope = names.get(0);
				names = names.subList(1, names.size());
			}
			
			if (!names.isEmpty()) {
				dataType = names.get(0);
				
				if (names.size() == 2) {
					alt = names.get(1);
				}
			}
		}
	}
	
	public AttrId(String dataType, String alt, String scope, String name) {
		this.dataType = dataType;
		this.alt = alt;
		this.scope = scope;
		this.name = name;
	}
}
