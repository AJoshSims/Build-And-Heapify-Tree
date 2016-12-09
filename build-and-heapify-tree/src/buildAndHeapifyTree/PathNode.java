package buildAndHeapifyTree;

import java.util.ArrayList;

import utilities.CommonConstants;

/**
 * Describes the path from a vertex to another vertex.
 * 
 * @author Joshua Sims
 * @version 09 December 2016
 */
final class PathNode
{
	/**
	 * Describes the path from a vertex to another vertex.
	 */
	private ArrayList<Integer> path;
	
	/**
	 * The length of the path from the source vertex to the destination vertex.
	 */
	private int pathLength = -1;
	
	/**
	 * The left child of this node in the tree.
	 */
	private PathNode left;

	/**
	 * The right child of this node in the tree.
	 */
	private PathNode right;
	
	/**
	 * The parent of this node in the tree.
	 */
	private PathNode parent;
	
	/**
	 * The sibling to the left of this node, along the same level in the tree.
	 */
	private PathNode sibling;
	
	/**
	 * Indicates whether or not this node is the leftmost node in its level
	 * in the tree.
	 */
	private Boolean isLevelEnd;
	
	/**
	 * Indicates whether or not this node is the rightmost node in the 
	 * lowest level of the tree.
	 */
	private Boolean isLastNode;
	
	/**
	 * Initializes all fields to null.
	 */
	PathNode()
	{
		path = new ArrayList<Integer>();
		left = null;
		right = null;
		parent = null;
		sibling = null;
		isLevelEnd = null;
		isLastNode = null;
	}
	
	/**
	 * Returns the path that this node represents.
	 * 
	 * @return the path from a vertex to another vertex
	 */
	ArrayList<Integer> getPath()
	{
		return path;
	}
	
	/**
	 * Adds a vertex to the path represented by this node.
	 * 
	 * @param vertex - the vertex to be added to the path represented by this
	 *     node
	 */
	void addToPath(Integer vertex)
	{
		path.add(vertex);
		
		++pathLength;
	}
	
	/**
	 * Returns the length of the path that this node represents.
	 * 
	 * @return the length of the path from the source vertex to the 
	 *     destination vertex
	 */
	int getPathLength()
	{
		return pathLength;
	}

	/**
	 * Returns the left child of this node.
	 * 
	 * @return the left child of this node
	 */
	PathNode getLeft()
	{
		return left;
	}

	/**
	 * Sets the left child of this node.
	 * 
	 * @param the new left child of this node
	 */
	void setLeft(PathNode left)
	{
		this.left = left;
	}

	/**
	 * Returns the right child of this node.
	 * 
	 * @return the right child of this node
	 */
	PathNode getRight()
	{
		return right;
	}
	
	/**
	 * Sets the right child of this node.
	 * 
	 * @param the new right child of this node
	 */
	void setRight(PathNode right)
	{
		this.right = right;
	}
	
	/**
	 * Returns the parent of this node.
	 * 
	 * @return the parent of this node
	 */
	PathNode getParent()
	{
		return parent;
	}
	
	/**
	 * Sets the parent of this node.
	 * 
	 * @param parent - the new parent of this node
	 */
	void setParent(PathNode parent)
	{
		this.parent = parent;
	}

	/**
	 * Returns the sibling of this node.
	 * 
	 * @return the sibling of this node
	 */
	PathNode getSibling()
	{
		return sibling;
	}

	/**
	 * Sets the sibling of this node.
	 * 
	 * @param sibling - the new sibling of this node
	 */
	void setSibling(PathNode sibling)
	{
		this.sibling = sibling;
	}
	
	/**
	 * Returns a boolean indicating whether or not this node is at the end of 
	 * its level in the tree.
	 * 
	 * @return true if this node is at the end of its level in the tree or
	 *     false otherwise
	 */
	private Boolean isLevelEnd()
	{
		return isLevelEnd;
	}
	
	/**
	 * Sets the boolean indicating whether or not this node is at the end of 
	 * its level in the tree.
	 * 
	 * @param isLevelEnd - true if this node is at the end of its level in the 
	 *     tree or false otherwise
	 */
	void setIsLevelEnd(Boolean isLevelEnd)
	{
		this.isLevelEnd = isLevelEnd;
	}
	
	/**
	 * Returns a boolean indicating whether or not this node is at the end of
	 * the tree.
	 * 
	 * @return true if this node is at the end of the tree or false otherwise
	 */
	private Boolean isLastNode()
	{
		return isLastNode;
	}

	/**
	 * Sets the boolean indicating whether or not this node is at the end of
	 * the tree.
	 * 
	 * @param isLastNode - true if this node is at the end of the tree or 
	 *     false otherwise
	 */
	private void setIsLastNode(Boolean isLastNode)
	{
		this.isLastNode = isLastNode;
	}
	
	/**
	 * Returns a string describing the path represented by this node.
	 * 
	 * @return a string describing the path represented by this node
	 */
	@Override
	public String toString()
	{
		String description = (pathLength) + "(";
		
		for (Integer vertex : path)
		{
			description += vertex + ", ";
		}
		
		// The offset necessary to remove the leading comma.
		final int offset = 2;
		description = description.substring(
			CommonConstants.FIRST_CHAR_INDEX, description.length() - offset);
		description += ")";
		
		return description;
	}
}
