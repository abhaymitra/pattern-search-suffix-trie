
import java.util.Vector;


public class node {

	node parent;
	Vector<node> children=new Vector<node>();
	Vector<Integer> count=new Vector<Integer>();
	int x ;
	int y;
	public node(int n1,int n2){
		x =n1;y=n2;
	}
	public node(int n1,int n2,Vector<node> v){
		x=n1;y=n2;children=v;
	}
	
	public node() {	}
	public static Vector<Integer> increment(Vector<Integer> no,int n){
		Vector<Integer> temp=new Vector<Integer>();
		for (Integer t:no){
			temp.add(t+n);
		}
		return temp;
	}
	/*public int length(){
		if (count.size()==0){return 0;}
		else{return (count.get(0).y-count.get(0).x+1);}
	}*/
	public static void main(String[] args) {
		String s = "notes of dissent";
		System.out.println(s.substring(2));
		Vector<Integer> n1 = new Vector<Integer>();
		n1.add(1);n1.add(2);n1.add(3);
		Vector<Integer> n2 = new Vector<Integer>();
		n2.add(1);n2.add(2);n2.add(3);n2.add(3);
		for(Integer i:n1){
			for(Integer j:n2){
				System.out.println(i+ "     "+j);
			}
		}
	}

}
