import java.io.*;
import java.util.*;

class Pair{
    int first,sec;
    public Pair(int f, int s){
        first = f;
        sec = s;
    }
}

class GFG {
    public static boolean dfsCycleRec(int node, int par, boolean vis[], ArrayList<ArrayList<Integer>> adj){
        vis[node] = true;
        for(Integer it: adj.get(node)){
            if(!vis[it])
                dfsCycleRec(it,node,vis,adj);
            else if(par!=it) return true;
        }
        return false;
    }
    
    public static boolean detectCycleDfs(ArrayList<ArrayList<Integer>> adj, int v){
        boolean vis[] = new boolean[v+1];
        for(int i=0;i<=v;i++)
            if(!vis[i])
                if(dfsCycleRec(i,-1,vis,adj))return true;
        
        return false;
    }
    
    public static boolean detectCycleBfs(ArrayList<ArrayList<Integer>> adj, int v){
        boolean vis[] = new boolean[v+1];
        for(int i=0;i<=v;i++){
            if(!vis[i]){
                Queue<Pair> q = new LinkedList<>();
                q.add(new Pair(i,-1));
                vis[i]=true;
                while(!q.isEmpty()){
                    int node = q.peek().first;
                    int par = q.peek().sec;
                    q.remove();
                    for(Integer it : adj.get(node)){
                        if(!vis[it]){
                            q.add(new Pair(it,node));
                            vis[it]=true;
                        }
                        else if(par!=it)return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    
    public static void getAdjList(ArrayList<ArrayList<Integer>> adj, int v){
        for(int i=0;i<=v;i++){
	        System.out.print(i+"|");
	        for(Integer it: adj.get(i))
	            System.out.print(it+" ");
	       System.out.println();
	    }
    }
    
    public static ArrayList<Integer> bfsOfGraph(ArrayList<ArrayList<Integer>> adj, int v){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        boolean vis[] = new boolean[v+1];
        for(int i=0;i<=v;i++){
            if(!vis[i]){
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                vis[i]=true;
                while(!q.isEmpty()){
                    int node = q.poll();
                    arr.add(node);
                    for(Integer it: adj.get(node)){
                        if(!vis[it]){
                            q.add(it);
                            vis[it]=true;
                        }
                    }
                }
            }
        }
        return arr;
    }
    
    public static void dfsRec(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> arr){
        vis[node] = true;
        arr.add(node);
        for(Integer it: adj.get(node))
            if(!vis[it])
                dfsRec(it,vis,adj,arr);
    }
    
    public static ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj, int v){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        boolean vis[] = new boolean[v+1];
        for(int i=0;i<=v;i++){
            if(!vis[i])
                dfsRec(i,vis,adj,arr);
        }
        return arr;
    }
    
    public static void printList(ArrayList<Integer> arr){
        for(int i=0;i<arr.size();i++)
            System.out.print(arr.get(i)+" ");
        System.out.println();
    }
    
	public static void main (String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int v=sc.nextInt();
	    int e=sc.nextInt();
	    ArrayList<ArrayList<Integer>> adj = new ArrayList<>(v);
	    
	    for(int i=0;i<=v;i++)
	        adj.add(new ArrayList<Integer>());
	    
	    for(int i=0;i<e;i++){
	        int a=sc.nextInt(), b=sc.nextInt();
	        addEdge(adj,a,b);
	    }
	    getAdjList(adj, v);
	    
	    ArrayList<Integer> bfs = bfsOfGraph(adj,v),dfs = dfsOfGraph(adj,v);
	    System.out.print("BFS: ");
	    printList(bfs);
	    System.out.print("DFS: ");
	    printList(dfs);
	    System.out.println("Is Cycle(bfs): "+detectCycleBfs(adj,v));
	    System.out.println("Is Cycle(dfs): "+detectCycleDfs(adj,v));
	}
}
/*
INPUT
-----
9
10
0 1
1 2
1 3
1 5
2 3
2 6
3 4
3 6
4 5
7 8
*/