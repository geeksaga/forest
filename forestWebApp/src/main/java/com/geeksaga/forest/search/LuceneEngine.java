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
package com.geeksaga.forest.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ko.KoreanAnalyzerWrapper;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.geeksaga.forest.common.util.BundleUtils;

public class LuceneEngine
{
    private static String LUCENE_INDEX_PATH = "lucene.index.path";
    
    private static LuceneEngine instance = new LuceneEngine();
    private static Analyzer analyzer = new KoreanAnalyzerWrapper();
    private Object monitor = new Object();
    private static String bundleName;
    private static boolean isInitialize = false;

    private LuceneEngine()
    {}

    public static LuceneEngine getInstance()
    {
        return getInstance("application");
    }
    
    public static LuceneEngine getInstance(String bundleName)
    {
        LuceneEngine.bundleName = bundleName;

        if (!isInitialize)
        {
            instance.initialize();
        }

        return instance;
    }

    private void initialize()
    {
        Directory directory = null;
        IndexWriter indexWriter = null;

        synchronized (monitor)
        {
            try
            {
                directory = FSDirectory.open(new File(BundleUtils.getString(LuceneEngine.bundleName, LUCENE_INDEX_PATH)));

                IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
                indexWriterConfig.setOpenMode(OpenMode.CREATE);
                
                indexWriter = new IndexWriter(directory, indexWriterConfig);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (indexWriter != null)
                {
                    try
                    {
                        indexWriter.close();
                    }
                    catch (Exception e)
                    {}
                }
                
                if (directory != null)
                {
                    try
                    {
                        directory.close();
                    }
                    catch (Exception e)
                    {}
                }
            }

            LuceneEngine.isInitialize = true;
        }
    }

    public List<String> search(String queryString)
    {
        Directory directory = null;
        IndexSearcher indexSearcher = null;
        List<String> list = new ArrayList<String>();

        try
        {
            directory = FSDirectory.open(new File(BundleUtils.getString(LuceneEngine.bundleName, LUCENE_INDEX_PATH)));

            IndexReader reader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(reader);

            QueryParser parser = new QueryParser("content", analyzer);
            parser.setDefaultOperator(Operator.AND);

            Query query = parser.parse(queryString);

            TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
            indexSearcher.search(query, collector);

            ScoreDoc[] hits = collector.topDocs().scoreDocs;

            for (int i = 0, j = hits.length; i < j; i++)
            {
                Document document = indexSearcher.doc(hits[i].doc);

                if (!list.contains(document.get("key")))
                {
                    list.add(document.get("key"));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       
        return list;
    }

    public void addDocument(String content, Long key)
    {
        addDocument(content, String.valueOf(key));
    }

    public void addDocument(String content, String key)
    {
        Directory directory = null;
        IndexWriter indexWriter = null;

        synchronized (monitor)
        {
            try
            {
                directory = FSDirectory.open(new File(BundleUtils.getString(LuceneEngine.bundleName, LUCENE_INDEX_PATH)));

                IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
                indexWriter = new IndexWriter(directory, indexWriterConfig);

                Document document = new Document();
                document.add(new StringField("key", key, Field.Store.YES));
                document.add(new TextField("content", content, Field.Store.NO));

                indexWriter.addDocument(document);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (indexWriter != null)
                {
                    try
                    {
                        indexWriter.close();
                    }
                    catch (Exception e)
                    {}
                }
            }
        }
    }

    public void deleteDocument(Long key)
    {
        deleteDocument(String.valueOf(key));
    }

    public void deleteDocument(String key)
    {
        Directory directory = null;
        // IndexReader indexReader = null;
        IndexWriter indexWriter = null;

        synchronized (monitor)
        {
            try
            {
                directory = FSDirectory.open(new File(BundleUtils.getString(LuceneEngine.bundleName, LUCENE_INDEX_PATH)));
                // indexReader = IndexReader.open(directory, false);
                // indexReader = IndexReader.open(directory);
                // indexReader.deleteDocuments(new Term("key", key));

                IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
                indexWriter = new IndexWriter(directory, indexWriterConfig);
                indexWriter.deleteDocuments(new Term("key", key));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (indexWriter != null)
                {
                    try
                    {
                        indexWriter.close();
                    }
                    catch (Exception e)
                    {}
                }
            }
        }
    }

    public void update(String content, Long key)
    {
        update(content, String.valueOf(key));
    }

    public void update(String content, String key)
    {
        deleteDocument(key);
        addDocument(content, key);
    }
}