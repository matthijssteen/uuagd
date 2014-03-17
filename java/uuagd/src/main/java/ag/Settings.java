package ag;

import java.util.List;

import util.Value;

public class Settings extends Value {
	public List<AttrId> whitelist;
	public List<AttrId> blacklist;
	public boolean filterKids = false;
	public boolean noDefaultImport = false;
	
	public boolean isAttrVisible(AttrId attr) {
		return (whitelist == null || inList(attr, whitelist)) && !inList(attr, blacklist);
	}
	
	private static boolean inList(AttrId id, List<AttrId> ids) {
		if (ids == null) {
			return false;
		}
		for (AttrId listId: ids) {
			if (listId.dataType != null) {
				if (!listId.dataType.equals(id.dataType)) {
					continue;				
				}
				if (listId.alt != null && !listId.alt.equals(id.alt)) {
					continue;
				}
			}
			if (listId.scope != null && !listId.scope.equals(id.scope)) {
				continue;
			}
			if (listId.name.equals(id.name)) {
				return true;
			}
		}
		return false;
	}
}
