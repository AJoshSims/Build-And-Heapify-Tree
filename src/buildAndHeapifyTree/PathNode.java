package buildAndHeapifyTree;

import java.util.ArrayList;

final class PathNode
{
	private ArrayList<Integer> path;
	
	private int pathLength = -1;
	
	private PathNode left;

	private PathNode right;
	
	private PathNode parent;
	
	private PathNode sibling;
	
	private Boolean isLevelEnd;
	
	private Boolean isLastNode;
	
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
	
	ArrayList<Integer> getPath()
	{
		return path;
	}
	
	void addToPath(Integer vertex)
	{
		path.add(vertex);
		
		++pathLength;
	}
	
	int getPathLength()
	{
		return pathLength;
	}

	PathNode getLeft()
	{
		return left;
	}

	void setLeft(PathNode left)
	{
		this.left = left;
	}

	PathNode getRight()
	{
		return right;
	}
	
	void setRight(PathNode right)
	{
		this.right = right;
	}
	
	PathNode getParent()
	{
		return parent;
	}
	
	void setParent(PathNode parent)
	{
		this.parent = parent;
	}

	PathNode getSibling()
	{
		return sibling;
	}

	void setSibling(PathNode sibling)
	{
		this.sibling = sibling;
	}
	
	private Boolean isLevelEnd()
	{
		return isLevelEnd;
	}
	
	void setIsLevelEnd(Boolean isLevelEnd)
	{
		this.isLevelEnd = isLevelEnd;
	}
	
	private Boolean isLastNode()
	{
		return isLastNode;
	}

	private void setIsLastNode(Boolean isLastNode)
	{
		this.isLastNode = isLastNode;
	}
	
	@Override
	public String toString()
	{
		String description = (pathLength) + "(";
		
		for (Integer vertex : path)
		{
			description += vertex + ", ";
		}
		
		description = description.substring(0, description.length() - 2);
		description += ")";
		
		return description;
	}
}
