package es.upm.fi.sos.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.axis2.AxisFault;

import es.upm.fi.sos.client.UPMGeoCachingStub.AddFollower;
import es.upm.fi.sos.client.UPMGeoCachingStub.AddFollowerResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.AddUser;
import es.upm.fi.sos.client.UPMGeoCachingStub.AddUserResponseE;
import es.upm.fi.sos.client.UPMGeoCachingStub.ChangePassword;
import es.upm.fi.sos.client.UPMGeoCachingStub.ChangePasswordResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.CreateTreasure;
import es.upm.fi.sos.client.UPMGeoCachingStub.CreateTreasureResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.FindTreasure;
import es.upm.fi.sos.client.UPMGeoCachingStub.FindTreasureResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.GetMyFollowerTreasuresCreated;
import es.upm.fi.sos.client.UPMGeoCachingStub.GetMyFollowerTreasuresCreatedResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.GetMyFollowers;
import es.upm.fi.sos.client.UPMGeoCachingStub.GetMyFollowersResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.GetMyTreasuresCreated;
import es.upm.fi.sos.client.UPMGeoCachingStub.GetMyTreasuresCreatedResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.GetMyTreasuresFound;
import es.upm.fi.sos.client.UPMGeoCachingStub.GetMyTreasuresFoundResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.Login;
import es.upm.fi.sos.client.UPMGeoCachingStub.LoginResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.Logout;
import es.upm.fi.sos.client.UPMGeoCachingStub.PasswordPair;
import es.upm.fi.sos.client.UPMGeoCachingStub.RemoveFollower;
import es.upm.fi.sos.client.UPMGeoCachingStub.RemoveFollowerResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.RemoveUser;
import es.upm.fi.sos.client.UPMGeoCachingStub.RemoveUserResponse;
import es.upm.fi.sos.client.UPMGeoCachingStub.Treasure;
import es.upm.fi.sos.client.UPMGeoCachingStub.TreasureList;
import es.upm.fi.sos.client.UPMGeoCachingStub.User;
import es.upm.fi.sos.client.UPMGeoCachingStub.Username;

public class UPMGeoCachingClient {
	
	private static Integer currentInstance = null;
	private static List<Instance> instances;
	
