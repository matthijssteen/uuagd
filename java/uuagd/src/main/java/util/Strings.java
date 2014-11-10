package util;

public class Strings {
	// Source: http://stackoverflow.com/a/20414826
	static class CodePointIterator {
		private final String sequence;
		private int index = 0;

		public CodePointIterator(final String sequence) {
			this.sequence = sequence;
		}

		public boolean hasNext() {
			return index < sequence.length();
		}

		public int next() {
			int codePoint = sequence.codePointAt(index);
			index += Character.charCount(codePoint);
			return codePoint;
		}
	}

	public static String toLiteral(final String s) {
		StringBuilder sb = new StringBuilder();
		sb.append('"');
		CodePointIterator it = new CodePointIterator(s);
		while (it.hasNext()) {
			int point = it.next();
			switch (point) {
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '"':
				sb.append("\\\"");
				break;
			default:
				sb.append(Character.toChars(point));
				break;
			}
		}
		sb.append('"');
		return sb.toString();
	}
	
	public static String removeOutsideChars(String s) {
		return s.substring(1, s.length() - 1);
	}

	public static String removeLeading(String s, String leading) {
		return s.trim().substring(leading.length()).trim();
	}

	public static String removeTrailing(String s, String trailing) {
		s = s.trim();
		return s.substring(0, s.length() - trailing.length()).trim();
	}
}
