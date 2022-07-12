package com.bitc.gotrip.common;

import com.bitc.gotrip.dto.FileDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {
    public List<FileDto> parseFileInfo(int tableNum, int pkNum, MultipartHttpServletRequest multiUploadFiles) throws Exception {
//      ObjectUtils 는 org.springframework.util.ObjectUtils 를 사용
//      isEmpty() 를 사용하여 MultipartHttpServletRequest 를 통해서 업로드된 데이터에 파일 데이터가 존재하는지 확인
//      파일 데이터가 없을 경우 null 을 반환하고 메서드 종료
        if(ObjectUtils.isEmpty(multiUploadFiles)) {
            return null;
        }

//      반환 타입에 맞는 데이터객체를 생성(=DB에 저장될 내용을 담을 List 타입의 변수)
        List<FileDto> fileList = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");// 현재 시간을 기준으로 새로운 파일명 생성
        ZonedDateTime current = ZonedDateTime.now();                       // 현재 지역을 기준으로하여 현재 시간을 가져옴
        String path = "images/" + current.format(format);                  // 실제 파일이 저장될 폴더명
        File file = new File(path);                                        //

//      Java.io 의 File 클래스 타입의 객체를 통해 지정한 위치에 동일한 이름의 폴더가 존재하는지 확인
        if (file.exists() == false) {
            file.mkdir();            // 폴더가 없을 경우 생성
        }

//      클라이언트에서 전달된 파일 데이터에서 전체 파일 이름 목록을 가져옴
        Iterator<String> iterator = multiUploadFiles.getFileNames();

//      각각의 파일 정보가 저장될 변수
        String newFileName;          // 새로 생성된 파일 이름을 저장할 변수
        String originalFileExtension;// 파일 확자자명을 저장할 변수
        String contentType;          // 실제 가지고 있는 미디어 타입을 저장할 변수

        while (iterator.hasNext()) {
//          지정한 파일명을 가지고 있는 데이터 그룹을 모두 가져옴
            List<MultipartFile> list = multiUploadFiles.getFiles(iterator.next());

            for(MultipartFile multipartFile : list) {
                if (multipartFile.isEmpty() == false) {
                    contentType = multipartFile.getContentType(); // 현재 파일 정보의 컨텐츠타입정보를 가져옴

                    if (ObjectUtils.isEmpty(contentType)) {       // 한 번 더 ObjectUtils 로 컨텐츠타입정보 비었는지 확인
                        break;
                    }
//                  이미지 파일일경우 아래의 제어문 실행(확장자명 설정)
                    else  {
                        if (contentType.contains("image/jpeg")) {
                            originalFileExtension = ".jpg";
                        }
                        else if (contentType.contains("image/png")) {
                            originalFileExtension = ".png";
                        }
                        else if (contentType.contains("image/gif")) {
                            originalFileExtension = ".gif";
                        }
                        else {
                            break;
                        }
                    }

//                  현재 시간을 기준으로 위에서 생성한 확장자와 합하여 고유한 파일명을 생성함.
                    newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

//                  DB 에 저장할 수 있는 BoardFileDto 클래스 타입의 객체를 생성하고 파일정보를 저장함
                    FileDto boardFile = new FileDto();
                    UUID uuid = UUID.randomUUID();   // uuid 생성
                    boardFile.setTableNum(tableNum); // 현재 게시판 번호 저장
                    boardFile.setPkNum(pkNum);// 현재 게시물 글번호 저장
                    boardFile.setFileSize(multipartFile.getSize()); // 파일의 크기 저장
                    boardFile.setOriginalFileName(multipartFile.getOriginalFilename()); // 원본 파일의 파일명 저장
                    boardFile.setFileUuid(uuid.toString());                             // uuid 설정
                    boardFile.setStoredFilePath(path + "/" + newFileName);              // 서버에 저장될 경로
                    // ㄴ> 위에서 생성한 폴더의 경로와 System.nanoTime 을 사용하여 생성한 고유한 파일명을 합하여 실제 파일이 저장되는 전체 경로를 저장

//                  위에서 미리 생성한 반환 타입의 변수에 데이터 저장
                    fileList.add(boardFile);

//                  File 클래스 타입의 객체 file 에 서버에 저장될 파일 전체 경로를 적용
                    file = new File( path + "/" + newFileName);
                    multipartFile.transferTo(file); // =지정한 위치(=file)로 파일을 복사. 실제로 서버에 저장됨
                }
            }
        }
//      위에서 생성된 모든 파일에 대한 정보를 BoardFileDto 클래스 타입의 List 객체인 fileList 를 반환
        return fileList;
    }
}