	private static LoginResponse loginResponse;
	private static AddUserResponseE addUserResponse;
	private static RemoveUserResponse removeUserResponse;
	private static ChangePasswordResponse changePasswordResponse;
	private static AddFollowerResponse addFollowerResponse;
	private static RemoveFollowerResponse removeFollowerResponse;
	private static GetMyFollowersResponse getMyFollowersResponse;
	private static CreateTreasureResponse createTreasureResponse;
	private static FindTreasureResponse findTreasureResponse;
	private static GetMyTreasuresFoundResponse getMyTreasuresFoundResponse;
	private static GetMyTreasuresCreatedResponse getMyTreasuresCreatedResponse;
	private static GetMyFollowerTreasuresCreatedResponse getMyFollowerTreasuresCreatedResponse;
	
	
	private static UPMGeoCachingStub initStub(){
		UPMGeoCachingStub stub = null;
		try {
			stub = new UPMGeoCachingStub();
			stub._getServiceClient().engageModule("addressing");
			stub._getServiceClient().getOptions().setManageSession(true);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stub;
	}
	
	private static void addUserClient(String userNameInput) throws RemoteException{
		AddUser newUser = new AddUser();
		Username username = new Username();
		username.setUsername(userNameInput);
		newUser.setArgs0(username );
		addUserResponse = instances.get(currentInstance).getStub().addUser(newUser);
		
		System.out.println(
				"Instance <" + currentInstance + 
				">\naddUserResponse :" +
				"\n\tResponse:" + addUserResponse.get_return().getResponse() + 
				"\n\tPassword:" + addUserResponse.get_return().getPwd()
				);
	}
	
	private static void loginClient(String userNameInput, String passwordInput) throws RemoteException{
		Login login = new Login();
		User user = new User();
		instances.get(currentInstance).setUserName(userNameInput);
		user.setName(instances.get(currentInstance).getUserName());
		instances.get(currentInstance).setPassword(passwordInput);
		user.setPwd(instances.get(currentInstance).getPassword());
		login.setArgs0(user);
		loginResponse = instances.get(currentInstance).getStub().login(login );
		
		System.out.println(
				"Instance <" + currentInstance + 
				">\nloginResponse :" +
				"\n\tResponse:" + loginResponse.get_return().getResponse()
				);
	}
	
	private static void printMenu(String[] options){
		System.out.println("Current instance: " + (currentInstance == null ? ("Nso instance created yet") : (currentInstance)));
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Opcion : ");
    }
	
	public static void main(String[] args) throws RemoteException{
		
		String[] options = {
				"1- \taddUser",
                "2- \tlogin",
                "3- \tlogout",
                "4- \tremoveUser",
                "5- \tchangePassword",
                "6- \taddFollower",
                "7- \tremoveFollower",
                "8- \tgetMyFollowers",
                "9- \tcreateTreasure",
                "10- \tfindTreasure",
                "11- \tgetMyTreasuresFound",
                "12- \tgetMyTreasuresCreated",
                "13- \tgetMyFollowerTreasuresCreated",
                "14- \taddNewInstance",
                "15- \tchangeInstance",
                "16- \tcloseInstance",
                "17- \tcloseApplication"
		};

		Scanner scanner = new Scanner(System.in);
		int option;
		boolean run = true;
		
		while (run){
		printMenu(options);
		option = scanner.nextInt();
		
			switch (option) {
			    case 1:
			    	if(instances == null){
			    		System.out.println("\n\nNo instances have been created yet \n");
			    	}
			    	else{
			    		scanner.nextLine();
			    		System.out.println("userName:");
			    		addUserClient(scanner.nextLine());
			    	}
			        break;
			    case 2:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
			    		scanner.nextLine();
			    		System.out.println("userName:");
			    		String userNameInput = scanner.nextLine();
			    		System.out.println("password:");
			    		String passwordInput = scanner.nextLine();
			    		loginClient(userNameInput, passwordInput);			    	}
			        break;
			    case 3:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
			    		instances.get(currentInstance).getStub().logout(new Logout());
			    	}
			        break;
			    case 4:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
			    		
			    		RemoveUser removeUser = new RemoveUser();
			    		Username userName = new Username();
			    		scanner.nextLine();
			    		System.out.println("userName:");
			    		userName.setUsername(scanner.nextLine());
						removeUser.setArgs0(userName );
						removeUserResponse = instances.get(currentInstance).getStub().removeUser(removeUser );
						
						System.out.println(
								"Instance <" + currentInstance + 
								">\nremoveUserResponse :" +
								"\n\tResponse:" + removeUserResponse.get_return().getResponse()
								);
			    	}
			    	break;
			    case 5:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
			    		ChangePassword changePassword = new ChangePassword();
			    		PasswordPair passwordPair = new PasswordPair();
			    		scanner.nextLine();
			    		System.out.println("Old password:");
			    		passwordPair.setOldpwd(scanner.nextLine());
			    		System.out.println("New password:");
			    		passwordPair.setNewpwd(scanner.nextLine());
						changePassword.setArgs0(passwordPair );
						changePasswordResponse = instances.get(currentInstance).getStub().changePassword(changePassword );
						
						System.out.println(
								"Instance <" + currentInstance + 
								">\nchangePasswordResponse :" +
								"\n\tResponse:" + changePasswordResponse.get_return().getResponse()
								);
			    	}
			    	break;
			    case 6:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
			    		AddFollower addFollower = new AddFollower();
			    		Username userName = new Username();
			    		scanner.nextLine();
			    		System.out.println("userName:");
			    		userName.setUsername(scanner.nextLine());
			    		addFollower.setArgs0(userName);
						addFollowerResponse = instances.get(currentInstance).getStub().addFollower(addFollower );
						
