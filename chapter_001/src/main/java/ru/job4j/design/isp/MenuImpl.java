package ru.job4j.design.isp;

import java.util.*;

/**
 * Menu implementation.
 */
public class MenuImpl implements Menu {
    /**
     * Root menu object.
     */
    final private Node top;

    /**
     * @param id   - root node id
     * @param name - root node name
     */
    public MenuImpl(String id, String name) {
        top = new NodeImpl(id, name, Command.EMPTY);
    }

    /**
     * Add node to menu.
     *
     * @param newNode - new node
     * @param id      - id parent node
     * @return result node adding
     */
    @Override
    public boolean addNode(Node newNode, String id) {
        boolean rsl = false;
        MenuIteratorImpl menuIterator = new MenuIteratorImpl();
        while (menuIterator.hasNext()) {
            Node n = menuIterator.next();
            if (n.getId().equals(id)) {
                n.addChildren(newNode);
                rsl = true;
                break;
            }

        }
        return rsl;
    }

    @Override
    public boolean execute(String id) {
        boolean rsl = false;
        Node node = findNode(id);
        if (node != Node.EMPTY) {
            rsl = node.execute();
        }
        return rsl;
    }

    /**
     * Find node by id.
     *
     * @param id - desired node id
     * @return Node - searching result or Node.Empty if failed
     */
    @Override
    public Node findNode(String id) {
        Node rsl = Node.EMPTY;
        MenuIteratorImpl menuIterator = new MenuIteratorImpl();
        while (menuIterator.hasNext()) {
            Node n = menuIterator.next();
            if (n.getId().equals(id)) {
                rsl = n;
                break;
            }

        }
        return rsl;
    }

    /**
     * Menu iterator.
     *
     * @return Menu iterator implements.
     */
    @Override
    public MenuIterator iterator() {
        return new MenuIteratorImpl();
    }

    /**
     * Convert menu object ot list
     *
     * @return List object of nodes
     */
    @Override
    public List<Node> toList() {
        ArrayList<Node> rsl = new ArrayList<>();
        MenuIterator iterator = iterator();
        while (iterator.hasNext()) {
            rsl.add(iterator.next());
        }
        return rsl;
    }

    /**
     * Menu iterator implementation.
     */
    private class MenuIteratorImpl implements MenuIterator {
        /**
         * Current node.
         */
        private Node currentNode = Node.EMPTY;
        /**
         * Current iterator.
         */
        ListIterator<Node> currentIterator;
        /**
         * iterators one ever level deep
         */
        private Stack<ListIterator<Node>> iterators = new Stack<>();

        /**
         * Get next node.
         *
         * @return Node next
         */
        @Override
        public Node next() {
            if (hasNext()) {
                if (currentNode == Node.EMPTY) {
                    init();
                } else if (currentNode.hasChildren()) {
                    currentIterator = currentNode.childIterator();
                    iterators.add(currentIterator);
                }
                currentNode = currentIterator.next();
            }
            return currentNode;
        }

        /**
         * Check has next node.
         *
         * @return has next node result
         */
        @Override
        public boolean hasNext() {
            boolean rsl = false;
            if (currentNode == Node.EMPTY || currentNode.hasChildren()) {
                rsl = true;
            } else {
                stackUp();
                if (!iterators.empty()) {
                    rsl = true;
                }
            }
            return rsl;
        }

        /**
         * Reset iterator state to top.
         */
        @Override
        public void reset() {
            iterators.clear();
            currentNode = Node.EMPTY;
            currentIterator = null;
        }


        /**
         * Up to stack while has next.
         */
        private void stackUp() {
            while (!currentIterator.hasNext() && !iterators.empty()) {
                currentIterator = iterators.pop();
            }
        }


        /**
         * Init iterator.
         */
        private void init() {
            ArrayList<Node> menu = new ArrayList<>();
            menu.add(top);
            currentNode = top;
            currentIterator = menu.listIterator();
            iterators.clear();
            iterators.add(currentIterator);
        }
    }
}
