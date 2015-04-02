package org.gaofeng.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CheckFileIfExists {

	private static Map <String,String> mapFileNames;
	
	
	public static Map <String,String> getFileList(){
		if(mapFileNames==null ){
			mapFileNames=doit();
			return mapFileNames;
		}else{
			return mapFileNames;
		}
		
	}
	private static Map <String,String> doit() {
		mapFileNames=new HashMap<String, String>();
		String path=MappingRule.domainPath;
		
		  File file=new File(path);
		  File[] tempList = file.listFiles();
		  for (int i = 0; i < tempList.length; i++) {
		   if (tempList[i].isFile()) {
			   mapFileNames.put(tempList[i].getPath(), "notNUll");
		   }
		  }
		return mapFileNames;
	}
}
