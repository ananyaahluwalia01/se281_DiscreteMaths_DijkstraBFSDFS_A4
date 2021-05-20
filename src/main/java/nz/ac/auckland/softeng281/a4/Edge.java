package nz.ac.auckland.softeng281.a4;

import java.util.Objects;

// *******************************
// YOU CANNOT MODIFY THIS CLASS
// *******************************

public class Edge {

    private Node source;
    private Node target;
    private int weight;
    private Edge next;

    public Edge(Node source, Node target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public Edge(Node source, Node target) {
        this(source, target, 0);
    }


    public Edge getNext() {
        return next;
    }

    public void setNext(Edge next) {
        this.next = next;
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Edge{");
        sb.append("source=").append(source);
        sb.append(", target=").append(target);
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return getWeight() == edge.getWeight() &&
                Objects.equals(getSource(), edge.getSource()) &&
                Objects.equals(getTarget(), edge.getTarget());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSource(), getTarget(), getWeight());
    }
}
