package com.spzh.form;

import java.util.List;

/**
 * 录像文件form
 * @author hyq
 *
 */
public class RecordForm {
	private int cmsserver;
	private List<RecordFileForm> files;
	private int result; //结果 {"cmsserver":1,"result":23} 汽车不在线

	public RecordForm(int result) {
		super();
		this.cmsserver = 1;
		this.result = result;
	}

	public RecordForm() {
		super();
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getCmsserver() {
		return cmsserver;
	}

	public void setCmsserver(int cmsserver) {
		this.cmsserver = cmsserver;
	}

	
	public List<RecordFileForm> getFiles() {
		return files;
	}

	public void setFiles(List<RecordFileForm> files) {
		this.files = files;
	}

}
