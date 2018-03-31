package com.example.a22914.softwaretest3.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFile {
	
	private static String Path;
	private static ArrayList<String> list;
	private static ReadFile readFile;
	
	ReadFile(String path){
		list=new ArrayList<String>();
		Path=path;
	}
	
	public static ReadFile getElement(String path) {
		if(readFile!=null) {
			return readFile;
		}else {
			return new ReadFile(path);
		}
	}
	
	
	public static ArrayList<String>getString() throws IOException{
		File file = new File(Path);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

}
