package com.geeksaga.forest.repositories.entity;

import java.io.Serializable;

import com.geeksaga.common.annotation.PrintToString;

/**
 * @author geeksaga
 * @version 0.1
 */
public class UserHistory extends BaseEntity implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@PrintToString private String userSid;
	@PrintToString private String writeCnt;
	@PrintToString private String bestCnt;
	@PrintToString private String replyCnt;
	@PrintToString private String memoCnt;
	@PrintToString private String memoReplyCnt;
	@PrintToString private String modifyTimestamp;

    public String getUserSid() {
		return userSid;
	}

	public void setUserSid(String userSid) {
		this.userSid = userSid;
	}

	public String getWriteCnt() {
		return writeCnt;
	}

	public void setWriteCnt(String writeCnt) {
		this.writeCnt = writeCnt;
	}

	public String getBestCnt() {
		return bestCnt;
	}

	public void setBestCnt(String bestCnt) {
		this.bestCnt = bestCnt;
	}

	public String getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(String replyCnt) {
		this.replyCnt = replyCnt;
	}

	public String getMemoCnt() {
		return memoCnt;
	}

	public void setMemoCnt(String memoCnt) {
		this.memoCnt = memoCnt;
	}

	public String getMemoReplyCnt() {
		return memoReplyCnt;
	}

	public void setMemoReplyCnt(String memoReplyCnt) {
		this.memoReplyCnt = memoReplyCnt;
	}

    public String getModifyTimestamp() {
        return modifyTimestamp;
    }

    public void setModifyTimestamp(String modifyTimestamp) {
        this.modifyTimestamp = modifyTimestamp;
    }
}