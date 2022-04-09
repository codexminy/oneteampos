# :books: 로그인
<br>

> ### 로그인 화면
* 비밀번호를 입력한 후 비밀번호가 맞으면 자동으로 로그인이 되도록 구현했습니다.
* 매니저급 이상만 로그인 가능하도록 설정했습니다.
![image](https://user-images.githubusercontent.com/85227582/162569421-22449ee5-d8b8-4007-9b59-b1012ef0bd90.png)
<br>

> ### 비밀번호가 틀렸을 때 화면
* 비밀번호가 틀렸을 경우 로고 상단에 안내문구가 나오도록 설정했습니다.
![image](https://user-images.githubusercontent.com/85227582/162573489-c04ba348-ac78-46cd-8bb3-f3c976d650a6.png)
<br>

> ### 기능 구현 코드
``` java
public class LoginNumberkeyAction implements MouseListener {
    private final static ArrayList<String> password = new ArrayList<>();

    static {
        String sql = "SELECT * FROM staff WHERE job_id <= 3";
        try(
            Connection conn = DBConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ){
            while(rs.next()) {
                String pwd = String.format("%04d", rs.getInt("stf_id"+""));
                password.add(pwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    MainFrame mainframe;
    JPanel loginPanel;
    JLabel[] inputlabel;
    RoundedButton keyboard;
    JLabel cntLabel;
    JLabel loginFailLabel;

    public LoginNumberkeyAction(MainFrame mainframe,JPanel loginPanel, JLabel[] inputlabel, RoundedButton keyboard, JLabel cntLabel, JLabel loginFailLabel) {
        this.mainframe = mainframe;
        this.loginPanel = loginPanel;
        this.inputlabel = inputlabel;
        this.keyboard = keyboard;
        this.cntLabel = cntLabel;
        this.loginFailLabel = loginFailLabel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int cnt = Integer.parseInt(cntLabel.getText());

        // 초기화
        if (cnt == 4) {
            cnt = 0;

            for (int i = 0; i < inputlabel.length; ++i) {
                inputlabel[i].setText("○");
            }
        }

        String keyStr = keyboard.getText();

        inputlabel[cnt].setText(keyStr);

        cntLabel.setText(++cnt + "");

        String inputpwd = "";

        boolean chkpwd = false;

        if (cnt == 4) {
            for (int i = 0; i < inputlabel.length; ++i) {
                inputpwd += inputlabel[i].getText();
            }

            for (int i = 0; i < password.size(); ++i) {
                if (password.get(i).equals(inputpwd)) {
                    ArrayList<Staff> staff = new ArrayList<>();
                    String sql = "SELECT * FROM staff WHERE stf_id = "+inputpwd;

                    try(
                        Connection conn = DBConnector.getConnection();
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        ResultSet rs = pstmt.executeQuery();
                    ){
                        while(rs.next()) {
                            staff.add(new Staff(rs));
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }


                    // 4자리수 채우기
                    String id = staff.get(0).getStfId() + "";

                    StringBuilder sb = new StringBuilder();

                    while (sb.length() < 4 - id.length()) {
                        sb.append('0');
                    }

                    sb.append(id);
                    id = sb.toString();

                    // 메인판넬로 넘어가기
                    loginPanel.setVisible(false);

                    MainPanel mainPanel = mainframe.getMainPanel();

                    mainPanel.setVisibleTrue();
                    mainframe.add(mainPanel.getCardPanel());
                    mainframe.getLoginStaffIDJLabel().setText(id);
                    mainframe.getStaffNameJLabel().setText(staff.get(0).getStfName());

                    mainPanel.getMenuPanel().getLeftPanel().getInfoIdLabel().setText(id);
                    mainPanel.getMenuPanel().getLeftPanel().getInfoNameLabel().setText("  " + staff.get(0).getStfName());
                    mainPanel.getMember().getLoginInfo().setText(id + " " + staff.get(0).getStfName());

                    System.out.println("로그인 성공!");

                    return;
                }
            }

            if (!chkpwd) {
                loginFailLabel.setVisible(true);
            }
        }
    }
}
```
