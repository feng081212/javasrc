package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class CustomThread extends Thread {

	@Override
	public final void run() {
		do {
			runSingle();
		} while (isCycle());
	}

	public abstract void runSingle();

	public abstract boolean isCycle();

	public static void main(String[] args) {

		try {
			String[] cmd = new String[] { "cmd", "/c", "start","D:/inform/qttz_sms_subscribe/wrun.bat" };
			// Runtime.getRuntime().exec(cmd);
			// Process child = Runtime.getRuntime().exec(cmd);
			String result = "";
			BufferedReader br = null;
			BufferedInputStream in = null;
			try {
				Process p = Runtime.getRuntime().exec("tasklist /v /fo csv |findstr /i java");
				in = new BufferedInputStream(p.getInputStream());
				br = new BufferedReader(new InputStreamReader(in));
				String lineStr;
				while ((lineStr = br.readLine()) != null) {
					result += lineStr;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			} finally {
				if (br != null) {
					try {
						br.close();
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("ShellUtil.ExeShell=>" + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
