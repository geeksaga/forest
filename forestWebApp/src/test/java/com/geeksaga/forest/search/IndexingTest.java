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
 *
 */
package com.geeksaga.forest.search;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.ko.KoreanAnalyzerWrapper;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;

import com.geeksaga.forest.common.util.BundleUtils;

public class IndexingTest
{
    private Directory directory;

    @Before
    public void setUp() throws Exception
    {
        directory = FSDirectory.open(new File(BundleUtils.getString("application", LuceneEngine.LUCENE_INDEX_PATH)));
    }

    private IndexWriter getWriter() throws IOException
    {
        return new IndexWriter(directory, new IndexWriterConfig(Version.LATEST, new KoreanAnalyzerWrapper()));
    }

    @Test
    public void testIndexWriter() throws IOException
    {

        IndexWriter writer = getWriter();

        String description = "학습에서 실전을 목표로 달려가는 프로젝트 입니다.";
        String publisher = "geeksaga";
        String title = "Forest 프로젝트 과연 완성이  될것인가?";

        Document doc = new Document();
        doc.add(new TextField("description", description, Field.Store.YES));
        doc.add(new StringField("publisher", publisher, Field.Store.YES));
        doc.add(new StringField("title", title, Field.Store.YES));

        writer.addDocument(doc);

        writer.close();
    }
}