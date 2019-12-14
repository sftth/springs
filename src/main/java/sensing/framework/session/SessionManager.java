package sensing.framework.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);

    /**
     * HttpSession 객체에서 요청된 속성값을 추출
     * @param session HttpSession 객체
     * @param attributeName 요청 속성명
     * @return HttpSession으로부터 추출된 속성값
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

	public static void setNewSessionData(HttpServletRequest request, SessionModel model) throws Exception {
		LOGGER.debug("setNewSessionData is Started");
		HttpSession session = request.getSession(false);
		session = getNewSession(request);
		session.setAttribute(SessionModel.SESSION_NAME, model);
		LOGGER.debug("setNewSessionData is Finished.");
	}

	private static HttpSession getNewSession(HttpServletRequest request) throws Exception {
		LOGGER.debug("getNewSession is Started.");
		int session_timeout = 60 * 60 ;
		request.getSession(true).setMaxInactiveInterval(session_timeout);

		return request.getSession(true);
	}

	public static SessionModel getUserInfo(HttpServletRequest request) throws Exception {
		if(request == null) {
			throw new Exception("SignIn is Failed.");
		}

		HttpSession session = request.getSession(false);
		if(session != null) {
			SessionModel model = (SessionModel)session.getAttribute(SessionModel.SESSION_NAME);
			model.setUserIp(request.getHeader("NS-CLIENT-IP"));
			return model;
		} else {
			return null;
		}
	}

	public static boolean isSignIn(HttpServletRequest request) {
		try {
			if(getUserInfo(request) == null || getUserInfo(request).getUserIp().length() < 1) {
				return false;
			}
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	public static void invalidate(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			try {
				LOGGER.debug("Session cleared" + session.getAttribute(SessionModel.SESSION_NAME));

			} catch(Exception e) {
				LOGGER.debug("Session not clear in SessionManager:" + e);
			}
			session.invalidate();
			session = null;
		}
	}
}
