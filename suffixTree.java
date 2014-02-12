import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;


public class suffixTree {
	String text;
	node root=new node();
	
	public void insert(int n,node init){
		if (init.children.size()==0){
			node temp = new node(n,text.length()-1);
			temp.parent=init;
			init.children.add(temp);
		}
		else{
			for (node ch:init.children){
				if(text.charAt(ch.x)==text.charAt(n)){
					Vector<Integer> suppl = (Vector<Integer>) ch.count.clone();
					ch.count.add(n);
					for (int i=0;i<Math.min(ch.y-ch.x+1,text.length()-n);i++){
						if(text.charAt(i+ch.x)!=text.charAt(n+i)){
							Vector<node> t1 = (Vector<node>) ch.children.clone();
							node n1 = new node(i+ch.x,ch.y,t1);
							n1.count = node.increment(suppl,i);
							n1.parent=ch;ch.y=i+ch.x-1;
							ch.children.removeAllElements();
							if(text.charAt(i)<text.charAt(n+i)){
								insert(n+i,ch);
								ch.children.add(n1);
							}
							else{
								ch.children.add(n1);
								insert(n+i,ch);
							}
							return;
						}
					}
					if((text.length()-n)>(ch.y-ch.x+1))
					{insert(n+ch.y-ch.x+1, ch);
					return;}
				}
				else if (text.codePointAt(ch.x)>text.codePointAt(n))
				{
					node n1=new node(n,text.length()-1);
					n1.parent=ch;
					init.children.insertElementAt(n1, init.children.indexOf(ch));
					return;
				}  			
			}
			node temp = new node(n,text.length()-1);
			temp.parent=init;
			init.children.add(temp);
		}
	}

	public Vector<Integer> star(node n,int i,int k,char c,int b,String s,int limit,Integer counter){
		Vector<Integer> temp= new Vector<Integer>();
		for (int j=i+1;j<n.y-n.x+1;j++){
			k++;counter++;
			if(counter<limit){
			if (c=='^'){
				temp.add(n.x+j-k);
				temp.add(n.x+j);
			}}
		}
		if(counter<limit){
		for(node ch:n.children){
			temp.addAll(star(ch,-1,k,c,1,s,limit,counter.intValue()));
		}
		}
		if(b==0){
		temp.insertElementAt(this.text.length()*2, 0);}
		return temp;
	}
	public Vector<Integer> search(String perm,String s,node n,int j,int limit){
		Vector<Integer> temp = new Vector<Integer>();
		if(n!=root){
		for (int i =0;i<Math.min(s.length(),(n.y-n.x+1));i++){
			if(s.charAt(i)=='*'){
				if(i==(s.length()-1)){
					if(limit==0){
					return (star(n,i,-j+i,'^',0,s,this.text.length(),0));}
					else{return (star(n,i,-j+i,'^',0,s,limit-1,0));}
			}
				else{
					temp.add(this.text.length()*2);
					Vector<Integer> pro1 = search(perm,perm.substring(0,perm.indexOf('*')),root,0,0);
					Vector<Integer> pro2 = search(perm,perm.substring(perm.indexOf('*')+1),root,0,0);
					System.out.println("");
					for(Integer p1:pro1){
						for(Integer p2:pro2){
							if(p2-p1>=(perm.indexOf('*'))&&(p2-p1)<=limit){temp.add(p1);temp.add(p2+perm.length()-perm.indexOf('*')-2);}
						}
					}
 				}
				return temp;
			}
			else if (s.charAt(i)!=this.text.charAt(n.x+i) && s.charAt(i)!='?'){
				return temp;
			}
		}
		if (s.length()<=(n.y-n.x+1)){
			temp.add(n.x);
			temp.addAll(n.count);
			return node.increment(temp,j);
		}
		else{
			for (node no:n.children){
				if(s.substring(n.y-n.x+1).charAt(0)=='*'&& s.substring(n.y-n.x+1).length()!=1){
				return (search(perm,s.substring(n.y-n.x+1),no,-(n.y-n.x+1-j),limit));}
				else{
					temp.addAll(search(perm,s.substring(n.y-n.x+1),no,-(n.y-n.x+1-j),limit));
				}
			}
		}
		}
		else{for (node no:n.children){
			temp.addAll(search(perm,s,no,0,limit));}}
		return temp;
	}
	
	void enumerate(node n){
		for (node children:n.children){
			System.out.println(children.x+"      "+ this.text.substring(children.x,children.y+1));
		}
	}
	void enumerate1(Vector<Integer> v,String s){
		if(v.size()==0){System.out.println("No match found");}
		else if(v.get(0)==this.text.length()*2){
			while(v.contains(this.text.length()*2)==true){v.removeElement(this.text.length()*2);}
			for (int i=0;i<v.size();i=i+2){
				System.out.println(v.get(i)+"      " + v.get(i+1)+"   "+this.text.substring(v.get(i),v.get(i+1)+1));
					}
			System.out.println("Number of Matches:  "+ (v.size()-1)/2);}
		else{for (int i=0;i<v.size();i++){
			System.out.println(v.get(i)+"      " + (v.get(i)+s.length()-1)+"   "+this.text.substring(v.get(i),v.get(i)+s.length()));}
		System.out.println("Number of Matches:  "+ v.size());}
	}
	
	
	public void search(String s1,int n1){
		enumerate1(search(s1,s1,root,0,n1),s1);
	}
public static void main(String[] args) throws IOException{
	suffixTree T = new suffixTree();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Enter the filename!");
	File fl = new File(br.readLine());
	T.text = readTextFile.getContents(fl).toLowerCase();
	for(int i=0;i<T.text.length();i++){T.insert(i,T.root);}
	String input = null;
	boolean b = true;
	while(b!=false){
		int n1 = 1111111;
		System.out.println("Enter the String Query or Enter Q to exit !");
		System.out.println("The query can be of the type String or String,int ; where int specifies the maximum size of *.");
		try{input = br.readLine();
		String[] p = input.split(",");
		input = p[0];if(p.length>1){n1=Integer.parseInt(p[1]);}
		}
		catch(Exception IOException ){break;}
		if(input.charAt(0)=='q'&&input.length()==1) {b=false;System.out.println("Thanks for using CSL201 Text Search.");return;}
		T.search(input,n1);
	}
	
}
}

