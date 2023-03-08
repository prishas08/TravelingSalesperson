
//import MyLinkedList.ListNode;


public class Tour 
{
	Node head;
	/** create an empty tour */
	public Tour()
	{
		head = null;
		//TODO
	}
	
	/** create a four-point tour, for debugging */
	public Tour(Point a, Point b, Point c, Point d)
	{
		head = new Node(a, null);
		head.next = new Node(b, head);
		head.next = new Node(c, head);
		head.next = new Node(d, head);
		/*Node temp = this.head;
		Node list = new Node(b, null);
		temp.next = list;
		list = new Node(c, null);
		temp.next = list;
		list = new Node(d, null);
		temp.next = list;*/
		//TODO
	}
	
	private class Node
	{
		Point P;
		Node next;
		
		public Node(Point P, Node node)
		{
			this.P = P;
			this.next = node;
		}
		
		@Override
		public String toString()
		{
			return "" + this.P;
		}
	}
	
	/** print tour (one point per line) to std output */
	public void show()
	{
		if(head!=null)
		{
			Node temp = this.head;
			while(temp!=null)
			{
				System.out.println(temp.P);
				temp = temp.next;
			}
		}
		//TODO
	}
	
	/** draw the tour using StdDraw */
	public void draw()
	{
		if(head!=null)
		{
			Node temp = this.head;
			while(temp.next!=null)
			{
				temp.P.drawTo(temp.next.P);
				temp = temp.next;
			}
		}
		//TODO
	}
	
	/** return number of nodes in the tour */
	public int size()
	{
		//TODO
		Node temp = this.head;
		int counter = 0;
		while(temp!=null)
		{
			temp = temp.next;
			counter++;
		}
		return counter;	

	}
	
	/** return the total distance "traveled", from start to all nodes and back to start */
	public double distance()
	{
		double distance = 0.0;
		//TODO
		if(head!=null)
		{
			Node temp = this.head;
			while(temp.next!=null)
			{
				distance += temp.P.distanceTo(temp.next.P);
				temp = temp.next;
			}
		}
		return distance;
	}
	
	public void insertInOrder(Point p)
	{
		if(head==null)
		{
			head = new Node(p, null);
			
		}
		else
		{
			Node temp = this.head;
			while(temp.next!=null)
			{
				temp = temp.next;
			}
			Node list = new Node(p, null);
			temp.next = list;
		}
	}
	/** insert p using nearest neighbor heuristic */
    public void insertNearest(Point p) 
    {
    	double shortestDistance = head.P.distanceTo(p);
    	if(head!=null)
		{
			Node temp = this.head;
			while(temp.next!=null)
			{
				if(shortestDistance>temp.P.distanceTo(p))
				{
					shortestDistance = temp.P.distanceTo(p);
					
				}
				temp = temp.next;
			}
		}
    	if(head!=null)
		{
			Node temp = this.head;
			while(temp.next!=null)
			{
				if(shortestDistance==temp.P.distanceTo(p))
				{
					Node temp2 = temp.next;
					temp.next = new Node(p, temp2);
				}					
				temp = temp.next;
			}
		}
    }

	/** insert p using smallest increase heuristic */
    public void insertSmallest(Point p) 
    {
    	double distance = 100000;
    	int finalCounter = 0;
    	if(head!=null)
		{
    		Node temp = this.head;
			for(int i = 0; i < size()+1; i++)
			{
				int counter = 0;
				while(temp.next!=null)
				{
					if(i==counter)
					{
						Node temp2 = temp.next;
						temp.next = new Node(p, temp2);
					}					
					temp = temp.next;
					counter++;
				}
				if(distance()<distance)
				{
					distance = distance();
					finalCounter = i;
				}
			}
			
		}
    	if(head!=null)
		{
    		int c = 0;
			Node temp = this.head;
			while(temp.next!=null)
			{
				if(c==finalCounter)
				{
					Node temp2 = temp.next;
					temp.next = new Node(p, temp2);
				}					
				temp = temp.next;
				c++;
			}
		}
    }
    
    //myChoice - farthest point from beginning?
    public void insertBestChoice(Point p)
    {
    	double farthestDistance = head.P.distanceTo(p);
    	if(head!=null)
		{
			Node temp = this.head;
			while(temp.next!=null)
			{
				if(farthestDistance<temp.P.distanceTo(p))
				{
					farthestDistance = temp.P.distanceTo(p);
					
				}
				temp = temp.next;
			}
		}
    	if(head!=null)
		{
			Node temp = this.head;
			while(temp.next!=null)
			{
				if(farthestDistance==temp.P.distanceTo(p))
				{
					Node temp2 = temp.next;
					temp.next = new Node(p, temp2);
				}					
				temp = temp.next;
			}
		}
    	
    }


	public static void main(String[] args) 
	{
		Point a = new Point(100.0, 100.0);
		Point b = new Point(500.0, 100.0);
		Point c = new Point(500.0, 500.0);
		Point d = new Point(100.0, 500.0);
		Tour squareTour = new Tour(a, b, c, d);
		squareTour.show();
		StdDraw.setXscale(0,600);
		StdDraw.setYscale(0,600);
		squareTour.draw();
		System.out.println(squareTour.size());
		System.out.println(squareTour.distance());
		squareTour.insertNearest(d);
		squareTour.show();
		squareTour.insertSmallest(d);
		squareTour.show();
		squareTour.insertBestChoice(d);
		squareTour.show();
	}
}
