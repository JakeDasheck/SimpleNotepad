

package SimpleNotepad;


public class Main {

  public static void main(String[] args) {
	  String title = "Simple notepad";
	  Notepad notepad = new Notepad(title);
	  notepad.setSize(500,500);
	  notepad.setVisible(true);
  }
}
