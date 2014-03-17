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
				sb.append("\"");
				break;
			default:
				sb.append(Character.toChars(point));
				break;
			}
		}
		sb.append('"');
		return sb.toString();
	}
}
