package org.core.file.model.vo;

public class FileJun {

	private int fileNum; // 파일 번호 (PK)
	private int bno; // TB_BOARD테이블의 게시판 번호(FK)
	private String fileOriName; // 원래 파일 이름
	private String fileRename; // 변경 파일이름
	private int fileSize; // 파일 크기
	private String del_Gb; // 삭제여부

	public FileJun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileJun(int fileNum, int bno, String fileOriName, String fileRename, int fileSize, String del_Gb) {
		super();
	this.fileNum     = fileNum;
    this.bno         = bno;
    this.fileOriName = fileOriName;
    this.fileRename  = fileRename;
	this.fileSize    = fileSize;
	this.del_Gb      = del_Gb;
	}

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getFileOriName() {
		return fileOriName;
	}

	public void setFileOriName(String fileOriName) {
		this.fileOriName = fileOriName;
	}

	public String getFileRename() {
		return fileRename;
	}

	public void setFileRename(String fileRename) {
		this.fileRename = fileRename;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getDel_Gb() {
		return del_Gb;
	}

	public void setDel_Gb(String del_Gb) {
		this.del_Gb = del_Gb;
	}

}
