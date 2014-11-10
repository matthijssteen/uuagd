package util;

import java.io.File;
import java.io.IOException;

public class Files {
	public static void delete(File file) throws IOException {
		if (!file.delete()) {
			throw new IOException("Failed to delete file: " + file);
		}
	}
	
	public static String getWithoutExt(String filename) {
		int pos = filename.lastIndexOf('.');
		if (pos == -1) return filename;
		return filename = filename.substring(0, pos);
	}
}
