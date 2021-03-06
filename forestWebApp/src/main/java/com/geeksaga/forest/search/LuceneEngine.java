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

import com.geeksaga.forest.common.util.Logger;
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
    public static String LUCENE_INDEX_PATH = "lucene.index.path";
    
    private static LuceneEngine instance = new LuceneEngine();
    private static Analyzer analyzer = new KoreanAnalyzerWrapper();
    private final Object monitor = new Object();
    private static String bundleName;
    private static boolean isInitialize = false;
    private static String indexPath;

    private LuceneEngine()
    {}

    public static LuceneEngine getInstance()
    {
        return getInstance("application");
    }

    public static LuceneEngine getInstanceWithYaml(String indexPath)
    {
        if (!isInitialize)
        {
            LuceneEngine.indexPath = indexPath;

            instance.initialize();
        }

        return instance;
    }

    public static LuceneEngine getInstance(String bundleName)
    {
        LuceneEngine.bundleName = bundleName;

        if (!isInitialize)
        {
            indexPath = BundleUtils.getString(LuceneEngine.bundleName, LUCENE_INDEX_PATH);

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
                directory = FSDirectory.open(new File(indexPath));

                IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
                indexWriterConfig.setOpenMode(OpenMode.CREATE);
                
                indexWriter = new IndexWriter(directory, indexWriterConfig);
            }
            catch (Exception e)
            {
                Logger.info(e);
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
                    {
                        Logger.info(e);
                    }
                }
                
                if (directory != null)
                {
                    try
                    {
                        directory.close();
                    }
                    catch (Exception e)
                    {
                        Logger.info(e);
                    }
                }
            }

            LuceneEngine.isInitialize = true;
        }
    }

    public List<String> search(String queryString)
    {
        Directory directory;
        IndexSearcher indexSearcher;
        List<String> list = new ArrayList<>();

        try
        {
            directory = FSDirectory.open(new File(indexPath));

            IndexReader reader = DirectoryReader.open(directory);
            indexSearcher = new IndexSearcher(reader);

            QueryParser parser = new QueryParser("content", analyzer);
            parser.setDefaultOperator(Operator.AND);

            Query query = parser.parse(queryString);

            TopScoreDocCollector collector = TopScoreDocCollector.create(10, true);
            indexSearcher.search(query, collector);

            ScoreDoc[] scoreDocs = collector.topDocs().scoreDocs;

            for (ScoreDoc score : scoreDocs)
            {
                Document document = indexSearcher.doc(score.doc);

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
        Directory directory;
        IndexWriter indexWriter = null;

        synchronized (monitor)
        {
            try
            {
                directory = FSDirectory.open(new File(indexPath));

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
                    {
                        Logger.info(e);
                    }
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
        Directory directory;
        // IndexReader indexReader = null;
        IndexWriter indexWriter = null;

        synchronized (monitor)
        {
            try
            {
                directory = FSDirectory.open(new File(indexPath));
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
                    {
                        Logger.info(e);
                    }
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