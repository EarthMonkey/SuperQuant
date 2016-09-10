package dataservice.SimulationDataService;

import java.util.ArrayList;

import DAO.pojo.Simulation;

public interface SimulationDataService {
	//the po doesn't need the id, and the method will return an id ,a positive number, if the po has been persisted successfully
	public int persist(Simulation simulation);
	
	//need the id of the record.The method will return a boolean number if the po has been removed successfully
	public boolean remove(int id);
	
	//it will return all the record in the table of simulation
	public ArrayList<Simulation> getAllSimulations();
	
	
}
