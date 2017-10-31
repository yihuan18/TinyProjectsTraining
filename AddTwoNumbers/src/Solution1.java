/**
 * Created by Yihuan on 2017/10/31.
 */
public class Solution1{
    public ListNode addTwoNumbers(ListNode l1 , ListNode l2){
        int addToNext = 0;
        int tag = 0;//tag为0标识1短，tag为1标识2短
        ListNode result,result_head;
        result = new ListNode(0);
        result_head = result;
        while(l1 != null ) {
            if (l2 == null) {
                tag = 1;
                break;
            }

            result.next = new ListNode(0);
            result = result.next;

            result.val = (l1.val + l2.val + addToNext) % 10;
            addToNext = (l1.val + l2.val + addToNext) / 10;         //进位的数字

            l1 = l1.next;
            l2 = l2.next;
        }

        if(tag == 1){ //此时l1不为空，l2位空。
            while(l1 != null){
                result.next = new ListNode((l1.val + addToNext) % 10);
                result = result.next;
                addToNext = (l1.val + addToNext) / 10;
                l1 = l1.next;
            }
            if(addToNext == 0)
                return result_head.next;
            else{
                result.next =  new ListNode(addToNext);
                return result_head.next;
            }
        }

        else if(tag == 0){
                while(l2 != null){
                    result.next = new ListNode((l2.val + addToNext) % 10);
                    result = result.next;
                    addToNext =  (l2.val + addToNext) / 10;
                    l2 = l2.next;
                }
                if(addToNext == 0)
                    return result_head.next;
                else{
                    result.next =  new ListNode(addToNext);
                    return result_head.next;
                }
        }

        return result_head.next;
    }


    public static void main(String args[]){
        Solution1 s = new Solution1();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode result = s.addTwoNumbers(l1,l2);
        System.out.print("Output:");
        while(result != null){
            System.out.print(result.val);
            System.out.print("->");
            result = result.next;
        }

        ListNode l3 = new ListNode(5);
        ListNode l4 = new ListNode(5);
        result = s.addTwoNumbers(l3,l4);

        return;
    }

}
