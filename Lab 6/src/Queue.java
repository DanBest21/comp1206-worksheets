public class Queue {

    private Cell head, tail;

    public synchronized void add(Object o){
        Cell c = new Cell(o);
        if (tail == null) { head = c; }
        else { tail.next = c; }
        c.next = null;
        tail = c;
        notifyAll();
    }

    public synchronized Object remove()
            throws InterruptedException {
        while (head == null){
            wait();
        }
        Cell c = head;
        head = head.next;
        if (head == null){ tail = null; };
        return c.contents;
    }

}

class Cell {
    Cell next;
    Object contents;
    public Cell(Object o) { contents = o; }
}