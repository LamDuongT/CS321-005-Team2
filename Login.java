import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Huan Nguyen
 */
public class Login {
	private int studentID;
	private String netID;
	private String username;
	private String passowrd;
	private boolean isLoggedIn = false;	
	ConnectDB connectdb;	
	
	public Login(String username, String password) {
		this.username = username;
		this.passowrd = password;
	}
	
	public int getStudentID () {
		return this.studentID;
	}
	
	public String getnetID () {
		return this.netID;		
	}
	
	public boolean isLoggedIn() {
		return this.isLoggedIn;
	}
	
	public void doLogin() {
		connectdb = new ConnectDB();
		
        try {
        	String queryString = "SELECT studentID, netID FROM `tblprofile` ";
        	queryString += "WHERE username ='" + this.username+ "' ";
        	queryString += "AND password = '" + this.passowrd + "' ";
        	queryString += "LIMIT 1";
        	
        	 System.out.println(queryString);
        	
            Statement statement = connectdb.theConnection.createStatement();
            ResultSet recordSet = statement.executeQuery(queryString);
            
            
            while (recordSet.next()) {
                this.studentID = recordSet.getInt("studentID");
                this.netID = recordSet.getString("netID");
                
                System.out.println("StudentID = " + studentID + "	netID = " + netID);
                isLoggedIn = true;
                
            }            
            statement.close();
            
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
        }
        finally {
        	connectdb.disconectDB();
		}		
	}
	
	public Profile getProfile(int studentID) {
		Profile aProfile = new Profile();
		
		if(this.isLoggedIn == false) {
			System.out.println("[ERROR] You are not logged in yet!");
		} else {
	        try {
	        	// reconnect to the database
	        	connectdb.reconnect();
	        	
	        	String queryString = "SELECT studentID, netID, studentName, studentEmail, username, password, profileName FROM `tblprofile` ";
	        	queryString += "WHERE studentID = " + this.studentID + " ";
	        	queryString += "LIMIT 1";
	        	
	        	 System.out.println(queryString);
	        	
	            Statement statement = connectdb.theConnection.createStatement();
	            ResultSet recordSet = statement.executeQuery(queryString);
	            
	            while (recordSet.next()) {
	                int _studentID = recordSet.getInt("studentID");
	                String _netID = recordSet.getString("netID");
	                String _studentName = recordSet.getString("studentName");
	                String _studentEmail = recordSet.getString("studentEmail");
	                String _username = recordSet.getString("username");
	                String _password = recordSet.getString("password");
	                String _profileName = recordSet.getString("profileName");
	                
	                aProfile.setValue(_studentID, _netID, _studentName, _studentEmail, _username, _password, _profileName);
	                
	                System.out.println(aProfile.toString());
	                
	            }            
	            statement.close();
	            
	        } catch (SQLException e) {
	            throw new IllegalStateException("[ERROR] there is an error with the sql querry!", e);
	        }
	        finally {
	        	connectdb.disconectDB();
			}				
			
		}
		return aProfile;
	}
}
>>>>>>> e9d8f16be2bda217ff41e4cdb570141f154b535c
