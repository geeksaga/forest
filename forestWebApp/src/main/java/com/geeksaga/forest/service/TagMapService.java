package com.geeksaga.forest.service;

import org.springframework.dao.DataAccessException;

import com.geeksaga.forest.repositories.entity.Tag;
import com.geeksaga.forest.repositories.entity.TagMap;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface TagMapService
{
    /**
     * Tag �� �Ľ� �� �� ��� �Ѵ�.
     * �̹� ��ϵ� Tag ���� ��ȸ �Ͽ� ��ϵ� Tag �� ī��Ʈ�� ���� ��Ű�� ��ϵǾ� ���� ������ ��� ��Ų��.
     * 
     * ���� ���� ����� �±��� ù ��° �±� �̴�.
     * 
     * @param tags
     * @param type
     * @return
     * @throws DataAccessException
     */
    Tag add(Tag tags, TagMap.CNT_TYPE type);
        
    /**
     * Tag �� �Ľ� �� �� ��� �Ѵ�.
     * �̹� ��ϵ� Tag ���� ��ȸ �Ͽ� ��ϵ� Tag �� ī��Ʈ�� ���� ��Ű��,
     * ��ϵǾ� ���� ������ ��� ��Ų �� ��� ���� Tag ������ ���� �Ѵ�.
     * 
     * ���� ���� ����� �±��� ù ��° Tag �̴�.
     * 
     * @param tags
     * @param type
     * @param update
     * @return
     * @throws DataAccessException
     */
    Tag add(Tag tags, TagMap.CNT_TYPE type, boolean update);
}