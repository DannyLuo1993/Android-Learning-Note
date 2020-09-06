public class SingleLinkedList {

    //记录单向链表的头结点
    private Node first;
    private int total;

    //内部类，因为节点类只为单向链表服务
    //用于存储和表示单向链表的节点关系
    private class Node{
        //不私有化，供外部类使用
        Object data;
        Node next;
        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node() {
        }
    }

    public void add(Object obj){

        //把obj的数据包装成一个Node类型结点对象
        Node newNode = new Node(obj, null);

        //将新结点“链接”到当前链表的最后
        if(first == null){
            first = newNode;
        }else{
            Node node = first;
            while (node.next != null){
                node = node.next;
            }
        }

        total ++;

    }

    public void delete(Object obj){

        int count = 0;
        boolean flag = true;
        Node node = new Node();
        while (flag){
            if(node.data != obj){
                node = node.next;
                count++;
            }else {
                //找到之后，复制和移动位置
                for( int i = count; i < total; i++){
                    node.data = node.next.data;
                    node.next = node.next.next;
                    flag = false;
                }
            }
            if (count > total){
                flag = false;
            }
        }
        total --;
    }

    public void removeRef(Object obj){

        if(obj == null){
            //先考虑是不是第一个结点
            if(first != null){
                if(first.data == null){
                    //让第一个结点指向它的下一个
                    first = first.next;
                    total --;
                    return;;
                }
            }

            //要删除的不是第一个结点
            Node node = first.next;
            Node last = first;
            while (node.next != null){
                if(node.data == null){
                    last.next = node.next;
                    total--;
                    return;
                }
                last = node;
                node = node.next;
            }

            if(obj.equals(node.data)){
                //要删除的是最后一个结点
                last.next = null;
                total --;
                return;
            }


        }

    }

    public void remove(Object obj){

    }

}
