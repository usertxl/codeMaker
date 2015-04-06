package org.gaofeng.common.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据文件名获得jar包同级目录下的文件里的内容</br> 字符集编码采用UTF-8
 * 
 * @author gf
 *
 */
public class FileUtils {
	private static FileUtils fu = new FileUtils();

	private FileUtils() {

	}

	public static FileUtils getInstance() {
		if (fu == null) {
			return new FileUtils();
		} else {
			return fu;
		}
	}

	/**
	 * 根据文件名获得与jar包同级目录的文件
	 * 
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public File getFile(String fileName) {
		String jarPath = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		jarPath = jarPath.substring(jarPath.indexOf("/") + 1,
				jarPath.lastIndexOf("/"));
		try {
			jarPath = URLDecoder.decode(jarPath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("路径可能不正确，请确认是否实在Windows操作系统的英文目录下运行的程序");
			e.printStackTrace();
		}
		File file = new File(jarPath + "/" + fileName);
		return file;
	}

	private List<String> getFileStrs(File file) {
		List<String> listStrings = new ArrayList<String>();
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), "utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String temp = bufferedReader.readLine();
			while (temp != null) {
				listStrings.add(temp);
				temp = bufferedReader.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("请确认jar包同级目录下文件\"" + file.getName() + "\"是否存在");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listStrings;
	}

	/**
	 * 直接输入文件全名（文件名+后缀名），</br> 返回该文件的所有字符串。</br> 以List存储，一行就是一行
	 * 
	 * @param fileName
	 * @return
	 */
	public List<String> getAllStrsByFileName(String fileName) {

		return getFileStrs(getFile(fileName));
	}

	/**
	 * 直接输入文件全名（文件名+后缀名）， </br> 返回该文件的所有sql字符串。 </br>过滤掉注释内容</br> 以List存储，一行就是一行
	 * 
	 * @param fileName
	 * @return
	 */
	public List<String> getSqlStrsByFileName(String fileName) {
		Boolean commentFlag = false;
		List<String> listStr = getFileStrs(getFile(fileName));
		List<String> listSqlStr = new ArrayList<String>();
		for (String line : listStr) {
			String currentLine;
			int comment1 = line.indexOf("--");
			int comment2 = line.indexOf("/**");
			int comment3 = line.indexOf("*/");
			if (!commentFlag) {
				if (comment1 > -1 && comment2 > -1 && comment1 < comment2) {
					currentLine = line.substring(0, comment1);
					if (currentLine != null && !"".equals(currentLine)) {
						listSqlStr.add(currentLine);
					}

				} else if (comment1 > -1) {
					currentLine = line.substring(0, comment1);
					if (currentLine != null && !"".equals(currentLine)) {
						listSqlStr.add(currentLine);
					}
				} else if (comment2 > -1) {
					commentFlag=true;
					currentLine = line.substring(0, comment2);
					if (currentLine != null && !"".equals(currentLine)) {
						listSqlStr.add(currentLine);
					}
				} else {
					if (line != null && !"".equals(line)) {
						listSqlStr.add(line);
					}
				}
			}else{
				if(comment3>-1){
					commentFlag=false;
					currentLine=line.substring(comment3+2,line.length());
					if(!"".equals(currentLine)){
						listSqlStr.add(currentLine);
					}
				}
			}
		}
		return listSqlStr;
	}
}
