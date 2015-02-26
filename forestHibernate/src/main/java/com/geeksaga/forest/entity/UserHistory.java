/*
 * GeekSaga Class Infomation Library v0.0.1
 * 
 * http://geeksaga.com/
 * 
 * Copyright 2014 GeekSaga Foundation, Inc. and other contributors
 * 
 * Released under the MIT license http://geeksaga.com/license
 */

/**
 * @author geeksaga
 * @version 0.1
 */
package com.geeksaga.forest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.geeksaga.common.annotation.PrintToString;

@Entity
@Table(name = "pw_history", schema = "")
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