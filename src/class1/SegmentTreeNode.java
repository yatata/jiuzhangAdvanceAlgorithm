package class1;

public class SegmentTreeNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public SegmentTreeNode left, right;
	public int max;
	public int start;
	public int end;
	
	public SegmentTreeNode(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public SegmentTreeNode build(int start, int end) {
		if (start > end) {
			return null;
		}
		SegmentTreeNode root = new SegmentTreeNode(start, end);
		if (start != end) {
			int mid = start + (end - start)/2;
			root.left = build(start, mid);
			root.right = build(mid + 1, end);
		}
		return root;
	}
	
	public SegmentTreeNode build2(int start, int end) {
		return null;
	}
	
	public void modify(SegmentTreeNode root, int index, int value) {
		
	}
	
	public int query(SegmentTreeNode root, int start, int end) {
		return -1;
	}
	
	
	
	
	
	
}
