package ag;

import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import util.ReplaceCallback;

public class TypesFactory {
	final private static Pattern TY_PAT = Pattern.compile("[A-Z][a-zA-Z0-9_']*");
	
	final private Map<String, String> setDecls;
	
	public TypesFactory(Map<String, String> setDecls) {
		this.setDecls = setDecls;
	}
	
	public Types create(String text) {
		return Types.fromString(ReplaceCallback.replace(TY_PAT, text, new ReplaceCallback.Callback() {
			@Override
			public String replaceMatch(MatchResult match) {
				String type = match.group();
				if (setDecls.containsKey(type)) {
					return setDecls.get(type);
				}
				return type;
			}
		}));
	}
}
