package com.semi.policy;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {
	@Override
	public File rename(File oldFile) {

		File newFile=null;
		do {
			//날짜(시간)+임의값으로 rename 설정
			long currentTime=System.currentTimeMillis();//시간출력을 위한것
			SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS"); //연도 달 날짜 표기 방식
			int rndNum=(int)(Math.random()*1000);//랜덤한 난수값을 출력해줄때 소수점으로 표시되니까 *1000(랜덤 값의 범위)해주고 자료형 변환
			
			//파일명처리하기
			String oldName=oldFile.getName();
			int point=oldName.lastIndexOf("."); //문자열 오른쪽부터 문자를 세어나감 파일 확장자에 사용
			String ext="";
			if(point>-1) {
				ext=oldName.substring(point);
			}
			//String[] t=oldName.split(".");
			//ext=t[t.length-1];
			
			//새파일생성
			String newName="semi_"+sf.format(new Date(currentTime))
					+"_"+rndNum+ext;
			
			newFile=new File(oldFile.getParent(),newName);
			
			
		}while(!createNew(newFile));
		
		
		return newFile;
	}
	
	private boolean createNew(File newFile) {
		try {
			return newFile.createNewFile();
		}catch(IOException e){
			return false;
		}
	}
}

