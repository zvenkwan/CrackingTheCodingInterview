package chapter4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * 4.1
	 * Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
	 */
	public boolean isConnected(Graph g, Node start, Node end) {
		if(start == end) return true;
		LinkedList<Node> q = new LinkedList<Node>();
		for(Node n:g.getNodes()){
			n.visited = false;
		}
		q.add(start);
		Node n;
		while(!q.isEmpty()) {
			n = q.removeFirst();
			if(n !=null) {
				for(Node m:n.getAdjacent()) {
					if(m == end)
						return true;
					else
						q.add(m);
				}
			}
			n.visited = true;
		}
		return false;
	}
	
	/*
	 * 4.2
	 * Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height.
	 */
	public TreeNode buildBinary(int sorted[]) {
		return buildBinary(sorted, 0, sorted.length-1);
	}

	private TreeNode buildBinary(int[] sorted, int s, int e) {
		if(s > e) return null;
		int m = (e+s)/2;
		TreeNode n = new TreeNode(sorted[m]);
		n.left = buildBinary(sorted, s, m-1);
		n.right = buildBinary(sorted, m+1, e);
		return n;
	}
	
	/*
	 * 4.3
	 * List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth
	 * (e.g., if you have a tree with depth D, you'll have D linked lists).
	 */
	public ArrayList<LinkedList<TreeNode>> getList(TreeNode root) {
		if(root == null) return null;
		ArrayList<LinkedList<TreeNode>> arr = new ArrayList<LinkedList<TreeNode>>();
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()) {
			int len = q.size();
			LinkedList<TreeNode> level = new LinkedList<TreeNode>();
			for(int i=0; i<len; i++) {
				if(q.peek().left !=null)
					q.add(q.peek().left);
				if(q.peek().right !=null)
					q.add(q.peek().right);
				level.add(q.pop());
			}
			arr.add(level);
		}
		return arr;
	}
	/*
	 * 4.4
	 * Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of this question, a balanced tree is defined to be a tree
	 * such that the heights of the two subtrees of any node never differ by more than one.
	 */
	public boolean isBalanced(TreeNode root) {
		return checkHeight(root) != Integer.MIN_VALUE;
	}

	private int checkHeight(TreeNode root) {
		if(root == null) return -1;
		int left = checkHeight(root.left);
		if(left == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		int right = checkHeight(root.right);
		if(right == Integer.MIN_VALUE) return Integer.MIN_VALUE;
		if(Math.abs(left - right) > 1)
			return Integer.MIN_VALUE;
		else return Math.max(left, right) + 1;
	}
	
	/*
	 * 4.5
	 * Validate BST: Implement a function to check if a binary tree is a binary search tree.
	 */
	public boolean isBST(TreeNode root) {
		if(root == null) return true;
		return checkScope(root.left, Integer.MIN_VALUE, root.value) && checkScope(root.left, root.value, Integer.MAX_VALUE);
	}

	private boolean checkScope(TreeNode root, int min, int max) {
		if(root == null) return true;
		if(root.value >= min && root.value < max) {
			return checkScope(root.left, min, root.value) && checkScope(root.right, root.value, max);
		}
		else return false;
	}
	
	/*
	 * 4.6
	 * Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a binary search tree. You may assume that
	 * each node has a link to its parent.
	 */
	public TreeNode next(TreeNode n) {
		if(n == null) return null;
		TreeNode res = null;
		if(n.right != null) {
			res = n.right;
			while(res!=null) res = res.left;
			return res;
		} else {
			if(n.parent == null) return null;
			else {
				res = n.parent;
				while(res.value < n.value) {
					res = res.parent;
				}
				return res;
			}
		}
	}
	
	/*
	 * 4.7
	 * Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of projects where 
	 * the second project is dependent on the first project). All of a project's dependencies must be built before the project is. Find a build order that
	 * will allow the projects to be built. If there is no valid build order, return an error.
	 */
//	see Project.java topological sort
	
	/*
	 * 4.8
	 * First Common Ancestor: Design an algorithm and write code to find the first common ancestor of two nodes in a binary tree. Avoid storing additional
	 * nodes in a data structure. NOTE: This is not necessarily a binary search tree.
	 */
	
	/*
	 * 4.9
	 * BST Sequences: A binary search tree was created by traversing through an array from left to right and inserting each element. Given a binary search tree
	 * with distinct elements, print all possible arrays that could have led to this tree.
	 */
	public ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
		ArrayList<LinkedList<Integer>> results = new ArrayList<LinkedList<Integer>>();
		if(node == null) {
			results.add(new LinkedList<Integer>());
			return results;
		}
		
		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.value);
		
		ArrayList<LinkedList<Integer>> left = allSequences(node.left);
		ArrayList<LinkedList<Integer>> right = allSequences(node.right);
		
		for(LinkedList<Integer> l : left)
			for(LinkedList<Integer> r : right) 
				weaveLists(l, r, results, prefix);
		return results;
	}

	private void weaveLists(LinkedList<Integer> l, LinkedList<Integer> r,
			ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
		if(l.size()==0 || r.size()==0) {
			LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
			result.addAll(l);
			result.addAll(r);
			results.add(result);
			return;
		}
		
		int head = l.removeFirst();
		prefix.add(head);
		weaveLists(l,r,results, prefix);
		prefix.removeLast();
		l.addFirst(head);
		
		head = r.removeFirst();
		prefix.add(head);
		weaveLists(l,r,results, prefix);
		prefix.removeLast();
		r.addFirst(head);
	}
	
	/*
	 * 4.10
	 * Check Subtree: T1 and T2 are two very large binary trees, with T1 much bigger than T2. Create an algorithm to determine if T2 is a subtree of T1.
	 * A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical to T2. That is, if you cut off the tree at
	 * node n, the two trees would be identical.
	 */
	public boolean isSubTree(TreeNode t1, TreeNode t2) {
		if(t1 == null) return false;
		if(t2 == null) return true;
		if(t1.value != t2.value) 
			return isSubTree(t1.left, t2) || isSubTree(t1.right, t2);
		return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
	}

	private boolean matchTree(TreeNode t1, TreeNode t2) {
		if(t1 == null && t2 == null) return true;
		if(t1.value == t2.value)
			return matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right);
		return false;
	}
	
	/*4.11
	 * Random Node: You are implementing a binary search tree class from scratch, which,in addition to insert, find, and delete, has a method getRandomNode()
	 * which returns a random node from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm for getRandomNode, and
	 * explain how you would implement the rest of the methods.
	 */
