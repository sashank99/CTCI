package linked_lists;

/**
 * Write code to partition a linked list around value x, such that all elements less than x come before all nodes greater than or equal to x.
 * If x is contained within the list, the values of x only need to be after the elements less than x(see below).
 * The partition element x can appear anywhere in the right partition; it does not need to appear between left and right partitions.
 * Ex:
 * Input: 3-> 5 -> 8 -> 5 -> 10 -> 2 -> 1 (partition=5)
 * Output: 3 -> 1-> 2 -> 10 -> 5 -> 5 -> 8
 */
public class PartitionLinkedList{
    
    public static void main(String[] args){
        Node head=new Node(3);
        Node node2=new Node(5);
        Node node3=new Node(8);
        Node node4=new Node(5);
        Node node5=new Node(10);
        Node node6=new Node(2);
        Node node7=new Node(1);
        head.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        node6.next=node7;
        System.out.println(head);
        System.out.println(partitionList(head,3));
    }
    
    private static Node partitionList(Node node, int partition){
        
        Node head=null;
        Node tail=null;
        Node tailPointer=null;
        Node headPointer=null;
        while(node!=null){
            
            if(node.val< partition){
                if(head==null){
                    head=node;
                    headPointer=head;
                }else{
                    head.next=node;
                    head=head.next;
                }
                
            }else{
                if(tail==null){
                    tail=node;
                    tailPointer=tail;
                }else{
                    tail.next=node;
                    tail=tail.next;
                }
                
            }
            
            node=node.next;
            
        }
        
        if(tail!=null){
            tail.next=null;
        }
        if(head!=null){
            head.next=tailPointer;
        }else{
            headPointer=tailPointer;
        }
        
        return headPointer;
    }
    
}

