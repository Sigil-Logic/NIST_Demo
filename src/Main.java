//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// @review Claude This file needs a standard RDE header, which includes a Copyright that is for Galois.
void main() {
  //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
  // to see how IntelliJ IDEA suggests fixing it.
  // The quick brown fox jumps over the lazy dog.
  IO.println(String.format("Hello and welcome!"));
  IO.println("The quick brown fox jumps over the lazy dog.");

  for (int i = 1; i <= 5; i++) {
    //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
    // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
    IO.println("i = " + i);
    
  }
}
