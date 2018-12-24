package org.jwolfe.quetzal.library.trie;

import java.util.concurrent.atomic.AtomicInteger;

public class SuffixTree {

	public SuffixNode getRoot() {
		return root;
	}

	public void setRoot(SuffixNode root) {
		this.root = root;
	}
	
	SuffixNode root;
	
	// Active Points
	SuffixNode activeNode;
	int activeEdge;
	int activeLength;
	
	// Tree construction fields
	AtomicInteger endIndex;
	int remainingSuffixCount;
	SuffixNode lastNewNode;
	

	public void construct(String text) {
		// Ukkonen's Algorithm
		// Note the below concepts / tricks
		//		1) Phases & Extensions
		//		2) 3 Extension Rules
		//		3) Skip Count
		//		4) Edge Label Compression
		//		5) Rule 3 is a show-stopper 
		//		6) Global End for leaves
		//		7) Suffix Links
		//		8) Active Points
				
		root = new SuffixNode(-1, -1);
		
		activeNode = root;
		activeEdge = -1;
		activeLength = 0;
		endIndex = new AtomicInteger(-1);
		remainingSuffixCount = 0;
		
		for (int i = 0; i < text.length(); i++) {
			extendTree(text, i);
		}
	}
	
	private void extendTree(String text, int index) {
		// Set the global end-index. (Extension Rule 1).
		// This will ensure automatic setting of end-index for leaf nodes created till now.
		endIndex.set(index);
		
		// Register a new suffix to the already in-queue list of suffixes.
		remainingSuffixCount++;
		
		// Start of phase - reset last new node to null. 
		lastNewNode = null;
		
		while(remainingSuffixCount > 0) {
			if(activeLength == 0) {
				// Scenario - APCFALZ
				// Set active edge to the character being processed
				activeEdge = index;
			}
			
			if(activeNode.children[text.charAt(activeEdge)] == null) {
				// No edge corresponding to the active edge in active node. Create. (Extension Rule 2.)
				activeNode.children[text.charAt(activeEdge)] = new SuffixNode(index, endIndex, root);
				
				if(lastNewNode != null) {
					lastNewNode.setSuffixlink(activeNode);
					lastNewNode = null;
				}
			}
			else {
				// Outgoing active edge exists from active node
				var nextNode = activeNode.children[text.charAt(activeEdge)];
				if(walkDown(nextNode, text, index)) {
					// Loop from start with the new active node.
					continue;
				}
				
				
				if(text.charAt(index) == text.charAt(nextNode.getStartIndex() + activeLength)) {
					// Current character already exists on the edge. (Extension Rule 3.)
					// Set suffix link for last new node if appropriate
					if(activeNode != root && lastNewNode != null) {
						lastNewNode.setSuffixlink(activeNode);
						lastNewNode = null;
					}
					
					// Scenario - APCFER3
					activeLength++;
					break;
				}
				
				// Active point is in the middle of the edge being traversed. (Extension Rule 2.)
				// Add a new internal node, with a new leaf
				int splitStart = nextNode.getStartIndex();
				int splitEnd = nextNode.getStartIndex() + activeLength - 1;
				SuffixNode splitNode = new SuffixNode(splitStart, splitEnd, root);
				activeNode.children[text.charAt(activeEdge)] = splitNode;
				
				SuffixNode leafNode = new SuffixNode(index, endIndex);
				splitNode.children[text.charAt(index)] = leafNode;
				
				nextNode.setStartIndex(nextNode.getStartIndex() + activeLength);
				splitNode.children[text.charAt(nextNode.getStartIndex())] = nextNode;
				
				// If there is any internal node created in the previous extensions, set its suffix link
				if(lastNewNode != null) {
					lastNewNode.setSuffixlink(splitNode);
				}
				
				lastNewNode = splitNode;
			}
			
			remainingSuffixCount--;
			if(activeNode == root && activeLength > 0) {
				// APCFER2C1
				activeLength--;
				activeEdge = index - remainingSuffixCount + 1;
			}
			else if(activeNode != root) {
				// APCFER2C2
				activeNode = activeNode.suffixlink;
			}
		}		
	}
	
	private boolean walkDown(SuffixNode nextNode, String text, int index) {
		int edgeLength = nextNode.getLength();
		if(edgeLength < activeLength) {
			activeNode = nextNode;
			activeEdge += edgeLength;
			activeLength -= edgeLength;
			
			return true;
		} 
		
		return false;		
	}
}
