package org.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.core.board.model.vo.Board;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtil")
public class FileUtil {

	private static final String filePath = "C:\\mp\\file\\"; // 물리적인 파일이 저장될 위치

	public List<Map<String, Object>> parseInsertFileInfo(Board board, MultipartHttpServletRequest mpRequest)
			throws Exception {

		 Iterator<String> iterator = mpRequest.getFileNames();
		// 이터레이터 패턴을 사용해서 request에 전송된 모든 name 을 사용하겟다.
		// 멀티파트폼데이터를이용하여 가져온 type="file"의 파일을 가져와서 이터레이터에 넣어줌
		// why? map은 순차적으로 접근할수가없다.
		// 그렇기 때문에 이터레이터를 이용하여 map에있는 데이터를 while문을 이용하여 순차접근하기위해 이터레이터 사용
		// 업로드 된 파일들의 이름 목록을 제공하는 Iterator를 구한다.

		MultipartFile multipartFile     = null;
		String strOriginalFileName      = null; // 원래파일명
	    String strOriginalFileExtension = null; // 원래파일명 확장자
		String strStoredFileName        = null; // ex) ReName명 (랜덤파일명+확장자)

	    List<Map<String, Object>> list  = new ArrayList<Map<String, Object>>(); // 클라이언트에서 전송된 파일 정보를 담아서 반환해줄 list 객체
		Map<String, Object> listMap     = null; // listMap이라는 Map형 객체를 null로 만들어 준다.
		int nBno                        = board.getBno(); // 게시글업로드후 생성된 게시글번호를 가져옴
		
		File file = new File(filePath); // 파일경로에 파일이없다면 mkdir로 만들어준다.
		if (file.exists() == false) {
			file.mkdirs();
		}

		while (iterator.hasNext()) {
			// 이터레이터를 이용하여 map에 있는 데이터를 while문을 이용하여 순차적으로 접근
			// .hasNext()메소드 : 이터레이터의 다음값이 있는동안 반복해서 다른작업을 수행한다.

	   multipartFile                    = mpRequest.getFile(iterator.next());
			// 멀티파트파일 객체에 request에서 파일 객체를 가져오는 부분

		if (multipartFile.isEmpty() == false) { // 만약 multipartFile이 비어있다면 취소해라
				// 업로드한 파일이 존재하지 않는 경우 true를 리턴한다.
				// false이기때문에 업로드한파일이 존재할 경우이다.
				// 파일을 업로드시

	    strOriginalFileName             = multipartFile.getOriginalFilename();
				// 업로드한 파일의 확장자를 포함한 파일명이다.

		strOriginalFileExtension        = strOriginalFileName.substring(strOriginalFileName.lastIndexOf(".")); //원본파일명.확장자를 나눔 (원본파일명)(확장자)
		strStoredFileName               = getRandomString() + strOriginalFileExtension; // 원래 파일명 = 랜덤문자열+확장자명
		file                            = new File(filePath + strStoredFileName); // file(io)이라는 객체를 만들고 그 객체에는 파일경로+랜덤문자열+확장자를 붙인 파일을 저장한다.
				
		multipartFile.transferTo(file);
				// 실제파일을 저장하는 부분
				// 업로드한 파일을 특정파일로 저장하고 싶다면 MultipartFile.transferTo() 를 쓰면 편함

		listMap                         = new HashMap<String, Object>();
				//// 파일의 정보를 맵에 할당
		listMap.put("BNO", nBno); // 게시글번호 // 게시글 업로드후 생성된 게시글 번호
		listMap.put("FILE_ORINAME", strOriginalFileName); // "디비컬럼명"+원본파일명
		listMap.put("FILE_RENAME", strStoredFileName); // "디비컬럼명"+랜덤문자열+확장자를 붙인 파일명 // 저장할 파일 이름
		listMap.put("FILE_SIZE", multipartFile.getSize()); // "디비컬럼명"+파일의 크기를 넣어준다. 	// 저장할 파일 크기
		list.add(listMap); // 만든 정보들을 list에 담아줌
			}
		}
		return list;
	}

	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
		// UUID :128 비트로 이루어진 유니크 키
		// 고유값 생성
		// 랜덤 문자열을 만들어줌
	}

}