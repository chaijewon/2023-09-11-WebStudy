package com.sist.manager;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

import com.sist.vo.WordVO;
import java.util.*;
public class WordManager {
  public List<WordVO> wordCountData(String msg)
  {
	  List<WordVO> wList=new ArrayList<WordVO>();
	  KeywordExtractor ke=new KeywordExtractor();
	  KeywordList list=ke.extractKeyword(msg, true);
	  for(int i=0;i<list.size();i++)
	  {
		  WordVO vo=new WordVO();
		  Keyword kw=list.get(i);
		  if(kw.getString().length()>1)
		  {
		    vo.setWord(kw.getString());
		    vo.setCount(kw.getCnt());
		  
		    wList.add(vo);
		  }
	  }
	  return wList;
  }
}
