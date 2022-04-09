# :books: 메인
<br>

> ### 메인 화면
* 로그인을 하게 되면 나오는 메뉴 패널입니다.
* 각 버튼들을 누르면 원하는 파트로 넘어가도록 설정하였습니다.
![image](https://user-images.githubusercontent.com/85227582/162573958-ecee4d06-51db-4479-9b41-46141f2883cd.png)
<br>

> ### 기능 구현 코드
``` java
public class MainButtonEnterAction implements ActionListener {
	
    private MainFrame mainFrame;

    public MainButtonEnterAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        CardLayout card = (CardLayout) mainFrame.getMainPanel().getCardPanel().getLayout();
        card.show(mainFrame.getMainPanel().getCardPanel(), btn.getText());

        if(e.toString().contains("발주")) {
            mainFrame.getMainPanel().setVisibleFalse();
            new OrderJPanel(mainFrame);
        } else if((btn.getText().equals("사원"))){
            mainFrame.getMainPanel().setVisibleFalse();
            new LoginStaffJPanel(mainFrame);
        } else if(btn.getText().equals("매출")) {
            mainFrame.getMainPanel().setVisibleFalse();
            new SalesPanel(mainFrame);
        }
    }
}
```
