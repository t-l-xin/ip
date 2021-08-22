import java.util.ArrayList;

public class CmdManager {
    private ArrayList<String> cmdList = new ArrayList<String>();
    private int cmdCount = 0;

    public void addCmd(String cmd){
        cmdList.add(cmd);
        cmdCount++;
    }

    public void showHistory(){
        if(cmdCount>0){
            for(int i = 0; i < cmdCount; i++) {
                String cmd = cmdList.get(i);
                int j = i + 1;
                System.out.printf("\n%d. %s ", j, cmd);
            }
        }else {
            System.out.println("No previous commands");
        }
    }

    public boolean checkCmd(String inputCommand, String standardCommand){
        if(inputCommand.equals(standardCommand)){
            return true;
        }
        return false;
    }
}
