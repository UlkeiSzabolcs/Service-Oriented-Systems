
/**
 * UPMGeoCachingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package es.upm.fi.sos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub;
import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.AddUser;
import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.AddUserResponse;
import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePassword;
import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.ExistUser;
import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.Login;
import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.LoginBackEnd;
import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUser;
import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserE;
import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.RemoveUserResponseE;
import es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.UserBackEnd;
import es.upm.fi.sos.model.xsd.FollowerList;
import es.upm.fi.sos.model.xsd.Response;
import es.upm.fi.sos.model.xsd.Treasure;
import es.upm.fi.sos.model.xsd.TreasureList;
import es.upm.fi.sos.model.xsd.User;
import es.upm.fi.sos.model.xsd.Username;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
    /**
     *  UPMGeoCachingSkeleton java skeleton for the axisService
     */
    public class UPMGeoCachingSkeleton{
    	private UPMAuthenticationAuthorizationWSSkeletonStub stub;
    	private String uName;
    	private String pwd;
    	private User instanceUser;
    	private boolean activeSession;
    	private boolean adminFlag;
    	private static String adminPassword = "admin";
    	
    	private static int instanceCounter = 0;
    	private int instance;
    	
    	private static LinkedHashMap<User, ArrayList<User>> seguidoresMap;
        private static LinkedHashMap<Treasure, User> treasureMap;
        private static LinkedHashMap<Treasure, User> foundTreasureMap;
        
        private final String seguidoresMapName = "seguidoresMap.ser";
        private final String treasureMapName = "treasureMap.ser";
        private final String foundTreasureMapName = "foundTreasureMap.ser";
        
        
        public UPMGeoCachingSkeleton(){
        	if(seguidoresMap == null){
        		seguidoresMap = getSeguidoresMap();
        	}
        	if(treasureMap == null){
        		treasureMap = getTreasureMap();
        	}
        	if(foundTreasureMap == null){
        		foundTreasureMap = getFoundTreasureMap();
        	}
        	
        	instance = ++instanceCounter;
        }
        
        private LinkedHashMap<User, ArrayList<User>> getSeguidoresMap(){
        	Map<User, ArrayList<User>> sMap = new LinkedHashMap<User, ArrayList<User>>();

            try (FileInputStream fis = new FileInputStream("./" + seguidoresMapName)) {

                try (ObjectInputStream ois = new ObjectInputStream(fis)) {

                    sMap=(LinkedHashMap<User, ArrayList<User>>)ois.readObject();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return (LinkedHashMap<User, ArrayList<User>>) sMap;
        }
        
        private LinkedHashMap<Treasure, User> getTreasureMap(){
        	Map<Treasure, User> tMap = new LinkedHashMap<Treasure, User>();

            try (FileInputStream fis = new FileInputStream("./" + treasureMapName)) {

                try (ObjectInputStream ois = new ObjectInputStream(fis)) {

                    tMap=(LinkedHashMap<Treasure, User>)ois.readObject();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return (LinkedHashMap<Treasure, User>) tMap;
        }
        
        private LinkedHashMap<Treasure, User> getFoundTreasureMap(){
        	Map<Treasure, User> sMap = new LinkedHashMap<Treasure, User>();

            try (FileInputStream fis = new FileInputStream("./" + foundTreasureMapName)) {

                try (ObjectInputStream ois = new ObjectInputStream(fis)) {

                    sMap=(LinkedHashMap<Treasure, User>)ois.readObject();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return (LinkedHashMap<Treasure, User>) sMap;
        }
        
        private void saveSeguidoresMap(){
        	
            try (FileOutputStream fos = new FileOutputStream("~/" + seguidoresMapName)) {

                try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                	oos.writeObject(seguidoresMap);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        private void saveTreasureMap(){
        
            try (FileOutputStream fos = new FileOutputStream("~/" + treasureMapName)) {

                try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                	oos.writeObject(treasureMap);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        private void saveFoundTreasureMap(){
            
            try (FileOutputStream fos = new FileOutputStream("~/" + foundTreasureMapName)) {

                try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                	oos.writeObject(foundTreasureMap);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Auto generated method signature
         * 
                                     * @param logout 
             * @return  
         * @throws AxisFault 
         */
        
                 public void logout
                  (
                  es.upm.fi.sos.Logout logout
                  ) throws AxisFault
            {
                //TODO : fill this with the necessary business logic
            	if(stub == null){
                  	stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
                  	//stub._getServiceClient().getOptions().setManageSession(true);
                }
            	activeSession = false;
            	adminFlag = false;
                
            	if(instanceCounter == 1){
	            	saveSeguidoresMap();
	            	saveTreasureMap();
	            	saveFoundTreasureMap();
            	}
            	
            	instanceCounter--;
        }
                 
                 private void removeFollowerFromMap(String follower, String followed){
                	 for(Map.Entry<User, ArrayList<User>> set : seguidoresMap.entrySet()){
                		 if(set.getKey().getName().equals(followed)){
                			 for(int i = 0; i < set.getValue().size(); i++){
                				 if(set.getValue().get(i).getName().equals(follower))
                					 set.getValue().remove(i);
                			 }
                		 }
                	 }
                 }
         
        /**
         * Auto generated method signature
         * 
                                     * @param removeFollower 
             * @return removeFollowerResponse 
         * @throws AxisFault 
         */
        
                 public es.upm.fi.sos.RemoveFollowerResponse removeFollower
                  (
                  es.upm.fi.sos.RemoveFollower removeFollower
                  ) throws AxisFault
            {
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#removeFollower");
            	 if(stub == null){
                    	stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
                    	//stub._getServiceClient().getOptions().setManageSession(true);
                  }
            	 RemoveFollowerResponse response = new RemoveFollowerResponse();
 				 Response param = new Response();
            	 if(this.activeSession){
            		 if(followerRelationExists(removeFollower.getArgs0().getUsername(), this.uName)){
            			 param.setResponse(true);
            			 removeFollowerFromMap(removeFollower.getArgs0().getUsername(), this.uName);
            		 }
            		 else
            			 param.setResponse(false);
            	 }
            	 else
            		 param.setResponse(false);
                
				response.set_return(param );
				return response;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getMyTreasuresFound 
             * @return getMyTreasuresFoundResponse 
         */
        
                 public es.upm.fi.sos.GetMyTreasuresFoundResponse getMyTreasuresFound
                  (
                  es.upm.fi.sos.GetMyTreasuresFound getMyTreasuresFound
                  )
            {
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getMyTreasuresFound");
                
                GetMyTreasuresFoundResponse response = new GetMyTreasuresFoundResponse();
                TreasureList param = new TreasureList();
                if(this.activeSession){
                	param.setResult(true);
                	List<Double> latsList = new ArrayList<>();
        			List<Double> altsList = new ArrayList<>();
                	for(Map.Entry<Treasure, User> set : foundTreasureMap.entrySet()){
                		if(set.getValue().getName().equals(this.uName)){
                			altsList.add(set.getKey().getAltitude());
                			latsList.add(set.getKey().getLatitude());
                			param.addNames(set.getKey().getName());
                		}
                	}
                	double[] lats = new double[latsList.size()];
					for(int i = 0; i < latsList.size(); i++) lats[i] = latsList.get(i);
					param.setLats(lats);
					double[] alts = new double[altsList.size()];
					for(int i = 0; i < altsList.size(); i++) alts[i] = altsList.get(i);
					param.setAlts(alts);
                }
                else
                	param.setResult(false);
				response.set_return(param);
				return response ;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getMyFollowers 
             * @return getMyFollowersResponse 
         * @throws AxisFault 
         */
        
                 public es.upm.fi.sos.GetMyFollowersResponse getMyFollowers
                  (
                  es.upm.fi.sos.GetMyFollowers getMyFollowers
                  ) throws AxisFault
            {
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getMyFollowers");
            	 if(stub == null){
                   	stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
                   	//stub._getServiceClient().getOptions().setManageSession(true);
                 }
            	 GetMyFollowersResponse response = new GetMyFollowersResponse();
            	 FollowerList param = new FollowerList();
            	 if(activeSession){
            		 param.setResult(true);
	            	 for(Map.Entry<User, ArrayList<User>> set : seguidoresMap.entrySet()){
	            		 if(set.getKey().getName().equals(uName))
	            			 for(int i = 0; i < set.getValue().size(); i++)
	            				 param.addFollowers(seguidoresMap.size() + set.getValue().get(i).getName() + "  -->>  " + set.getKey().getName());
	            	 }
            	 }
            	 else
            		 param.setResult(false);
            	 
            	 
				response.set_return(param);
				return response;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param getMyTreasuresCreated 
             * @return getMyTreasuresCreatedResponse 
         */
        
                 public es.upm.fi.sos.GetMyTreasuresCreatedResponse getMyTreasuresCreated
                  (
                  es.upm.fi.sos.GetMyTreasuresCreated getMyTreasuresCreated
                  )
            {
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getMyTreasuresCreated");
                	 
                GetMyTreasuresCreatedResponse response = new GetMyTreasuresCreatedResponse();
                TreasureList param = new TreasureList();
    			
                List<Double> latsList = new ArrayList<>();
    			List<Double> altsList = new ArrayList<>();
                if(this.activeSession){
                	param.setResult(true);
                	for(Map.Entry<Treasure, User> set : treasureMap.entrySet()){
                		if(set.getValue().getName().equals(this.uName)){
                			param.addNames(set.getKey().getName());
                			altsList.add(set.getKey().getAltitude());
                			latsList.add(set.getKey().getLatitude());
                		}
                	}
                	double[] lats = new double[latsList.size()];
					for(int i = 0; i < latsList.size(); i++) lats[i] = latsList.get(i);
					param.setLats(lats);
					double[] alts = new double[altsList.size()];
					for(int i = 0; i < altsList.size(); i++) alts[i] = altsList.get(i);
					param.setAlts(alts);
                }
                else
                	param.setResult(false);
                
				response.set_return(param );
				return response ;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param removeUser 
             * @return removeUserResponse 
         * @throws RemoteException 
         */
        
                 public es.upm.fi.sos.RemoveUserResponse removeUser
                  (
                  es.upm.fi.sos.RemoveUser removeUser
                  ) throws RemoteException
            {
                //TODO : fill this with the necessary business logic
            	 if(stub == null){
                 	stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
                  	//stub._getServiceClient().getOptions().setManageSession(true);
                 }
	             if(this.activeSession && this.adminFlag){
	                 AddUser newUser = new AddUser();
	 				
	 				RemoveUserE newRemoveUser = new RemoveUserE();
	                RemoveUser param = new RemoveUser();
	                param.setName(removeUser.getArgs0().getUsername());
	                param.setPassword(this.pwd);
	 				newRemoveUser.setRemoveUser(param);
					RemoveUserResponseE stubRes = stub.removeUser(newRemoveUser);
	 				
	 				Response modelRes = new Response();
	 				modelRes.setResponse(stubRes.get_return().getResult());
	 				
	 				es.upm.fi.sos.RemoveUserResponse response = new es.upm.fi.sos.RemoveUserResponse();
	 				//response.set_return(modelRes);
	 				response.set_return(modelRes);
	 				
	 				return response;
                 }
                 else{
                	RemoveUserResponse response = new RemoveUserResponse();
                	Response param = new Response();
                	param.setResponse(false);
					response.set_return(param);
					return response;
                 }
        }
                 
                 private boolean followerRelationExists(String follower, String followed){
                	 for(Map.Entry<User, ArrayList<User>> set : seguidoresMap.entrySet()){
                		 if(set.getKey().getName().equals(followed)){
                			 for(int i = 0; i < set.getValue().size(); i++){
                				 if(set.getValue().get(i).getName().equals(follower)) return true;
                			 }
                		 }
                	 }
                	 return false;
                 }
         
        /**
         * Auto generated method signature
         * 
                                     * @param addFollower 
             * @return addFollowerResponse 
         * @throws RemoteException 
         */
        
                 public es.upm.fi.sos.AddFollowerResponse addFollower
                  (
                  es.upm.fi.sos.AddFollower addFollower
                  ) throws RemoteException
            {
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#addFollower");
	        	 if(stub == null){
	              	stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
	               	//stub._getServiceClient().getOptions().setManageSession(true);
	              }
                AddFollowerResponse response = new AddFollowerResponse();
                Response param = new Response();
                if(activeSession){
                	if(followerRelationExists(addFollower.getArgs0().getUsername() ,this.uName)){
                		param.setResponse(true);
                	}
                	else{
                		ExistUser followerUser = new ExistUser();
                		es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.Username followerUserName = 
                				new es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.Username();
                		followerUserName.setName(addFollower.getArgs0().getUsername());
						followerUser.setUsername(followerUserName);
						if(addFollower.getArgs0().getUsername().equals("admin") || stub.existUser(followerUser).get_return().getResult()){
							User follower = new User();
							follower.setName(addFollower.getArgs0().getUsername());
							ArrayList<User> seguidoresList = seguidoresMap.get(instanceUser);
							if(seguidoresList == null)
								seguidoresList = new ArrayList<User>();
							seguidoresList.add(follower);
							seguidoresMap.put(instanceUser, seguidoresList);
							
							param.setResponse(true);
						}
						else
							param.setResponse(false);
                	}
                }
                else param.setResponse(false);
                
				response.set_return(param);
				return response ;
        }
                 
                 private Treasure existsTreasure(Treasure treasure){
                	 
                	 for(Map.Entry<Treasure, User> set : treasureMap.entrySet()){
                		 if(set.getKey().getName().equals(treasure.getName()))
                			 return set.getKey();
                	 }
                	 return null;
                	 
                 }
                 
                 private User getTreasureCreator(Treasure treasure){
                	 for(Map.Entry<Treasure, User> set : treasureMap.entrySet()){
                		 if(set.getKey().getName().equals(treasure))
                			 return set.getValue();
                	 }
                	 return null;
                 }
                 
         
        /**
         * Auto generated method signature
         * 
                                     * @param createTreasure 
             * @return createTreasureResponse 
         */
        
                 public es.upm.fi.sos.CreateTreasureResponse createTreasure
                  (
                  es.upm.fi.sos.CreateTreasure createTreasure
                  )
            {
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#createTreasure");
                
                	 
                CreateTreasureResponse response = new CreateTreasureResponse();
                Response param = new Response();
                if(this.activeSession){
                	param.setResponse(true);
                	if(existsTreasure(createTreasure.getArgs0()) != null){
                		for(Map.Entry<Treasure, User> set : treasureMap.entrySet()){
                   		 	if(set.getKey().getName().equals(createTreasure.getArgs0().getName())){
                   		 		set.getKey().setAltitude(createTreasure.getArgs0().getAltitude());
                   		 		set.getKey().setLatitude(createTreasure.getArgs0().getLatitude());
                   		 		set.getKey().setName(createTreasure.getArgs0().getName());
                   		 	}
                   	 	}
                	}
                	else
                		treasureMap.put(createTreasure.getArgs0(), instanceUser);
                }
                else{
                	param.setResponse(false);
                }
				response.set_return(param );
				return response ;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param findTreasure 
             * @return findTreasureResponse 
         */
        
                 public es.upm.fi.sos.FindTreasureResponse findTreasure
                  (
                  es.upm.fi.sos.FindTreasure findTreasure
                  )
            {
				//TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#findTreasure");
                FindTreasureResponse response = new FindTreasureResponse();
                Response param = new Response();
                if(this.activeSession){
                	if(existsTreasure(findTreasure.getArgs0()) != null){
                		param.setResponse(true);
                		this.foundTreasureMap.put(findTreasure.getArgs0(), instanceUser);
                	}
                	else
                		param.setResponse(false);
                }
                else{
                	param.setResponse(false);
                }
				response.set_return(param );
                return response;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param addUser 
             * @return addUserResponse 
         * @throws RemoteException 
         */
        
                 public es.upm.fi.sos.AddUserResponse addUser
                  (
                  es.upm.fi.sos.AddUser addUser
                  ) throws RemoteException
            {
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#addUser");
                if(stub == null){
                	stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
                  	//stub._getServiceClient().getOptions().setManageSession(true);

                }
                //System.out.println(this.adminFlag + " <> " + this.activeSession + " <> " + this.uName + " <> " + this.pwd);
                if(this.activeSession && this.adminFlag){
	                AddUser newUser = new AddUser();
	                UserBackEnd param = new UserBackEnd();
	                param.setName(addUser.getArgs0().getUsername());
					newUser.setUser(param);
					
					es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.AddUserResponse stubRes = stub.addUser(newUser);
					
					es.upm.fi.sos.model.xsd.AddUserResponse modelRes = new es.upm.fi.sos.model.xsd.AddUserResponse();
					modelRes.setPwd(stubRes.get_return().getPassword());
					modelRes.setResponse(stubRes.get_return().getResult());
					
					es.upm.fi.sos.AddUserResponse response = new es.upm.fi.sos.AddUserResponse();
					response.set_return(modelRes);
					
					return response;
                }
                else{
                	es.upm.fi.sos.AddUserResponse response = new es.upm.fi.sos.AddUserResponse();
                	es.upm.fi.sos.model.xsd.AddUserResponse param = new es.upm.fi.sos.model.xsd.AddUserResponse();
                	param.setPwd("");
                	param.setResponse(false);
					response.set_return(param );
					return response ;
                }
        }
    
         
        /**
         * Auto generated method signature
         * 
                                     * @param getMyFollowerTreasuresCreated 
             * @return getMyFollowerTreasuresCreatedResponse 
         */
        
                 public es.upm.fi.sos.GetMyFollowerTreasuresCreatedResponse getMyFollowerTreasuresCreated
                  (
                  es.upm.fi.sos.GetMyFollowerTreasuresCreated getMyFollowerTreasuresCreated
                  )
            {
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getMyFollowerTreasuresCreated");
                
                	 
               GetMyFollowerTreasuresCreatedResponse response = new GetMyFollowerTreasuresCreatedResponse();
               TreasureList param = new TreasureList();
               if(this.activeSession && followerRelationExists(getMyFollowerTreasuresCreated.getArgs0().getUsername(), this.uName)){
            	   param.setResult(true);
            	   List<Double> altsList = new ArrayList<>();
            	   List<Double> latsList = new ArrayList<>();
            	   for(Map.Entry<Treasure, User> set : treasureMap.entrySet()){
            		   if(set.getValue().getName().equals(getMyFollowerTreasuresCreated.getArgs0().getUsername())){
            			   altsList.add(set.getKey().getAltitude());
            			   latsList.add(set.getKey().getLatitude());
            			   param.addNames(set.getKey().getName());
            		   }
            	   }

            	   double[] alts = new double[altsList.size()];
            	   double[] lats = new double[latsList.size()];
            	   for(int i = 0; i < altsList.size(); i++)alts[i] = altsList.get(i);
            	   for(int i = 0; i < latsList.size(); i++)lats[i] = latsList.get(i);
            	   param.setAlts(alts);
            	   param.setLats(lats);
               }
               else{
            	   param.setResult(false);
               }
               response.set_return(param );
               return response;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param changePassword 
             * @return changePasswordResponse 
         * @throws RemoteException 
         */
        
                 public es.upm.fi.sos.ChangePasswordResponse changePassword
                  (
                  es.upm.fi.sos.ChangePassword changePassword
                  ) throws RemoteException
            {
                //TODO : fill this with the necessary business logic
            	if(stub == null){
                	stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
                  	//stub._getServiceClient().getOptions().setManageSession(true);
                }
				if(this.activeSession && !adminFlag){
					ChangePassword newPwd = new ChangePassword();
					UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordBackEnd param = new UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordBackEnd();
					param.setNewpwd(changePassword.getArgs0().getNewpwd());
					param.setOldpwd(changePassword.getArgs0().getOldpwd());
					param.setName(this.uName);
					newPwd.setChangePassword(param);
					
					es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.ChangePasswordResponseE stubRes = stub.changePassword(newPwd);
					
					es.upm.fi.sos.model.xsd.Response modelRes = new es.upm.fi.sos.model.xsd.Response();
					modelRes.setResponse(stubRes.get_return().getResult());
					
					es.upm.fi.sos.ChangePasswordResponse response = new es.upm.fi.sos.ChangePasswordResponse();
					response.set_return(modelRes);
					
					return response;
 				}
				else{
					if(activeSession && adminFlag && changePassword.getArgs0().getOldpwd().equals(adminPassword)){
						this.adminPassword = changePassword.getArgs0().getNewpwd();
						ChangePasswordResponse response = new ChangePasswordResponse();
						Response param = new Response();
						param.setResponse(true);
						response.set_return(param);
						return response;
					}
					else{
						ChangePasswordResponse response = new ChangePasswordResponse();
						Response param = new Response();
						param.setResponse(false);
						response.set_return(param);
						return response;
					}
				}
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param login 
             * @return loginResponse 
         * @throws RemoteException 
         */
        
                 public es.upm.fi.sos.LoginResponse login
                  (
                  es.upm.fi.sos.Login login
                  ) throws RemoteException
            {
                //TODO : fill this with the necessary business logic
                //throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#login");
            	 if(stub == null){
                 	stub = new UPMAuthenticationAuthorizationWSSkeletonStub();
                   	//stub._getServiceClient().getOptions().setManageSession(true);
                 }
            	LoginResponse response = new LoginResponse();
         		Response param = new Response();
            	if(login.getArgs0().getName().equals("admin") && login.getArgs0().getPwd().equals(this.adminPassword)){
            		this.adminFlag = true;
            		this.activeSession = true;
            		this.uName = login.getArgs0().getName();
            		this.pwd = login.getArgs0().getPwd();
            		this.instanceUser = login.getArgs0();
            		
            		
            		param.setResponse(true);
					response.set_return(param);
            	}
            	else{
            		this.adminFlag = false;
            		
	 				Login newLogin = new Login();
	 				LoginBackEnd loginBackEnd = new LoginBackEnd();
	 				loginBackEnd.setName(login.getArgs0().getName());
	 				loginBackEnd.setPassword(login.getArgs0().getPwd());
					newLogin.setLogin(loginBackEnd);
					
					es.upm.fi.sos.authentication.UPMAuthenticationAuthorizationWSSkeletonStub.LoginResponse stubRes = stub.login(newLogin);
	  				
	  				es.upm.fi.sos.model.xsd.Response modelRes = new es.upm.fi.sos.model.xsd.Response();
	  				modelRes.setResponse(stubRes.get_return().getResult());
	  				
	  				response = new es.upm.fi.sos.LoginResponse();
	  				response.set_return(modelRes);
	  				
	  				this.activeSession = response.get_return().getResponse();
	  				
	  				if(this.activeSession){
	  					this.uName = login.getArgs0().getName();
	  					this.pwd = login.getArgs0().getPwd();
	  					this.instanceUser = login.getArgs0();
	  				}
            	}
            	return response;
        }
     
    }
    