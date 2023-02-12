public class Tester{
    public static void main(String[] args) {
        ListI<Integer> list = new LinkedList<Integer>();
        int n = 10;
        for(int i = 0; i < n; i++){
            list.addFirst(i);
        }
        
        for(int x: list){
        System.out.println(x);
        }

}
}
