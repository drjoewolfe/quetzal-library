package org.jwolfe.quetzal.library.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SuffixNode {
	
	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	
	Integer startIndex;

	public AtomicInteger getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(AtomicInteger endIndex) {
		this.endIndex = endIndex;
	}
	
	AtomicInteger endIndex;

	public SuffixNode[] getChildren() {
		return children;
	}
	
	SuffixNode[] children;
	

	public int getSuffixIndex() {
		return suffixIndex;
	}

	public void setSuffixIndex(int suffixIndex) {
		this.suffixIndex = suffixIndex;
	}
	
	int suffixIndex;
	
	public SuffixNode getSuffixlink() {
		return suffixlink;
	}

	public void setSuffixlink(SuffixNode suffixlink) {
		this.suffixlink = suffixlink;
	}
		
	SuffixNode suffixlink;


	public SuffixNode() {
		this.children = new SuffixNode[256];
		this.suffixIndex = -1;
	}

	public SuffixNode(int startIndex, int endIndex) {
		this();
		
		this.startIndex = startIndex;
		this.endIndex = new AtomicInteger(endIndex);
	}	
	
	public SuffixNode(int startIndex, AtomicInteger endIndex) {
		this();
		
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}	
	
	public SuffixNode(int startIndex, int endIndex, SuffixNode suffixLink) {
		this(startIndex, endIndex);
		
		this.suffixlink = suffixLink;
	}	
	
	public SuffixNode(int startIndex, AtomicInteger endIndex, SuffixNode suffixLink) {
		this(startIndex, endIndex);
		
		this.suffixlink = suffixLink;
	}
	
	public int getLength() {
		return endIndex.intValue() - startIndex + 1;
				
	}
}
