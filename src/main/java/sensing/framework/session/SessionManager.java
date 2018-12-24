package sensing.framework.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
	/**
	 * HttpSession ��ü���� ��û�� �Ӽ����� ����
	 * @param session HttpSession ��ü
	 * @param attributeName ��û �Ӽ���
	 * @return HttpSession���κ��� ����� �Ӽ���
	 */
	public static Object getAttribute(HttpSession session, String attributeName) {
		return getAttributePrivate(session, attributeName);
	}
	
	public static Object getAttribute(HttpServletRequest request, String attributeName) {
		return getAttributePrivate(request, attributeName);
	}
	
	public static Object getAttributePrivate(HttpServletRequest request, String attributeName) {
		Object result = null;
		if(request != null) {
			HttpSession session = request.getSession(false);
			result = SessionManager.getAttribute(session, attributeName);
		}
		return result;
	}
	
	private static Object getAttributePrivate(HttpSession session, String attributeName) {
		Object result = null;
		if(session != null && attributeName != null) {
			result = session.getAttribute(attributeName);
		}
		
		return result;
	}
	
	public static void setAttribute(HttpSession session, String attributeName, Object value) {
		setAttributePrivate(session, attributeName, value);
	}
	
	public static void setAttributePrivate(HttpSession session, String attributeName, Object value) {
		if(session != null && attributeName != null) {
			session.setAttribute(attributeName, value);
		}
	}
}
