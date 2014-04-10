package util;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceCallback {
	public static interface Callback {
        public String replaceMatch(MatchResult match);
    }
	
	public static String replace(Pattern pattern, String subject, Callback callback) {
		StringBuffer sb = new StringBuffer();
		Matcher m = pattern.matcher(subject);
		while (m.find()) {
			m.appendReplacement(sb,
					Matcher.quoteReplacement(
							callback.replaceMatch(
									m.toMatchResult())));
		}
		m.appendTail(sb);
		return sb.toString();
	}
}