package sensing.framework.session;

import sensing.util.StringUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Data
public class SessionModel implements Serializable {
    public static final String SESSION_NAME = "session_name";
    private String userId;
    private String name;
    private UUID uid ;
    private String userIp;

    @Override
    public String toString() {
        return "SessionModel [" +
                "  USER_ID=" + StringUtil.nullToStr(userId) +
                ", NAME=" + StringUtil.nullToStr(name) +
                ", UUID=" + StringUtil.nullToStr(uid) +
                ", userIp=" + StringUtil.nullToStr(userIp) +
                "]";
    }
}
