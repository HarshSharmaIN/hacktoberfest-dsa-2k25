import java.util.Random;

class SkipListNode {
    int value;
    SkipListNode[] forward;

    public SkipListNode(int value, int level) {
        this.value = value;
        forward = new SkipListNode[level + 1];
    }
}

class SkipList {
    private static final int MAX_LEVEL = 5;
    private static final double P = 0.5;
    private int level = 0;
    private final SkipListNode header = new SkipListNode(-1, MAX_LEVEL);
    private final Random random = new Random();

    private int randomLevel() {
        int lvl = 0;
        while (random.nextDouble() < P && lvl < MAX_LEVEL)
            lvl++;
        return lvl;
    }

    public void insert(int value) {
        SkipListNode[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode current = header;

        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[0];

        if (current == null || current.value != value) {
            int newLevel = randomLevel();

            if (newLevel > level) {
                for (int i = level + 1; i <= newLevel; i++) {
                    update[i] = header;
                }
                level = newLevel;
            }

            SkipListNode newNode = new SkipListNode(value, newLevel);
            for (int i = 0; i <= newLevel; i++) {
                newNode.forward[i] = update[i].forward[i];
                update[i].forward[i] = newNode;
            }
        }
    }

    public boolean search(int value) {
        SkipListNode current = header;
        for (int i = level; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
        }
        current = current.forward[0];
        return current != null && current.value == value;
    }

    public void display() {
        System.out.println("\n==== Skip List ====");
        for (int i = level; i >= 0; i--) {
            System.out.print("Level " + i + ": ");
            SkipListNode node = header.forward[i];
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.forward[i];
            }
            System.out.println();
        }
    }
}

public class SkipList {
    public static void main(String[] args) {
        SkipList list = new SkipList();

        list.insert(3);
        list.insert(6);
        list.insert(7);
        list.insert(9);
        list.insert(12);
        list.insert(19);
        list.insert(17);
        list.insert(26);
        list.insert(21);
        list.insert(25);

        list.display();

        int searchValue = 19;
        System.out.
