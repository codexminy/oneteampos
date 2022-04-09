# :books: 로그인
<br>

> ### 로그인 화면
* 비밀번호를 입력한 후 비밀번호가 맞으면 자동으로 로그인이 되도록 구현하였습니다.
* 매니저급 이상만 로그인 가능하도록 설정했습니다.
![image](https://user-images.githubusercontent.com/85227582/162569421-22449ee5-d8b8-4007-9b59-b1012ef0bd90.png)
> ### 코드
``` java
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
```
