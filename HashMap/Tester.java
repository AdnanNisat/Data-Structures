public class Tester{
    /**
     * @param args
     */
    public static void main(String[] args) {
        Hash<String,Integer> hashTable = new Hash(1);

        hashTable.add("one", 1);
        hashTable.add("two", 2);
        hashTable.add("three", 3);

        System.out.println(hashTable.getValue("two"));

        for(String key : hashTable){
            System.out.println(key);
        }

    
}
}
