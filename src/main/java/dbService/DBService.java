package dbService;

import dbService.dataSets.UsersDataSet;

public interface DBService{

    UsersDataSet getUser(long id) throws DBException;

    UsersDataSet getUser(String name) throws DBException;

    long addNewUser(String login, String password) throws DBException;

    void printConnectInfo();

}
