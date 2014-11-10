package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import parser.args.CmdArgsLexer;

public class Cmd {
	public static void exec(String cmd, Appendable... dests) throws IOException {
		System.out.println("Executing: " + cmd);

		// Execute the given command and wait until it has terminated.
		String[] args = parseCmd(cmd);
		ProcessBuilder pb = new ProcessBuilder(args);
		pb.redirectErrorStream(true);
		Process p = pb.start();
		Thread t = inheritIO(p.getInputStream(), dests);
		t.start();
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		// Waiting for the process does not mean that we are finished reading the output stream,
		// so we have to join the stream handler thread.
		try {
			t.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		if (p.exitValue() == 0) {
			System.out.println("Exited with success.");
		} else {
			System.out.println("Exited with failure.");

			// If something went wrong during the execution of the command,
			// we have to terminate this program as well, otherwise it would become unreliable.
			System.exit(0);
		}
	}

	private static String[] parseCmd(String cmd) throws IOException {
		ANTLRInputStream input = new ANTLRInputStream(new StringReader(cmd));
		CmdArgsLexer lexer = new CmdArgsLexer(input);
		List<String> args = new ArrayList<String>();
		Token token = lexer.nextToken();
		while(token.getType() != Recognizer.EOF) {
			args.add(token.getText());
			token = lexer.nextToken();
		}
		return args.toArray(new String[args.size()]);
	}

	private static Thread inheritIO(final InputStream src, final Appendable[] dests) {
		return new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					final BufferedReader br = new BufferedReader(new InputStreamReader(src));
					Throwable brEx = null;
					try {
						String line;
						while ((line = br.readLine()) != null) {
							for (Appendable dest: dests) {
								dest.append(line).append(System.getProperty("line.separator"));
							}
						}
					} catch (IOException e) {
						brEx = e;
						throw e;
					} finally {
						if (br != null) {
							if (brEx != null) {
								try {
									br.close();
								} catch (Throwable t) {}
							} else {
								br.close();
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
