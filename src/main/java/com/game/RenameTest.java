package com.game;

import java.io.File;

public class RenameTest {
	public static void main(String[] args) {
		String rootPath = "C:\\dev\\works\\kd-html\\img";
		String targetPath = "C:\\dev\\works\\kd-html\\img\\cards\\";
		File rootFile = new File(rootPath);
		File[] dirs = rootFile.listFiles();
		int num = 0;
		for (File dir : dirs) {
			// System.out.print(dir.getName()+", ");
			// System.out.println(dir.isDirectory());
			if (dir.isDirectory()) { // 폴더라면
				File[] files = dir.listFiles();
				for (File file : files) {
					if (file.getName().contains("Th")) {
						continue;
					}
					File targetFile = new File(targetPath + (num++) + ".png");
					file.renameTo(targetFile);
				}
			}
		}
	}

}
