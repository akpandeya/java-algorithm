public class HelloGoodbye {
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        if (args.length != 2)
        {
            System.out.println("The program needs exactly to arguments");
        }
        else 
        {
            System.out.println("Hello " + args[0] + " and " + args[1] + ".");
            System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
        }
    }
}
