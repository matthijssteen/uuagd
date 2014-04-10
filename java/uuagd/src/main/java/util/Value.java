package util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Value {
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append("[");
		boolean first = true;
		for (Field field: this.getClass().getDeclaredFields()) {
			int mod = field.getModifiers();
			if (Modifier.isPublic(mod)) {
				try {
					Object val = field.get(this);
					String repr = val != null ? val.toString() : "null";
					if (!first) {
						sb.append(", ");
					} else {
						first = false;
					}
					sb.append(field.getName());
					sb.append("=");
					sb.append(val != null && field.getType() == String.class ? Strings.toLiteral(repr) : repr);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
