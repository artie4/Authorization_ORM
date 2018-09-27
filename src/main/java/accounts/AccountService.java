package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.DBServiceImpl;
import dbService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class AccountService {

    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) {

        DBService dbService = new DBServiceImpl();
        try {
            dbService.addNewUser(userProfile.getLogin(), userProfile.getPass());
        }
        catch (DBException dbe) {
            dbe.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login) {

        DBService dbService = new DBServiceImpl();
        UserProfile userProfile = null;
        try {

            UsersDataSet uDS = dbService.getUser(login);
            userProfile = new UserProfile(uDS.getName(), uDS.getPassword());
        }
        catch (DBException dbe) {
            dbe.printStackTrace();
        }
        return userProfile;
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