						System.out.println(
								"Instance <" + currentInstance + 
								">\naddFollowerResponse :" +
								"\n\tResponse:" + addFollowerResponse.get_return().getResponse()
								);
			    	}
			    	break;
			    case 7:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
			    		RemoveFollower removeFollower = new RemoveFollower();
			    		Username userName = new Username();
			    		scanner.nextLine();
			    		System.out.println("userName:");
			    		userName.setUsername(scanner.nextLine());
			    		removeFollower.setArgs0(userName);
						removeFollowerResponse = instances.get(currentInstance).getStub().removeFollower(removeFollower );
						
						System.out.println(
								"Instance <" + currentInstance + 
								">\nremoveFollowerResponse :" +
								"\n\tResponse:" + removeFollowerResponse.get_return().getResponse()
								);
			    	}
			        break;
			    case 8:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
						getMyFollowersResponse = instances.get(currentInstance).getStub().getMyFollowers(new GetMyFollowers());
						
						System.out.println(
								"getMyFollowerResponse: " + getMyFollowersResponse.get_return().getResult()
								);
						for(int i = 0;
								getMyFollowersResponse.get_return().getFollowers() != null &&
								i < getMyFollowersResponse.get_return().getFollowers().length;
								i++)
							System.out.println("\t\t" + getMyFollowersResponse.get_return().getFollowers()[i]);
			    	}
			        break;
			    case 9:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
			    		CreateTreasure createTreasure = new CreateTreasure();
			    		Treasure treasure = new Treasure();
			    		scanner.nextLine();
			    		System.out.println("Treasure name:");
			    		treasure.setName(scanner.nextLine());
			    		System.out.println("Treasure Altitude:");
			    		treasure.setAltitude(Double.parseDouble(scanner.nextLine()));
			    		System.out.println("Treasure Latitude:");
			    		treasure.setLatitude(Double.parseDouble(scanner.nextLine()));
						createTreasure.setArgs0(treasure );
						createTreasureResponse = instances.get(currentInstance).getStub().createTreasure(createTreasure );
						
						System.out.println(
								"Instance <" + currentInstance + 
								">\ncreateTreasureResponse :" +
								"\n\tResponse:" + createTreasureResponse.get_return().getResponse()
								);
			    	}
			    	break;
			    case 10:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
			    		FindTreasure findTreasure = new FindTreasure();
			    		Treasure treasure = new Treasure();
			    		scanner.nextLine();
			    		System.out.println("Treasure name:");
			    		treasure.setName(scanner.nextLine());
			    		System.out.println("Treasure Altitude:");
			    		treasure.setAltitude(Double.parseDouble(scanner.nextLine()));
			    		System.out.println("Treasure Latitude:");
			    		treasure.setLatitude(Double.parseDouble(scanner.nextLine()));
			    		findTreasure.setArgs0(treasure);
						findTreasureResponse = instances.get(currentInstance).getStub().findTreasure(findTreasure);
						
						System.out.println(
								"Instance <" + currentInstance + 
								">\nfindTreasureResponse :" +
								"\n\tResponse:" + findTreasureResponse.get_return().getResponse()
								);
			    	}
			    	break;
			    case 11:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
						getMyTreasuresFoundResponse = instances.get(currentInstance).getStub().getMyTreasuresFound(new GetMyTreasuresFound());
						
						System.out.println(
								"Instance <" + currentInstance + 
								">\ngetMyTreasuresFoundResponse: " + getMyTreasuresFoundResponse.get_return().getResult()
								);
						TreasureList treasureList = getMyTreasuresFoundResponse.get_return();
						for(int i = 0; treasureList.getResult() && i < treasureList.getNames().length; i++){
							System.out.println("\t" + treasureList.getNames()[i]);
							if(treasureList.getAlts() != null)
								System.out.println("\t\t" + " : < " + treasureList.getAlts()[i]+ " > < " + treasureList.getLats()[i] + ">");
						}
			    	}
			        break;
			    case 12:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
			    		getMyTreasuresCreatedResponse = instances.get(currentInstance).getStub().getMyTreasuresCreated(new GetMyTreasuresCreated());
			    		
			    		System.out.println(
			    				"Instance <" + currentInstance + 
								">\ngetMyTreasuresCreatedResponse: " + getMyTreasuresCreatedResponse.get_return().getResult()
								);
						TreasureList treasureList = getMyTreasuresCreatedResponse.get_return();
						for(int i = 0; treasureList.getResult() && i < treasureList.getNames().length; i++){
							System.out.println("\t" + treasureList.getNames()[i]);
							if(treasureList.getAlts() != null)
								System.out.println("\t\t" + " : < " + treasureList.getAlts()[i]+ " > < " + treasureList.getLats()[i] + ">");
						}
			    	}
			        break;
			    case 13:
			    	if(instances == null){
			    		System.out.println("No instances have been created yet \n");
			    	}
			    	else{
			    		GetMyFollowerTreasuresCreated getMyFollowerTreasuresCreated = new GetMyFollowerTreasuresCreated();
			    		Username userName = new Username();
			    		scanner.nextLine();
			    		System.out.println("userName:");
			    		userName.setUsername(scanner.nextLine());
			    		getMyFollowerTreasuresCreated.setArgs0(userName);
						getMyFollowerTreasuresCreatedResponse = instances.get(currentInstance).getStub().getMyFollowerTreasuresCreated(getMyFollowerTreasuresCreated );
						
						System.out.println(
								"Instance <" + currentInstance + 
								">\ngetMyFollowerTreasuresCreatedResponse: " + getMyFollowerTreasuresCreatedResponse.get_return().getResult()
								);
						TreasureList treasureList = getMyFollowerTreasuresCreatedResponse.get_return();
						for(int i = 0; treasureList.getResult() && i < treasureList.getNames().length; i++){
							System.out.println("\t" + treasureList.getNames()[i]);
							if(treasureList.getAlts() != null)
								System.out.println("\t\t" + " : < " + treasureList.getAlts()[i]+ " > < " + treasureList.getLats()[i] + ">");
						}
			    	}
			        break;
			    case 14:
			    	if(currentInstance == null){
			    		currentInstance = new Integer(0);
				    	instances = new ArrayList<>();
				    }
			    	else{
			    		currentInstance++;
			    	}
			    	Instance instance = new Instance();
			    	instance.setStub(initStub());
			    	instances.add(instance);
			    	for(Instance inst : instances)System.out.println(inst.toString());
			        break;
			    case 15:
			    	int newInstance = scanner.nextInt();
			    	if(instances != null && newInstance < instances.size()){
			    		currentInstance = newInstance;
			    		System.out.println(instances.get(currentInstance));
			    		instances.get(currentInstance).setStub(initStub());
			    		loginClient(instances.get(currentInstance).getUserName(),instances.get(currentInstance).getPassword());
			    	}
			    	else
			    		System.out.println("Invalid instance option \n");
			        break;
			    case 16:
			    	if(instances == null)
			    		System.out.println("No instances have been created yet \n");
			    	else{
			    		if(instances.size() == 1){
				    		currentInstance = null;
				    		instances = null;
				    	}
				    	else{
				    		instances.remove(currentInstance);
				    		currentInstance = 0;
				    	}
			    	}
			        break;
			    case 17:
			    	run = false;
			    	 break;
			    default:
			    	System.out.println("Invalid option \n");
			        break;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*try {
			UPMGeoCachingStub  stub = new UPMGeoCachingStub();
			stub._getServiceClient().engageModule("addressing");
			stub._getServiceClient().getOptions().setManageSession(true);
			//stub._getServiceClient().getOptions().setUseSeparateListener(true);
			
			Login user = new Login();
			User userCredentials = new User();
			userCredentials.setName("admin");
			userCredentials.setPwd("admin");
			user.setArgs0(userCredentials);
			LoginResponse loginResponse = stub.login(user);
			
			System.out.println("adminlogin:" + loginResponse.get_return().getResponse());
			
			AddUser addUser = new AddUser();
			Username newUser = new Username();
			newUser.setUsername("myUser1plsa");
			addUser.setArgs0(newUser );
			AddUserResponseE addUserResponse = stub.addUser(addUser);
			
			System.out.println("myUser1pls added:" + addUserResponse.get_return().getResponse() + " < " + addUserResponse.get_return().getPwd() + " >");
			
			newUser.setUsername("myUser2plsb");
			addUser.setArgs0(newUser);
			addUserResponse = stub.addUser(addUser);
			
			System.out.println("myUser2pls added:" + addUserResponse.get_return().getResponse() + " < " + addUserResponse.get_return().getPwd() + " >");
			
			UPMGeoCachingStub  stub2 = new UPMGeoCachingStub();
			stub2._getServiceClient().engageModule("addressing");
			stub2._getServiceClient().getOptions().setManageSession(true);
			
			userCredentials.setName(newUser.getUsername());
			userCredentials.setPwd(addUserResponse.get_return().getPwd());
			user.setArgs0(userCredentials);
			loginResponse = stub2.login(user);
			
			System.out.println("myUser2 login:" + loginResponse.get_return().getResponse());
			
			CreateTreasure createTreasure = new CreateTreasure();
			Treasure treasure = new Treasure();
			treasure.setName("Treasuretest23");
			treasure.setAltitude(2.0);
			treasure.setLatitude(2.0);
			createTreasure.setArgs0(treasure );
			CreateTreasureResponse createTreasureResponse = stub2.createTreasure(createTreasure );
			
			treasure.setAltitude(1.0);
			treasure.setLatitude(4.0);
			treasure.setName("Treasuretest1");
			createTreasure.setArgs0(treasure );
			createTreasureResponse = stub2.createTreasure(createTreasure );
			
			System.out.println("treasureCreate: " + createTreasureResponse.get_return().getResponse());
			
			GetMyTreasuresCreatedResponse getMyTreasuresCreatedResponse = stub2.getMyTreasuresCreated(new GetMyTreasuresCreated());
			TreasureList treasureList = getMyTreasuresCreatedResponse.get_return();
			System.out.println("treasurelist: " + treasureList.getResult() + ":");
			for(int i = 0; treasureList.getResult() && i < treasureList.getNames().length; i++){
				System.out.println("\t" + treasureList.getNames()[i]);
				if(treasureList.getAlts() != null)
					System.out.println("\t\t" + " : < " + treasureList.getAlts()[i]+ " > < " + treasureList.getLats()[i] + ">");
			}
			
			AddFollower addFollower = new AddFollower();
			newUser.setUsername("myUser1plsa");
			addFollower.setArgs0(newUser);
			AddFollowerResponse addFollowerResponse = stub.addFollower(addFollower );
			
			System.out.println("addFollowerResponse1: " + addFollowerResponse.get_return().getResponse());
			
			newUser.setUsername("myUser2plsb");
			addFollower.setArgs0(newUser);
			addFollowerResponse = stub.addFollower(addFollower );
			
			System.out.println("addFollowerResponse2: " + addFollowerResponse.get_return().getResponse());
			
			RemoveFollower removeFollower = new RemoveFollower();
			newUser.setUsername("myUser1plsa");
			removeFollower.setArgs0(newUser);
			RemoveFollowerResponse removeFollowerResponse = stub.removeFollower(removeFollower );
			
			System.out.println("removeResponse: " + removeFollowerResponse.get_return().getResponse());
			
			GetMyFollowersResponse getMyFollowersResponse = stub.getMyFollowers(new GetMyFollowers());
			System.out.println("getMyFollowerResponse: " + getMyFollowersResponse.get_return().getResult());
			for(int i = 0;getMyFollowersResponse.get_return().getFollowers() != null && i < getMyFollowersResponse.get_return().getFollowers().length; i++){
				System.out.println("\t\t" + getMyFollowersResponse.get_return().getFollowers()[i] 
						+ "<>" + getMyFollowersResponse.get_return().getFollowers().length);
			}
			
			GetMyFollowerTreasuresCreated getMyFollowerTreasuresCreated = new GetMyFollowerTreasuresCreated();
			newUser.setUsername("myUser2plsb");
			getMyFollowerTreasuresCreated.setArgs0(newUser);
			GetMyFollowerTreasuresCreatedResponse getMyFollowerTreasuresCreatedResponse = stub.getMyFollowerTreasuresCreated(getMyFollowerTreasuresCreated );
			System.out.println("getMyFollowerTreasuresCreatedResponse: " + getMyFollowerTreasuresCreatedResponse.get_return().getResult());
			treasureList = getMyFollowerTreasuresCreatedResponse.get_return();
			for(int i = 0; treasureList.getResult() && i < treasureList.getNames().length; i++){
				System.out.println("\t" + treasureList.getNames()[i]);
				if(treasureList.getAlts() != null)
					System.out.println("\t\t" + " : < " + treasureList.getAlts()[i]+ " > < " + treasureList.getLats()[i] + ">");
			}
			
			FindTreasure findTreasure = new FindTreasure();
			findTreasure.setArgs0(treasure);
			FindTreasureResponse findTreasureResponse = stub.findTreasure(findTreasure );
			
			System.out.println("findTreasureResponse: " + findTreasureResponse.get_return().getResponse());
			
			GetMyTreasuresFoundResponse getMyTreasuresFoundResponse = stub.getMyTreasuresFound(new GetMyTreasuresFound());
			System.out.println("getMyTreasuresFoundResponse: " + getMyTreasuresFoundResponse.get_return().getResult());
			treasureList = getMyTreasuresFoundResponse.get_return();
			for(int i = 0; treasureList.getResult() && i < treasureList.getNames().length; i++){
				System.out.println("\t" + treasureList.getNames()[i]);
				if(treasureList.getAlts() != null)
					System.out.println("\t\t" + " : < " + treasureList.getAlts()[i]+ " > < " + treasureList.getLats()[i] + ">");
			}
			
			RemoveUser removeUser = new RemoveUser();
			newUser.setUsername("myUser1plsa");
			removeUser.setArgs0(newUser);
			RemoveUserResponse removeUserResponse = stub.removeUser(removeUser);
			
			System.out.println("user1rem" + removeUserResponse.get_return().getResponse());
			
			newUser.setUsername("myUser2plsb");
			removeUser.setArgs0(newUser);
			removeUserResponse = stub.removeUser(removeUser);
			
			System.out.println("user2rem" + removeUserResponse.get_return().getResponse());
			
			
			
			//stub2.logout(new Logout());
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
}

class Instance{
	private UPMGeoCachingStub stub;
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UPMGeoCachingStub getStub() {
		return stub;
	}
	public void setStub(UPMGeoCachingStub stub) {
		this.stub = stub;
	}	
}
