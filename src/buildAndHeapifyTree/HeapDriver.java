package buildAndHeapifyTree;

public class HeapDriver
{
	public static void main(String[] args)
	{
		// TODO remove
		Heap heap = new Heap();
		heap.readPaths("input3.txt");
		heap.buildCompleteTree(heap.tempPath, 1, null);
		heap.setLevelEnd(heap.tempPath.get(1));
		heap.setSiblingLinks(heap.tempPath.get(1));
		heap.printTreeLevels();
		
		System.out.println("");
		
//		heap.heapify();
//		heap.printTreeLevels();
	}
}
