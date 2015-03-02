package com.geeksaga.forest.search;

import java.io.IOException;

import org.apache.lucene.analysis.ko.KoreanAnalyzerWrapper;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.geeksaga.forest.common.util.Logger;

public class QueryParserTest
{
    private Directory index = new RAMDirectory();

    @Test
    public void testQuery() throws Exception
    {
        String querystr = "만성";

        index();
        
        Logger.info("===search");
        
        KoreanAnalyzerWrapper analyzer = new KoreanAnalyzerWrapper();
        analyzer.setOriginCNoun(false);
        analyzer.setHasOrigin(false);

        QueryParser qp = new QueryParser("title", analyzer);
        qp.setDefaultOperator(Operator.AND);
        Query query = qp.parse(querystr);

        Logger.info(query.toString());

        int hitsPerPage = 10;

        //IndexReader reader = IndexReader.open(index);
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);

        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
        searcher.search(query, collector);

        ScoreDoc[] hits = collector.topDocs().scoreDocs;

        Logger.info("Found " + hits.length + " hits.");
        
        for (int i = 0; i < hits.length; i++)
        {
            int docid = hits[i].doc;
            Document d = searcher.doc(docid);

            Logger.info(d.get("title"));
        }
    }

    private void index() throws IOException
    {
        String[] idxstrs = new String[] { "만성간염", "A형간염", "만성비염" };

        KoreanAnalyzerWrapper analyzer = new KoreanAnalyzerWrapper();
        analyzer.setOriginCNoun(true);
        analyzer.setHasOrigin(true);

        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);

        IndexWriter writer = new IndexWriter(index, config);

        for (int i = 0; i < idxstrs.length; i++)
        {
            Document doc = new Document();
            doc.add(new TextField("title", idxstrs[i], Field.Store.YES));
            writer.addDocument(doc);
        }

        writer.close();
    }
}