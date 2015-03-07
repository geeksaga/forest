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
 * http://www.java2s.com/Tutorials/Java/JPA/4330__JPA_Query_Exists.htm
 * 
 * @author geeksaga
 * @version 0.1
 */
package com.geeksaga.forest.service;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.geeksaga.common.util.HangleParser;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.entity.Tag;
import com.geeksaga.forest.entity.TagMap;
import com.geeksaga.forest.repositories.TagMapRepository;

@Service
public class TagMapService extends QueryDslRepositorySupport
{
    @Autowired
    private TagMapRepository tagMapRepository;

    @Autowired
    private TagService tagService;

    public TagMapService()
    {
        super(TagMap.class);
    }

    /**
     * Tag 를 파싱 한 후 등록 한다. 이미 등록된 Tag 인지 조회 하여 등록된 Tag 는 카운트만 증가 시키고 등록되어 있지 않으면 등록 시킨다.
     * 
     * 리턴 값은 등록한 태그의 첫 번째 태그 이다.
     * 
     * @param tags
     * @param type
     * @return
     * @throws DataAccessException
     */
    @Transactional
    public Tag save(Seed seed, Tag tags, TagMap.CNT_TYPE type)
    {
        return save(seed, tags, type, false);
    }

    /**
     * Tag 를 파싱 한 후 등록 한다. 이미 등록된 Tag 인지 조회 하여 등록된 Tag 는 카운트만 증가 시키고, 등록되어 있지 않으면 등록 시킨 후 대상에 대한 Tag 내용을 수정 한다.
     * 
     * 리턴 값은 등록한 태그의 첫 번째 Tag 이다.
     * 
     * @param tags
     * @param type
     * @param update
     * @return
     * @throws DataAccessException
     */
    public Tag save(Seed seed, Tag tags, TagMap.CNT_TYPE type, boolean update)
    {
        Tag resultTag = null;

        if (!StringUtils.isEmpty(tags.getTagName()))
        {
            StringTokenizer st = new StringTokenizer(tags.getTagName(), ",");
            int tagCnt = st.countTokens();

            boolean isFirst = true;
            while (st.hasMoreTokens())
            {
                String t = st.nextToken().trim();

                Tag tag = new Tag(t, HangleParser.parse(t));

                Tag targetTag = tagService.findByTagName(tag);

                if (targetTag == null)
                {
                    targetTag = tagService.save(tag);
                }

                TagMap tagMap = new TagMap(seed, targetTag);

                if (!tagMapRepository.exists(tagMap.getPk()))
                {
                    tagMapRepository.save(tagMap);

                    tagService.updateCnt(targetTag);
                }

                if (isFirst)
                {
                    tags.setSid(tag.getSid());
                    tags.setTagName(tag.getTagName());

                    resultTag = tag;

                    isFirst = false;
                }
            }

            tags.setTagCnt(tagCnt);

            if (update && tagCnt > 0)
            {
                // FIXME update count for target
                // tagMapDao.updateCnt(tags, type);
            }
        }

        return resultTag;
    }

    /**
     * 태그를 등록 한다.
     * 
     * @param tagMap
     * @return
     */
    public TagMap save(TagMap tagMap)
    {
        // tagMap.setRegistTimestamp(DateConvertor.getDateTimeFormat());

        tagMapRepository.save(tagMap);

        return tagMap;
    }

    public List<TagMap> save(Iterable<TagMap> list)
    {
        // Iterator<TagMap> iter = list.iterator();
        //
        // while (iter.hasNext())
        // {
        // TagMap tagMap = iter.next();
        // tagMap.setRegistTimestamp(DateConvertor.getDateTimeFormat());
        // }

        tagMapRepository.save(list);

        return (List<TagMap>) list;
    }

    /**
     * 이미 등록되어 있는 태그 인지 확인 한다.
     * 
     * @param tagMap
     * @return
     */
    public boolean exists(TagMap.PK tagMap)
    {
        return tagMapRepository.exists(tagMap);
    }

    // public Seed findBySid(Long sid)
    // {
    // return tagMapRepository.findOne(QSeed.seed.sid.eq(sid));
    // }
}
