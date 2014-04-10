package util;

import java.io.File;
import java.io.IOException;

public class Files {
	public static void delete(File file) throws IOException {
		if (!file.delete()) {
			throw new IOException("Failed to delete file: " + file);
		}
	}
}
