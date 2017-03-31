package SinglyLinkedlist;

import javax.swing.JOptionPane;

public class SinglyLinkedlist {
	Object rdata; //remove ��ų ����
	Object gdata; //get���� ���� ����
	static Node head, tail;
	int size=0;
	
	public static class Node
	{
		Object data;
		Node next;
		public Node() {
			this.next = null;
			this.data = null;
		}
		public Node(int data){
			this(data, null);
		}
		public Node(int data, Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	
	private void add(int input)
	{
		Node cur = head;
		Node addnode = new Node(input);
		while(true)
		{
			if(head.next == tail || (cur == head && input <= (int)cur.next.data) || 
					cur.next == tail ||
					(cur != head && input >= (int)cur.data && input <= (int)cur.next.data))
			{
				addnode.next = cur.next;
				cur.next = addnode;
				break;
			}
			else
			{
				cur = cur.next;
			}
		}
		this.size++;
	}
	
	private Object remove(int index)
	{
		Node cur = head;
		if(index < 1 || index > this.size) //����
		{
			return null;
		}
		if(index == 1) //ù��° ������ ����
		{
			rdata = head.next.data;
			head.next = head.next.next;
			this.size--;
			return rdata;
		}
		for(int i=0; i<index-1; i++) //�� �̿��� ������ ����
		{
			cur = cur.next;
		}
		rdata = cur.next.data;
		cur.next = cur.next.next;
		this.size--;
		return rdata;
	}
	
	private Object get(int index)
	{
		Node cur = head;
		if(index <= 0 || index > this.size) //����
		{
			return null;
		}
		for(int i=0; i<index; i++)
		{
			cur = cur.next;
		}
		gdata = cur.data;
		return gdata;
	}
	
	private Object set(int index, int input)
	{
		Node cur = head;
		Object predata;
		if(index <= 0 || index > this.size) //����
		{
			return  null;
		}
		for(int i=0; i<index; i++)
		{
			cur = cur.next;
		}
		predata = cur.data;
		if(cur.data != null) cur.data = input; //�� �����ʹ� set �Ұ�
		return predata;
	}
	
	private void set_(int index, int input) //set �Ŀ� �������� ����
	{
		Node cur = head;
		if(index <= 0 || index > this.size) //����
		{
			return;
		}
		for(int i=0; i<index; i++)
		{
			cur = cur.next;
		}
		if(cur.data != null) //�� �����ʹ� set �Ұ�
		{
			this.remove(index);
			this.add(input);
		}
	}
	
	private void print()
	{
		Node cur = head;
		for(int i=0; i<this.size; i++)
		{
			cur = cur.next;
			if(cur.data != null) System.out.print(cur.data+" "); //�� �����ʹ� print x
		}
		System.out.println("");
	}
	
	public static void main(String[] args)
	{
		boolean exit = true;
		SinglyLinkedlist list = new SinglyLinkedlist();
		String menu;
		list.head = new Node();
		list.tail = new Node();
		list.head.next = list.tail;
		System.out.println("SinglyLinkedlist");
		while(exit)
		{
			menu = JOptionPane.showInputDialog("(1~6�� �Է��ϼ���.)\n1.add\n2.remove\n3.get\n4.set\n5.print\n6.exit");
			switch(menu)
			{
			case "1": //add
				int inputdata;
				try
				{
					inputdata = Integer.parseInt(JOptionPane.showInputDialog("add�� ���ڸ� �Է��ϼ���."));
					list.add(inputdata);
					System.out.println("add : "+inputdata);
				}
				catch(Exception e)
				{
					System.out.println("���ڸ� �Է��ϼ���.");
				}
				break;
			case "2": //remove
				int removeindex;
				Object removedata;
				try
				{
					removeindex = Integer.parseInt(JOptionPane.showInputDialog("remove�� index�� �Է��ϼ���."));
					removedata = list.remove(removeindex);
					System.out.println("remove : "+removedata);
				}
				catch(Exception e)
				{
					System.out.println("���ڸ� �Է��ϼ���.");
				}
				break;
			case "3": //get
				int getindex; 
				Object getdata;
				try
				{
					getindex = Integer.parseInt(JOptionPane.showInputDialog("get�� index�� �Է��ϼ���."));
					getdata = list.get(getindex);
					System.out.println("get : "+getdata);
				}
				catch(Exception e)
				{
					System.out.println("���ڸ� �Է��ϼ���.");
				}
				break;
			case "4": //set
				int setindex, setdata;
				Object predata;
				try
				{
					setindex = Integer.parseInt(JOptionPane.showInputDialog("set�� index�� �Է��ϼ���."));
					setdata = Integer.parseInt(JOptionPane.showInputDialog("set�� data�� �Է��ϼ���."));
					predata = list.set(setindex,setdata);
					if(predata != null) System.out.println("set : "+predata+" -> "+setdata);
					else System.out.println(setindex+" ��° data�� �����ϴ�.");
				}
				catch(Exception e)
				{
					System.out.println("���ڸ� �Է��ϼ���.");
				}
				break;
			case "5": //print
				list.print();
				break;
			case "6": //exit
				exit = false;
				break;
			}
		}
	}
}

