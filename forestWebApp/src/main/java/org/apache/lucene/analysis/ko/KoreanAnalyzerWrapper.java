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
package org.apache.lucene.analysis.ko;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.ko.HanjaMappingFilter;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.analysis.ko.KoreanFilter;
import org.apache.lucene.analysis.ko.KoreanTokenizer;
import org.apache.lucene.analysis.ko.PunctuationDelimitFilter;
import org.apache.lucene.analysis.ko.WordSegmentFilter;
import org.apache.lucene.analysis.standard.ClassicFilter;

public class KoreanAnalyzerWrapper extends KoreanAnalyzer
{
    private boolean bigrammable = false;
    private boolean hasOrigin = false;
    private boolean exactMatch = false;
    private boolean originCNoun = true;
    private boolean queryMode = false;
    private boolean wordSegment = false;

    public KoreanAnalyzerWrapper()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.apache.lucene.analysis.ko.KoreanAnalyzer#createComponents(java.lang.String, java.io.Reader)
     */
    @Override
    protected TokenStreamComponents createComponents(final String fieldName, final Reader reader)
    {
        final KoreanTokenizer src = new KoreanTokenizer(reader);
        TokenStream tok = new LowerCaseFilter(src);
        tok = new ClassicFilter(tok);
        tok = new KoreanFilter(tok, bigrammable, hasOrigin, exactMatch, originCNoun, queryMode);

        if (wordSegment)
        {
            tok = new WordSegmentFilter(tok, hasOrigin);
        }

        tok = new HanjaMappingFilter(tok);
        tok = new PunctuationDelimitFilter(tok);
        tok = new StopFilter(tok, stopwords);

        return new TokenStreamComponents(src, tok)
        {
            @Override
            protected void setReader(final Reader reader) throws IOException
            {
                super.setReader(reader);
            }
        };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.ko.KoreanAnalyzer#setBigrammable(boolean)
     */
    public void setBigrammable(boolean is)
    {
        bigrammable = is;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.ko.KoreanAnalyzer#setHasOrigin(boolean)
     */
    public void setHasOrigin(boolean has)
    {
        hasOrigin = has;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.ko.KoreanAnalyzer#setOriginCNoun(boolean)
     */
    public void setOriginCNoun(boolean cnoun)
    {
        originCNoun = cnoun;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.ko.KoreanAnalyzer#setExactMatch(boolean)
     */
    public void setExactMatch(boolean exact)
    {
        exactMatch = exact;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.ko.KoreanAnalyzer#setQueryMode(boolean)
     */
    public void setQueryMode(boolean mode)
    {
        queryMode = mode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.ko.KoreanAnalyzer#isWordSegment()
     */
    public boolean isWordSegment()
    {
        return wordSegment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.ko.KoreanAnalyzer#setWordSegment(boolean)
     */
    public void setWordSegment(boolean wordSegment)
    {
        this.wordSegment = wordSegment;
    }
}