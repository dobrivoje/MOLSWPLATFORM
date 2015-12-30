package db.retail.interfaces;

import java.util.List;

/**
 * This interface facilitates solving Master-Detail situations, such as creation
 * of trees with root nodes with all sub-nodes list.
 *
 * @author Dobri
 * @param <MasterType> Master for which we generate list of details
 */
public interface IMasterDetail<MasterType> {

    /**
     * Get the detail list for the master root data.
     *
     * @param master
     * @return List
     */
    public List getDetails(MasterType master);

    /**
     * For all master nodes, create all their lists of details.
     *
     * @return
     */
    public List<MasterType> getAllDetails();

}