//	assume the size of each node is known
	
	/*
	 * 4.12
	 * Paths with sum: You are given a binary tree in which each node contains an interger value (which might be positive or negative). Design an algorithm to 
	 * count the number of paths that sum to a given value. The path does not need to start or end at the root or a leaf, but it must go downwards
	 * (traveling only from parent nodes to child nodes)
	 */
	public int countPaths(TreeNode root, int targetSum) {
		return countPaths(root, targetSum, 0, new HashMap<Integer, Integer>());
	}

	private int countPaths(TreeNode node, int targetSum, int runningSum, HashMap<Integer, Integer> sumSet) {
		if(node == null) return 0;
		runningSum += node.value;
		int val = runningSum - targetSum;
		int totalPaths = sumSet.getOrDefault(val, 0);
		if(runningSum == targetSum) totalPaths++;
		
		updateSumSet(sumSet, runningSum, 1);
		totalPaths += countPaths(node.left, targetSum, runningSum, sumSet);
		totalPaths += countPaths(node.right, targetSum, runningSum, sumSet);
		updateSumSet(sumSet, runningSum, -1);
		return totalPaths;
	}

	private void updateSumSet(HashMap<Integer, Integer> sumSet, int key, int delta) {
		int newCount = sumSet.getOrDefault(key, 0) + delta;
		if(newCount == 0) sumSet.remove(key);
		else sumSet.put(key, newCount);
	}
	
	
}
