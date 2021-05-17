package nz.ac.auckland.softeng281.a4;


// *******************************
// YOU CANNOT MODIFY THIS CLASS
// *******************************

/**
 * This program uses MVC and has the following classes The SetUI class reads a
 * file in dot format It also has a method to read user commands over this file
 * The entity classes are SetOfStrings and StringRelations The SetContrtol is
 * the main controller which has a execute() method that joins all the classes
 * by passing appropriate messages
 * <p>
 * The controller class parses the user commands by invoking the appropriate
 * method of the SetUI class and then processes the command The program runs
 * until user specifies the "exit" command and to support set operations on
 * these SetOfStrings
 *
 * @author PARTHA ROOP
 */
public class GraphControl {
    private Graph graph;
    private final GraphUI sUI;

    /**
     * The constructor that initializes all private members
     */
    public GraphControl() {
        sUI = new GraphUI();
    }

    /**
     * The main method that reads the next user command and processes it until the
     * "exit" command is entered
     */
    public void execute() {
        boolean isAnotherCommand;
        do {
            String nextCmd = sUI.getCommand();
            isAnotherCommand = processCommand(nextCmd);
        } while (isAnotherCommand);
    }

    /**
     * This method processes a given user command
     *
     * @param command
     * @return true if another command is required false if we need to terminate the
     * program
     */
    public boolean processCommand(String command) {

        System.out.println("The command is " + command);
        String[] parts = command.split(" ");

        switch (parts[0]) {
            case "open":
                if (parts.length == 1) {
                    System.out.println("Invalid file name");
                    return true;
                } else if (parts.length == 2) {
                    sUI.open(parts[1]);
                    createGraph();
                } else if (!sUI.getFileStatus()) {
                    System.out.println("File can't be opened");
                    System.out.println("Enter a valid file name");
                    return true;
                }
                break;

            case "help":
                System.out.println("You can either *open* a file or *list* an opened file or *exit* the program");
                System.out.println("Once a valid file is open you can *search* in the graph for a given edge or weight");
                System.out.println("You can also determine the shortest path using the *path* command");
                break;
            case "list":
                if (sUI.getFileStatus()) {
                    sUI.list();
                }
                break;
            case "check":
                if (!sUI.getFileStatus()) {
                    System.out.println("First open a valid file ");
                    break;
                } else if (parts.length == 1) {
                    System.out.println("Invalid check command: specify -r / -s/ -t ");
                    return true;
                } else if (parts.length == 2) {
                    switch (parts[1]) {
                        case "-r":
                            break;

                        default:
                            System.out.println("Invalid check command entered .. try again");
                            break;
                    }
                }
                break;
            case "path":
                if (sUI.getFileStatus()) {
                    if (parts.length != 3) {
                        System.out.println("Incorrect arguments for path (i.e. path x y)");
                        break;
                    }
                    sUI.listShortestPath(graph.computeShortestPath(new Node(parts[1]), new Node(parts[2])));
                    createGraph();
                }
                break;

            case "search":
                if (sUI.getFileStatus()) {
                    switch (parts.length) {

                        case 2:
                            sUI.listEdgeGivenWeight(graph.searchEdgeByWeight(Integer.valueOf(parts[1])), Integer.valueOf(parts[1]));
                            break;
                        case 3:
                            sUI.listWeightGivenEdge(new Node(parts[1]), new Node(parts[2]), graph.searchWeightByEdge(new Node(parts[1]), new Node(parts[2])));
                            break;
                        default:
                            System.out.println("Incorrect arguments for search: argument can be either a weight or an edge with source and target");
                    }
                    createGraph();

                }
                break;
            case "exit":
                System.out.println("We will exit now.. bye!!");
                return false;
            default:
                System.out.println("Enter a valid command:");
                return true;
        }
        return true;
    }

    private void createGraph() {
        graph = new Graph(sUI.getRelationElements(), sUI.getWeightElements());
    }


    public static void main(String[] args) {
        GraphControl controller = new GraphControl();
        controller.execute();
    }
}