package ag;

import java.io.File;
import java.util.List;

import net.sourceforge.argparse4j.annotation.Arg;
import util.Value;

public class Settings extends Value {
	@Arg
	public String uuagcOptions;
	
	@Arg
	public File agFile;
	
	@Arg
	public String castFn;
	
	@Arg
	public String castTy;
	
	@Arg
	public List<AttrId> whitelist;
	
	@Arg
	public List<AttrId> blacklist;
	
	@Arg
	public boolean filterKids = false;
	
	@Arg
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
