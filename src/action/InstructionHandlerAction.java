package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;
/**
 * @author k_arita
 *
 */
public class InstructionHandlerAction extends Action  {

    public void execute(HttpServletRequest req, HttpServletResponse res)  throws Exception {

    	// リクエストからどの操作がされたかを取得
        String actionType = req.getParameter("operation");

        try {
            if ("delete".equals(actionType)) {
            	//削除処理
            	DeleteInstructionAction deleteAction = new DeleteInstructionAction();
            	deleteAction.execute(req, res);
            	req.getRequestDispatcher("instruction.jsp").forward(req, res);

            } else if ("register".equals(actionType)) {
                //登録処理
                RegistrationInstructionAction registerAction = new RegistrationInstructionAction();
                registerAction.execute(req, res);
                req.getRequestDispatcher("instruction.jsp").forward(req, res);

            } else if ("update".equals(actionType)) {
            	//修正処理
            	UpdateInstructionAction updateAction = new UpdateInstructionAction();
            	updateAction.execute(req, res);
            	req.getRequestDispatcher("instruction.jsp").forward(req, res);

            } else if ("export".equals(actionType)) {
            	//CSV出力
            	ExportInstructionAction exportAction = new ExportInstructionAction();
            	exportAction.execute(req, res);
            	//CSV出力はActionクラス側でHttpServletResponseをcloseしているのでforwardしない
            } else {
                // 不正なアクションの場合のエラーハンドリング
                req.setAttribute("message", "無効な操作です。");

            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "エラーが発生しました");
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
