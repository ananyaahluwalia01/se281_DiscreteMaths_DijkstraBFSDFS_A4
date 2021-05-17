package nz.ac.auckland.softeng281.a4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// *******************************
// YOU CANNOT MODIFY THIS CLASS
// *******************************

public class Path {

    private List<Node> path;
    private int totalCost;

    public Path(int totalCost, Node... nodes) {
        this(totalCost, Arrays.asList(nodes));
    }

    public Path(int totalCost, List<Node> list) {
        this.path = new ArrayList<>(list);
        this.totalCost = totalCost;
    }


    public List getPath() {
        return path;
    }

    public int getTotalCost() {
        return totalCost;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i).getValue());
            if (i != path.size() - 1 && path.size() != 1) {
                sb.append(" -> ");
            }
        }
        sb.append(" cost: " + totalCost);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;
        Path path1 = (Path) o;
        return getTotalCost() == path1.getTotalCost() &&
                Objects.equals(getPath(), path1.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPath(), getTotalCost());
    }
}
