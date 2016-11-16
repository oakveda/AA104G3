package util;

import java.io.*;
import javax.servlet.http.Part;

public class Output {

	/* 把上傳的檔案轉為byte[]回傳 */
	public byte[] outputBytes(Part part) {
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try {
			is = part.getInputStream();
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024 * 4];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		return baos.toByteArray();
	}
}
