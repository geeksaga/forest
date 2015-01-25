package com.geeksaga.forest.service.impl;

import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.geeksaga.common.util.HangleParser;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.dao.TagDao;
import com.geeksaga.forest.dao.TagMapDao;
import com.geeksaga.forest.repositories.entity.Tag;
import com.geeksaga.forest.repositories.entity.TagMap;
import com.geeksaga.forest.service.TagMapService;

/**
 * @author geeksaga
 * @version 0.1
 */
@Service
@Transactional
public class TagMapServiceImpl implements TagMapService
{
    @Autowired
    private TagMapDao tagMapDao;
    @Autowired
    private TagDao tagDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.service.TagMapService#add(com.geeksaga.forest.repositories.entity.Tag,
     * com.geeksaga.forest.repositories.entity.TagMap.CNT_TYPE)
     */
    public Tag add(Tag tags, TagMap.CNT_TYPE type)
    {
        return add(tags, type, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.service.TagMapService#add(com.geeksaga.forest.repositories.entity.Tag,
     * com.geeksaga.forest.repositories.entity.TagMap.CNT_TYPE, boolean)
     */
    public Tag add(Tag tags, TagMap.CNT_TYPE type, boolean update)
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
                TagMap tagMap = new TagMap(tags.getTargetSid());

                Tag findTag = tagDao.findByTagName(tag);

                if (findTag == null)
                {
                    Long tagSid = KeyGenerator.generateKeyToLong();
                    tag.setSid(tagSid);
                    tagMap.getPk().setTagSid(tagSid);

                    tagDao.add(tag);
                }
                else
                {
                    tagMap.getPk().setTagSid(findTag.getSid());
                    tag.setSid(findTag.getSid());

                    tagDao.updateCnt(tag);
                }

                if (!tagMapDao.exists(tagMap))
                {
                    tagMapDao.save(tagMap);
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
                //tagMapDao.updateCnt(tags, type);
            }
        }

        return resultTag;
    }
}