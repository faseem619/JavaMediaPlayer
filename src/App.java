public class App {
    public static void main(String[] args) throws Exception {
         if(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) //checking if os is mac os
		{
		 System.setProperty("apple.laf.useScreenMenuBar", "true");
		  System.setProperty(
            "com.apple.mrj.application.apple.menu.about.name", "Name");
		}
        new Player();
        
    }
}
